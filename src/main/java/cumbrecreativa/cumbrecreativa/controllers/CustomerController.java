package cumbrecreativa.cumbrecreativa.controllers;

import cumbrecreativa.cumbrecreativa.DTOs.CustomerDTO;
import cumbrecreativa.cumbrecreativa.configurations.Authorization;
import cumbrecreativa.cumbrecreativa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    public CustomerRepository customerRepository;
    @GetMapping("/getAllCustomers")
    private Set<CustomerDTO> getAllCustomers () {
        return customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toSet());
    }
}