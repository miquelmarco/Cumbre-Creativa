package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.CustomerDTO;
import cumbrecreativa.cumbrecreativa.DTOs.RegisterDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Rol;
import cumbrecreativa.cumbrecreativa.services.CustomerService;
import cumbrecreativa.cumbrecreativa.services.implement.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(Authentication authentication) {
        Customer customer = customerService.findByEmail(authentication.getName());
        if (customer == null || customer.getRol() != Rol.ADMIN) {
            return new ResponseEntity<>("Acceso denegado", HttpStatus.FORBIDDEN);
        }
        Set<CustomerDTO> customerDTOSet = customerService.findAll();
        return new ResponseEntity<>(customerDTOSet, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>("Usuario no autenticado", HttpStatus.UNAUTHORIZED);
        }
        Customer customer = customerService.findByEmail(authentication.getName());
        if (customer == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        CustomerDTO customerDTO = new CustomerDTO(customer);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
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
        if (customerService.findByUserName(registerDTO.getUserName()) != null) {
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
        if (customerService.findByEmail(registerDTO.getEmail()) != null) {
            return new ResponseEntity<>("El correo electrónico ya está en uso", HttpStatus.CONFLICT);
        }
        if (registerDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("Debe ingresar contraseña", HttpStatus.FORBIDDEN);
        }
        String verCode = UUID.randomUUID().toString();
        Customer newCustomer = new Customer(registerDTO.getName(), registerDTO.getLastName(), registerDTO.getUserName(),
                registerDTO.getBirthdate(), registerDTO.getGender(), registerDTO.getRol(), verCode, false, registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword()));
        customerService.save(newCustomer);
        emailService.sendVerificationMail(newCustomer.getEmail(), verCode);
        return new ResponseEntity<>("Registro completo, verifica tu cuenta", HttpStatus.OK);
    }

    @GetMapping("/verifyMail/{code}")
    public ResponseEntity<?> verifyAccount(@PathVariable String code) {
        Customer customer = customerService.findByVerification(code);
        if (customer == null) {
            return new ResponseEntity<>("Código de verificación no válido", HttpStatus.FORBIDDEN);
        }
        customer.setActivate(true);
        customerService.save(customer);
        return new ResponseEntity<>("Verificación correcta", HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        Customer customer = customerService.findByEmail(email);
        if (customer == null) {
            return new ResponseEntity<>("No se encontró el usuario", HttpStatus.NOT_FOUND);
        }
        String token = UUID.randomUUID().toString();
        customer.setResetPasswordToken(token);
        customer.setResetPasswordExpiration(LocalDateTime.now().plusHours(1));
        customerService.save(customer);
        emailService.passwordRecovery(customer.getEmail(), token);
        return new ResponseEntity<>("Correo de restablecimiento enviado", HttpStatus.OK);
    }

    @PostMapping("/resetPassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestParam String newPassword) {
        Customer customer = customerService.findByResetPasswordToken(token);
        if (customer == null || customer.getResetPasswordExpiration().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>("Enlace inválido o caducado", HttpStatus.FORBIDDEN);
        }
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerService.save(customer);
        return new ResponseEntity<>("Contraseña restablecida correctamente", HttpStatus.OK);
    }
}