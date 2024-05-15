package cumbrecreativa.cumbrecreativa.services;

import cumbrecreativa.cumbrecreativa.DTOs.CustomerDTO;
import cumbrecreativa.cumbrecreativa.models.Customer;

import java.util.Set;

public interface CustomerService {
    void save(Customer customer);
    Customer findById(Long iD);
    Set<CustomerDTO> findAll();
    Customer findByEmail(String email);
    Customer findByUserName(String userName);
    Customer findByVerification(String code);
    Customer findByResetPasswordToken(String token);
}