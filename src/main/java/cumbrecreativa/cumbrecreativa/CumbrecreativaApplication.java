package cumbrecreativa.cumbrecreativa;

import cumbrecreativa.cumbrecreativa.models.*;
import cumbrecreativa.cumbrecreativa.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class CumbrecreativaApplication {

    public static void main(String[] args) {

        SpringApplication.run(CumbrecreativaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CustomerRepository customerRepository, EventRepository eventRepository, AssistanceRepository assistanceRepository, CommentRepository commentRepository, LocationRepository locationRepository, RatingRepository ratingRepository) {
        return args -> {
            Customer testCustomer = new Customer("testUser", "testUser", "testUser", LocalDate.of(1987, 4, 8), Gender.NOTSPECIFY, Rol.USER, true, "testmail@outlook.com", "123456");
            customerRepository.save(testCustomer);
            Event testEvent = new Event("testevent", "testOrganizer", "testDescription", LocalDate.now(), LocalTime.now(), (byte) 4);
            eventRepository.save(testEvent);
            Assistance testAssistance = new Assistance();
            assistanceRepository.save(testAssistance);
            Comment testComment = new Comment("testText", LocalDate.now());
            commentRepository.save(testComment);
            Location testLocation = new Location("testName", "testAddress", "testCity", "testCountry", "testGPS");
            locationRepository.save(testLocation);
            Rating testRating = new Rating((byte) 4, LocalDate.now());
            ratingRepository.save(testRating);
        };
    }
}