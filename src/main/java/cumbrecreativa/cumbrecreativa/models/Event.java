package cumbrecreativa.cumbrecreativa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String organizer;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Byte rating;
    private boolean isActivated;
    private boolean isExpired = false;
    @OneToMany(mappedBy = "eventComment", fetch = FetchType.EAGER)
    private Set<Comment> commentSet = new HashSet<>();
    @OneToMany(mappedBy = "eventAssistance", fetch = FetchType.EAGER)
    private Set<Assistance> assistanceSet = new HashSet<>();
    @OneToMany(mappedBy = "eventRating", fetch = FetchType.EAGER)
    private Set<Rating> ratingSet = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Customer userEvent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    public Event() {
    }

    public Event(String title, String organizer, String description, LocalDate date, LocalTime time, Byte rating, boolean isActivated) {
        this.title = title;
        this.organizer = organizer;
        this.description = description;
        this.date = date;
        this.time = time;
        this.rating = rating;
        this.isActivated = isActivated;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
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
    public Set<Assistance> getAssistanceSet() {
        return assistanceSet;
    }

    @JsonIgnore
    public void setAssistanceSet(Set<Assistance> assistanceSet) {
        this.assistanceSet = assistanceSet;
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
    public Customer getUserEvent() {
        return userEvent;
    }

    @JsonIgnore
    public void setUserEvent(Customer userEvent) {
        this.userEvent = userEvent;
    }

    @JsonIgnore
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    //add methods
    public void addRating(Rating rating) {
        rating.setEventRating(this);
        ratingSet.add(rating);
    }

    public void addAssistance(Assistance assistance) {
        assistance.setEventAssistance(this);
        assistanceSet.add(assistance);
    }

    //other methods
    @Transient
    public void updateIsExpired() {
        LocalDateTime eventTime = LocalDateTime.of(date, time).plusHours(1);
        LocalDateTime now = LocalDateTime.now();
        isExpired = now.isAfter(eventTime);
    }
}