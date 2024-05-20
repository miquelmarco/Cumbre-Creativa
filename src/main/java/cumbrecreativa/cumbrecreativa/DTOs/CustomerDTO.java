package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerDTO {
    private String name;
    private String lastName;
    private String userName;
    private LocalDate birthdate;
    private Gender gender;
    private Rol rol;
    private boolean isActivate;
    private String email;
    private String verification;
    private Set<EventDTO> eventSet;
    private Set<CommentDTO> commentSet;
    private Set<RatingDTO> ratingSet;
    private Set<AssistanceDTO> assistanceSet;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String lastName, String userName, LocalDate birthdate,
                       Gender gender, Rol rol, boolean isActivate, String email, String verification) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.rol = rol;
        this.isActivate = isActivate;
        this.email = email;
        this.verification = verification;
    }

    public CustomerDTO(Customer customer) {
        name = customer.getName();
        lastName = customer.getLastName();
        userName = customer.getUserName();
        birthdate = customer.getBirthdate();
        gender = customer.getGender();
        rol = customer.getRol();
        isActivate = customer.isActivate();
        email = customer.getEmail();
        verification = customer.getVerification();
        eventSet = customer.getEventSet().stream().map(EventDTO::new).collect(Collectors.toSet());
        commentSet = customer.getCommentSet().stream().map(CommentDTO::new).collect(Collectors.toSet());
        ratingSet = customer.getRatingSet().stream().map(RatingDTO::new).collect(Collectors.toSet());
        assistanceSet = customer.getAssistanceSet().stream().map(AssistanceDTO::new).collect(Collectors.toSet());
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

    public String getVerification() {
        return verification;
    }

    public Set<EventDTO> getEventSet() {
        return eventSet;
    }

    public Set<CommentDTO> getCommentSet() {
        return commentSet;
    }

    public Set<RatingDTO> getRatingSet() {
        return ratingSet;
    }

    public Set<AssistanceDTO> getAssistanceSet() {
        return assistanceSet;
    }
}