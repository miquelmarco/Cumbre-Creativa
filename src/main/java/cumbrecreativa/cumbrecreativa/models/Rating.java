package cumbrecreativa.cumbrecreativa.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte rating;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Customer userRating;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event eventRating;

    public Rating() {
    }

    public Rating(Byte rating, LocalDate date, Customer userRating, Event eventRating) {
        this.rating = rating;
        this.date = date;
        this.userRating = userRating;
        this.eventRating = eventRating;
    }

    public Long getId() {
        return id;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getUserRating() {
        return userRating;
    }

    public void setUserRating(Customer userRating) {
        this.userRating = userRating;
    }

    public Event getEventRating() {
        return eventRating;
    }

    public void setEventRating(Event eventRating) {
        this.eventRating = eventRating;
    }
}