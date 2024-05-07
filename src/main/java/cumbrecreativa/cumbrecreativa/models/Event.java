package cumbrecreativa.cumbrecreativa.models;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    public Event(String title, String organizer, String description, LocalDate date, LocalTime time, Byte rating) {
        this.title = title;
        this.organizer = organizer;
        this.description = description;
        this.date = date;
        this.time = time;
        this.rating = rating;
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

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public Set<Assistance> getAssistanceSet() {
        return assistanceSet;
    }

    public void setAssistanceSet(Set<Assistance> assistanceSet) {
        this.assistanceSet = assistanceSet;
    }

    public Set<Rating> getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
    }

    public Customer getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(Customer userEvent) {
        this.userEvent = userEvent;
    }

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
}