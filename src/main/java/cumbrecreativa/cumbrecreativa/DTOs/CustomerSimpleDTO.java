package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Customer;
import cumbrecreativa.cumbrecreativa.models.Gender;
import cumbrecreativa.cumbrecreativa.models.Rol;

import java.time.LocalDate;

public class CustomerSimpleDTO {
    private Long id;
    private String name;
    private String lastName;
    private String userName;
    private LocalDate birthdate;
    private Gender gender;
    private Rol rol;
    private boolean isActivate;
    private String email;

    public CustomerSimpleDTO() {
    }

    public CustomerSimpleDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        lastName = customer.getLastName();
        userName = customer.getUserName();
        birthdate = customer.getBirthdate();
        gender = customer.getGender();
        rol = customer.getRol();
        isActivate = customer.isActivate();
        email = customer.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public String getEmail() {
        return email;
    }
}