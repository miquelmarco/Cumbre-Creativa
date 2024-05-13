package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.EventCreatorDTO;
import cumbrecreativa.cumbrecreativa.DTOs.EventDTO;
import cumbrecreativa.cumbrecreativa.DTOs.EventEditorDTO;
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
    private ResponseEntity<?> getAllEvents() {
        Set<EventDTO> eventDTOSet = eventRepository.findAll().stream().filter(Event::isActivated).map(EventDTO::new).collect(Collectors.toSet());
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
        Optional<Location> optionalLocation = locationRepository.findById(eCreator.getLocationId());
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

    @PatchMapping("/editEvent")
    public ResponseEntity<?> editEvent(Authentication authentication, @RequestBody EventEditorDTO eEditor) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("No estás autenticado.", HttpStatus.UNAUTHORIZED);
        }
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (customer == null) {
            return new ResponseEntity<>("Debes estar logueado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        Event event = eventRepository.findById(eEditor.getEventId()).orElse(null);
        if (event == null) {
            return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
        }
        if (!(customer.getRol() == Rol.ADMIN || (customer.getRol() == Rol.ORGANIZER && customer.getEventSet().contains(event)))) {
            return new ResponseEntity<>("No tienes los permisos necesarios para modificar este evento", HttpStatus.UNAUTHORIZED);
        }
        event.setTitle(eEditor.getTitle());
        event.setDescription(eEditor.getDescription());
        event.setDate(eEditor.getDate());
        event.setTime(eEditor.getTime());
        event.setActivated(eEditor.isActivated());
        eventRepository.save(event);
        return new ResponseEntity<>("Evento modificado", HttpStatus.OK);
    }
}