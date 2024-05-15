package cumbrecreativa.cumbrecreativa.services.implement;

import cumbrecreativa.cumbrecreativa.DTOs.CustomerDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import cumbrecreativa.cumbrecreativa.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplement implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long iD) {
        return customerRepository.findById(iD).orElse(null);
    }

    @Override
    public Set<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer findByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }

    @Override
    public Customer findByVerification(String code) {
        return customerRepository.findByVerification(code);
    }

    @Override
    public Customer findByResetPasswordToken(String token) {
        return customerRepository.findByResetPasswordToken(token);
    }
}