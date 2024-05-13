package cumbrecreativa.cumbrecreativa.configurations;

import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Authentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputMail -> {
            Customer customer = customerRepository.findByEmail(inputMail);
            if (customer == null) {
                throw new UsernameNotFoundException("Email no encontrado: " + inputMail + "Hace falta registro");
            }
            if (!customer.isActivate()) {
                throw new DisabledException("Cuenta no activada" + inputMail);
            }
            return new User(customer.getEmail(), customer.getPassword(), AuthorityUtils.createAuthorityList(customer.getRol().toString()));
        });
    }
}