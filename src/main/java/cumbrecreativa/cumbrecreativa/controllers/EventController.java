package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.EventCreatorDTO;
import cumbrecreativa.cumbrecreativa.DTOs.EventDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.models.Location;
import cumbrecreativa.cumbrecreativa.models.Rol;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import cumbrecreativa.cumbrecreativa.repositories.EventRepository;
import cumbrecreativa.cumbrecreativa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/getAllEvents")
    private ResponseEntity<?> getAllEvents(Authentication authentication) {
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (customer == null || customer.getRol() != Rol.ADMIN) {
            return new ResponseEntity<>("Acceso denegado", HttpStatus.FORBIDDEN);
        }
        Set<EventDTO> eventDTOSet = eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toSet());
        return new ResponseEntity<>(eventDTOSet, HttpStatus.OK);
    }

    @PostMapping("/newEvent")
    public ResponseEntity<?> newEvent(Authentication authentication, @RequestBody EventCreatorDTO eCreator) {
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (customer == null || customer.getRol() == Rol.USER) {
            return new ResponseEntity<>("Debes tener perfil de organizador para ingresar nuevos eventos", HttpStatus.FORBIDDEN);
        }
        if (eCreator.getTitle() == null || eCreator.getTitle().isBlank()) {
            return new ResponseEntity<>("Debes ingresar un título de evento", HttpStatus.FORBIDDEN);
        }
        if (eCreator.getDescription() == null || eCreator.getDescription().isBlank()) {
            return new ResponseEntity<>("Debes ingresar una descripción de evento", HttpStatus.FORBIDDEN);
        }
        if (eCreator.getDate() == null || eCreator.getDate().isBefore(LocalDate.now())) {
            return new ResponseEntity<>("La fecha del evento no puede ser anterior a la actual", HttpStatus.FORBIDDEN);
        }
        if (eCreator.getDate() == null || eCreator.getTime().toString().isBlank()) {
            return new ResponseEntity<>("Debes ingresar una hora de evento", HttpStatus.FORBIDDEN);
        }
        Optional<Location> optionalLocation = locationRepository.findById(eCreator.getEventId());
        if (optionalLocation.isEmpty()) {
            return new ResponseEntity<>("Locación de evento incorrecta", HttpStatus.FORBIDDEN);
        }
        Location location = optionalLocation.get();
        Event newEvent = new Event(eCreator.getTitle(), customer.getUserName(), eCreator.getDescription(), eCreator.getDate(), eCreator.getTime(), (byte) 0, true);
        customer.addEvent(newEvent);
        location.addEvent(newEvent);
        customerRepository.save(customer);
        locationRepository.save(location);
        eventRepository.save(newEvent);
        return new ResponseEntity<>("Evento creado", HttpStatus.CREATED);
    }
}