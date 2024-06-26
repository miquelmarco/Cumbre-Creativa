package cumbrecreativa.cumbrecreativa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String userName;
    private LocalDate birthdate;
    private Gender gender;
    private Rol rol;
    private boolean isActivate;
    private String verification;
    private String resetPasswordToken;
    private LocalDateTime resetPasswordExpiration;
    private String email;
    private String password;
    @OneToMany(mappedBy = "userEvent", fetch = FetchType.EAGER)
    private Set<Event> eventSet = new HashSet<>();
    @OneToMany(mappedBy = "userComment", fetch = FetchType.EAGER)
    private Set<Comment> commentSet = new HashSet<>();
    @OneToMany(mappedBy = "userRating", fetch = FetchType.EAGER)
    private Set<Rating> ratingSet = new HashSet<>();
    @OneToMany(mappedBy = "userAssistance", fetch = FetchType.EAGER)
    private Set<Assistance> assistanceSet = new HashSet<>();

    public Customer() {
    }

    public Customer(String name, String lastName, String userName, LocalDate birthdate, Gender gender, Rol rol, String verification, boolean isActivate, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.rol = rol;
        this.isActivate = isActivate;
        this.verification = verification;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public LocalDateTime getResetPasswordExpiration() {
        return resetPasswordExpiration;
    }

    public void setResetPasswordExpiration(LocalDateTime resetPasswordExpiration) {
        this.resetPasswordExpiration = resetPasswordExpiration;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Set<Event> getEventSet() {
        return eventSet;
    }

    @JsonIgnore
    public void setEventSet(Set<Event> eventSet) {
        this.eventSet = eventSet;
    }

    @JsonIgnore
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    @JsonIgnore
    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @JsonIgnore
    public Set<Rating> getRatingSet() {
        return ratingSet;
    }

    @JsonIgnore
    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
    }

    @JsonIgnore
    public Set<Assistance> getAssistanceSet() {
        return assistanceSet;
    }

    @JsonIgnore
    public void setAssistanceSet(Set<Assistance> assistanceSet) {
        this.assistanceSet = assistanceSet;
    }

    //add methods
    public void addRating(Rating rating) {
        rating.setUserRating(this);
        ratingSet.add(rating);
    }

    public void addEvent(Event event) {
        event.setUserEvent(this);
        eventSet.add(event);
    }

    public void addAssistance(Assistance assistance) {
        assistance.setUserAssistance(this);
        assistanceSet.add(assistance);
    }
}