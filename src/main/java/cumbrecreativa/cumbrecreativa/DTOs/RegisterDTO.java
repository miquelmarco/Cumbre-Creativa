package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Gender;
import cumbrecreativa.cumbrecreativa.models.Rol;

import java.time.LocalDate;

public class RegisterDTO {
    private String name;
    private String lastName;
    private String userName;
    private LocalDate birthdate;
    private Gender gender;
    private Rol rol;
    private String email;
    private String password;

    public RegisterDTO() {
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
