package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.RatingDTO;
import cumbrecreativa.cumbrecreativa.models.Comment;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.models.Rating;
import cumbrecreativa.cumbrecreativa.repositories.RatingRepository;
import cumbrecreativa.cumbrecreativa.services.CommentService;
import cumbrecreativa.cumbrecreativa.services.CustomerService;
import cumbrecreativa.cumbrecreativa.services.EventService;
import cumbrecreativa.cumbrecreativa.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RatingController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EventService eventService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/getRatingsById/{eventId}")
    public ResponseEntity<?> getRatingsById(@PathVariable Long eventId) {
        Event event = eventService.findById(eventId);
        if (event == null) {
            return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
        }
        Set<RatingDTO> ratingSet = event.getRatingSet().stream().map(RatingDTO::new).collect(Collectors.toSet());
        return new ResponseEntity<>(ratingSet, HttpStatus.OK);
    }

    @PostMapping("/newRating")
    public ResponseEntity<?> newRating(@RequestParam Byte rating, @RequestParam Long eventId, @RequestParam String textComment, Authentication authentication) {
        Logger logger = LoggerFactory.getLogger(getClass());

        try {
            if (rating == null || eventId == null) {
                return new ResponseEntity<>("Falta información para ingresar la calificación", HttpStatus.FORBIDDEN);
            }
            if (rating < 1 || rating > 5) {
                return new ResponseEntity<>("Calificación no válida", HttpStatus.FORBIDDEN);
            }
            if (textComment.isBlank()) {
                return new ResponseEntity<>("Comentario no encontrado", HttpStatus.NOT_FOUND);
            }
            if (authentication == null || authentication.getName() == null) {
                return new ResponseEntity<>("Debes estar logueado para calificar", HttpStatus.FORBIDDEN);
            }
            Customer customer = customerService.findByEmail(authentication.getName());
            if (customer == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.FORBIDDEN);
            }
            Event event = eventService.findById(eventId);
            if (event == null) {
                return new ResponseEntity<>("Evento no encontrado", HttpStatus.FORBIDDEN);
            }
            boolean hasRated = event.getRatingSet().stream().anyMatch(rating1 -> rating1.getUserRating().getId().equals(customer.getId()));
            if (hasRated) {
                return new ResponseEntity<>("Ya has calificado este evento", HttpStatus.CONFLICT);
            }

            Comment comment = new Comment(textComment, LocalDate.now());
            Rating newRating = new Rating(rating, LocalDate.now(), customer, event);
            comment.setRating(newRating);
            newRating.setComment(comment);
            commentService.save(comment);
            ratingService.save(newRating);
            customer.addRating(newRating);
            event.addRating(newRating);
            customerService.save(customer);
            eventService.save(event);

            Set<Rating> ratings = event.getRatingSet();
            double average = ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
            Byte averageByte = (byte) Math.round(average);
            event.setRating(averageByte);
            eventService.save(event);
            return new ResponseEntity<>("Calificación ingresada", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al ingresar calificación o calcular el promedio", e);
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}