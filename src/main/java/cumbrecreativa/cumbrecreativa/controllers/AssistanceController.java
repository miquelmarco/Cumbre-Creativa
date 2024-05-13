package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.AssistanceDTO;
import cumbrecreativa.cumbrecreativa.DTOs.EventDTO;
import cumbrecreativa.cumbrecreativa.models.Assistance;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.models.Rol;
import cumbrecreativa.cumbrecreativa.repositories.AssistanceRepository;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import cumbrecreativa.cumbrecreativa.repositories.EventRepository;;
import cumbrecreativa.cumbrecreativa.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AssistanceController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AssistanceRepository assistanceRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EmailService emailService;

    @PostMapping("/newAssistance")
    public ResponseEntity<?> newAssistance(Authentication authentication, @RequestParam Long eventId, @RequestParam Byte companion) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("Debes estar autenticado", HttpStatus.FORBIDDEN);
        }
        if (eventId == null || !eventRepository.existsById(eventId)) {
            return new ResponseEntity<>("Evento no válido o no encontrado", HttpStatus.NOT_FOUND);
        }
        if (companion == null || (companion != 1 && companion != 2 && companion != 3)) {
            return new ResponseEntity<>("Número de acompañantes no válido", HttpStatus.FORBIDDEN);
        }
        Customer customer = customerRepository.findByEmail(authentication.getName());
        Event event = eventRepository.findById(eventId).orElse(null);
        Assistance newAssistance = new Assistance(companion, false, false);
        customer.addAssistance(newAssistance);
        event.addAssistance(newAssistance);
        assistanceRepository.save(newAssistance);
        eventRepository.save(event);
        customerRepository.save(customer);
        String verCode = UUID.randomUUID().toString();
        newAssistance.setConfirmationCode(verCode);
        assistanceRepository.save(newAssistance);
        emailService.sendVerificationAssistance(customer.getEmail(), verCode);
        return new ResponseEntity<>("Asistencia ingresada, revisa tu email para confirmar", HttpStatus.ACCEPTED);
    }

    @GetMapping("/confirmAssistance/{code}")
    public ResponseEntity<?> confirmAssistance(@PathVariable String code) {
        Assistance assistance = assistanceRepository.findByConfirmationCode(code);
        if (assistance == null) {
            return new ResponseEntity<>("Código no válido", HttpStatus.FORBIDDEN);
        }
        if (assistance.isActive()) {
            return new ResponseEntity<>("La asistencia ya ha sido confirmada anteriormente", HttpStatus.FORBIDDEN);
        }
        assistance.setActive(true);
        assistanceRepository.save(assistance);
        return new ResponseEntity<>("Asistencia confirmada", HttpStatus.OK);
    }
    @GetMapping("/getOrganAssistances")
    public ResponseEntity<?> getOrganAssistances(Authentication authentication, @RequestParam Long eventId) {
        if (authentication == null ||  authentication.getName() == null) {
            return new ResponseEntity<>("Debes estar logueado", HttpStatus.FORBIDDEN);
        }
        if (eventId == null) {
            return new ResponseEntity<>("Evento no encontrado o no válido", HttpStatus.NOT_FOUND);
        }
        Customer customer = customerRepository.findByEmail(authentication.getName());
        Event event = eventRepository.findById(eventId).orElse(null);
        if (customer == null || customer.getRol() != Rol.ORGANIZER) {
            return new ResponseEntity<>("No autorizado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        Set<AssistanceDTO> assistanceDTOS = new HashSet<>();
        if (event != null && event.getAssistanceSet() != null) {
            assistanceDTOS = event.getAssistanceSet().stream().map(AssistanceDTO::new).collect(Collectors.toSet());
        }
        return new ResponseEntity<>(assistanceDTOS, HttpStatus.FOUND);
    }
    @GetMapping("/getAssistancesByEventId/{eventId}")
    public ResponseEntity<?> getAssistancesByEventId(Authentication authentication, @PathVariable Long eventId) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("No estás autenticado", HttpStatus.FORBIDDEN);
        }
        if (eventId == null) {
            return new ResponseEntity<>("Evento no válido", HttpStatus.NOT_FOUND);
        }
        Customer customer = customerRepository.findByEmail(authentication.getName());
        Event event = eventRepository.findById(eventId).orElse(null);
        if (customer == null || customer.getRol() != Rol.ADMIN) {
            return new ResponseEntity<>("No autorizado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        if (event == null) {
            return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
        }
        Set<AssistanceDTO> assistanceDTOS = new HashSet<>();
        assistanceDTOS = event.getAssistanceSet().stream().map(AssistanceDTO::new).collect(Collectors.toSet());
        return new ResponseEntity<>(assistanceDTOS, HttpStatus.OK);
    }
    @PatchMapping("/cancelAssistance")
    public ResponseEntity<?> cancelAssistance(Authentication authentication, @RequestParam Long assistanceId) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("Debes estar logueado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (customer == null || customer.getRol() == Rol.USER) {
            return new ResponseEntity<>("No autorizado para esta acción", HttpStatus.FORBIDDEN);
        }
        Assistance assistance =  assistanceRepository.findById(assistanceId).orElse(null);
        if (assistance == null) {
            return new ResponseEntity<>("Asistente no encontrado", HttpStatus.NOT_FOUND);
        }
        boolean assistanceBelongs = customer.getEventSet().stream().anyMatch(event -> event.getAssistanceSet().contains(assistance));
        if (customer.getRol() == Rol.ORGANIZER && !assistanceBelongs) {
            return new ResponseEntity<>("No autorizado", HttpStatus.FORBIDDEN);
        }
        assistance.setCancel(true);
        assistanceRepository.save(assistance);
        return new ResponseEntity<>("Asistencia cancelada", HttpStatus.OK);
    }
}