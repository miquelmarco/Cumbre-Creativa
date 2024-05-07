package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.models.Rating;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import cumbrecreativa.cumbrecreativa.repositories.EventRepository;
import cumbrecreativa.cumbrecreativa.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RatingController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping("/newRating")
    public ResponseEntity<?> newRating(@RequestParam Byte rating, @RequestParam Long eventId, Authentication authentication) {
        Logger logger = LoggerFactory.getLogger(getClass());

        try {
            if (rating == null || eventId == null) {
                return new ResponseEntity<>("Falta información para ingresar la calificación", HttpStatus.FORBIDDEN);
            }
            if (rating < 1 || rating > 5) {
                return new ResponseEntity<>("Calificación no válida", HttpStatus.FORBIDDEN);
            }
            if (authentication == null || authentication.getName() == null) {
                return new ResponseEntity<>("Debes estar logueado para calificar", HttpStatus.FORBIDDEN);
            }
            Customer customer = customerRepository.findByEmail(authentication.getName());
            if (customer == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.FORBIDDEN);
            }
            Event event = eventRepository.findById(eventId).orElse(null);
            if (event == null) {
                return new ResponseEntity<>("Evento no encontrado", HttpStatus.FORBIDDEN);
            }
            boolean hasRated = event.getRatingSet().stream().anyMatch(rating1 -> rating1.getUserRating().getId().equals(customer.getId()));
            if (hasRated) {
                return new ResponseEntity<>("Ya has calificado este evento", HttpStatus.CONFLICT);
            }
            Rating newRating = new Rating(rating, LocalDate.now(), customer, event);
            ratingRepository.save(newRating);
            customer.addRating(newRating);
            event.addRating(newRating);
            customerRepository.save(customer);
            eventRepository.save(event);

            Set<Rating> ratings = event.getRatingSet();
            double average = ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
            Byte averageByte = (byte) Math.round(average);
            event.setRating(averageByte);
            eventRepository.save(event);
            return new ResponseEntity<>("Calificación ingresada", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al ingresar calificación o calcular el promedio", e);
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}