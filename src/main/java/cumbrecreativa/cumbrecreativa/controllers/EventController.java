package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.EventCreatorDTO;
import cumbrecreativa.cumbrecreativa.DTOs.EventDTO;
import cumbrecreativa.cumbrecreativa.DTOs.EventEditorDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.models.Location;
import cumbrecreativa.cumbrecreativa.models.Rol;
import cumbrecreativa.cumbrecreativa.services.CustomerService;
import cumbrecreativa.cumbrecreativa.services.EventService;
import cumbrecreativa.cumbrecreativa.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EventService eventService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/getAllEvents")
    private ResponseEntity<?> getAllEvents() {
        Set<EventDTO> eventDTOSet = eventService.findAll();
        return new ResponseEntity<>(eventDTOSet, HttpStatus.OK);
    }

    @PostMapping("/newEvent")
    public ResponseEntity<?> newEvent(Authentication authentication, @RequestBody EventCreatorDTO eCreator) {
        Customer customer = customerService.findByEmail(authentication.getName());
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
        if (eCreator.getLocationId() == null) {
            return new ResponseEntity<>("Debes ingresar una locación de evento", HttpStatus.FORBIDDEN);
        }
        Location location = locationService.findById(eCreator.getLocationId());
        if (location == null) {
            return new ResponseEntity<>("Locación no válida", HttpStatus.FORBIDDEN);
        }
        Event newEvent = new Event(eCreator.getTitle(), customer.getUserName(), eCreator.getDescription(), eCreator.getImg(), eCreator.getDate(), eCreator.getTime(), (byte) 0, true);
        customer.addEvent(newEvent);
        location.addEvent(newEvent);
        customerService.save(customer);
        locationService.save(location);
        eventService.save(newEvent);
        return new ResponseEntity<>("Evento creado", HttpStatus.CREATED);
    }

    @PatchMapping("/editEvent")
    public ResponseEntity<?> editEvent(Authentication authentication, @RequestBody EventEditorDTO eEditor) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("No estás autenticado.", HttpStatus.UNAUTHORIZED);
        }
        Customer customer = customerService.findByEmail(authentication.getName());
        if (customer == null) {
            return new ResponseEntity<>("Debes estar logueado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        Event event = eventService.findById(eEditor.getEventId());
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
        eventService.save(event);
        return new ResponseEntity<>("Evento modificado", HttpStatus.OK);
    }
}