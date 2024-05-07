package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.CustomerDTO;
import cumbrecreativa.cumbrecreativa.DTOs.RegisterDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public CustomerRepository customerRepository;
    @GetMapping("/getAllCustomers")
    private Set<CustomerDTO> getAllCustomers () {
        return customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toSet());
    }

    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        if (registerDTO.getName().isBlank()) {
            return new ResponseEntity<>("Debe ingresar un nombre", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getLastName().isBlank()) {
            return new ResponseEntity<>("Debe ingresar un apellido", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getUserName().isBlank()) {
            return new ResponseEntity<>("Debe ingresar un nombre de usuario", HttpStatus.FORBIDDEN);
        }
        String usernamePattern = "^[a-zA-Z0-9]{0,15}$";
        if (!registerDTO.getUserName().matches(usernamePattern)) {
            return new ResponseEntity<>("El nombre de usuario debe contener sólo letras o números y tener máximo 15 caracteres", HttpStatus.FORBIDDEN);
        }
        if (customerRepository.findByUserName(registerDTO.getUserName()) != null) {
            return new ResponseEntity<>("Nombre de usuario ya está en uso", HttpStatus.CONFLICT);
        }
        LocalDate today = LocalDate.now();
        LocalDate minimumBirthdate = today.minusYears(18);
        LocalDate maximumBirthDate = today.minusYears(120);
        if (registerDTO.getBirthdate().isAfter(today)) {
            return new ResponseEntity<>("Fecha de nacimiento no puede ser posterior a hoy", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getBirthdate().isAfter(minimumBirthdate)) {
            return new ResponseEntity<>("Debes tener al menos 18 años para registrarte", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getBirthdate().isBefore(maximumBirthDate)) {
            return new ResponseEntity<>("La fecha de nacimiento es demasiado extensa", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getGender().toString().isBlank()) {
            return new ResponseEntity<>("Debe ingresar género", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getRol().toString().isBlank()) {
            return new ResponseEntity<>("Debe ingresar un rol de usuario", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.getEmail().isBlank()) {
            return new ResponseEntity<>("Debe ingresar un email", HttpStatus.FORBIDDEN);
        }
        if (customerRepository.findByEmail(registerDTO.getEmail()) != null) {
            return new ResponseEntity<>("El correo electrónico ya está en uso", HttpStatus.CONFLICT);
        }
        if (registerDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("Debe ingresar contraseña", HttpStatus.FORBIDDEN);
        }
        Customer newCustomer = new Customer(registerDTO.getName(), registerDTO.getLastName(), registerDTO.getUserName(),
                registerDTO.getBirthdate(), registerDTO.getGender(), registerDTO.getRol(), true, registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword()));
        customerRepository.save(newCustomer);
        return new ResponseEntity<>("Registro completo", HttpStatus.OK);
    }
}