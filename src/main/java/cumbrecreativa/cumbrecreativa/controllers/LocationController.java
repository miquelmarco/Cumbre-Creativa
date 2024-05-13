package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.LocationCreatorDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Location;
import cumbrecreativa.cumbrecreativa.models.Rol;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import cumbrecreativa.cumbrecreativa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRepository locationRepository;
    @PostMapping("/newLocation")
    public ResponseEntity<?> newLocation(Authentication authentication, @RequestBody LocationCreatorDTO lCreator) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("Debes estar autenticado", HttpStatus.FORBIDDEN);
        }
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (customer == null || customer.getRol() == Rol.USER) {
            return new ResponseEntity<>("Sin autorización para ingresar locaciones", HttpStatus.FORBIDDEN);
        }
        if (lCreator.getName().isBlank()) {
            return new ResponseEntity<>("Debes ingresar un nombre de locación", HttpStatus.FORBIDDEN);
        }
        if (lCreator.getAddress().isBlank()) {
            return new ResponseEntity<>("Debes ingresar una dirección de locación", HttpStatus.FORBIDDEN);
        }
        if (lCreator.getCity().isBlank()) {
            return new ResponseEntity<>("Debes ingresar una ciudad de locación", HttpStatus.FORBIDDEN);
        }
        if (lCreator.getCountry().isBlank()) {
            return new ResponseEntity<>("Debes ingresar el país de locación", HttpStatus.FORBIDDEN);
        }
        String variableGPS = null;
        if (lCreator.getGps() != null) {
            variableGPS = lCreator.getGps();
        }
        locationRepository.save(new Location(lCreator.getName(), lCreator.getAddress(), lCreator.getCity(), lCreator.getCountry(), variableGPS));
        return new ResponseEntity<>("Locación ingresada", HttpStatus.CREATED);
    }
}