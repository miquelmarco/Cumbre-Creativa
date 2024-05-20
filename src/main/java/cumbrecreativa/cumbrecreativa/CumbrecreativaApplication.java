package cumbrecreativa.cumbrecreativa;

import cumbrecreativa.cumbrecreativa.models.*;
import cumbrecreativa.cumbrecreativa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class CumbrecreativaApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(CumbrecreativaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CustomerRepository customerRepository, EventRepository eventRepository, AssistanceRepository assistanceRepository, CommentRepository commentRepository, LocationRepository locationRepository, RatingRepository ratingRepository) {
        return args -> {
            Customer testCustomer = new Customer("testUser", "testUser", "testUser", LocalDate.of(1987, 4, 8), Gender.NOTSPECIFY, Rol.ADMIN, "lala", true, "miquel.marco@outlook.com", passwordEncoder.encode("123456"));
            customerRepository.save(testCustomer);
            Customer organCustomer = new Customer("organUser", "organUser", "organUser", LocalDate.of(1987, 4, 8), Gender.NOTSPECIFY, Rol.ORGANIZER, "lalala", true, "miquel.marco.01@gmail.com", passwordEncoder.encode("123456"));
            customerRepository.save(organCustomer);
            ;
            Location testLocation = new Location("testName", "testAddress", "testCity", "testCountry", "testGPS");
            locationRepository.save(testLocation);
        };
    }
}