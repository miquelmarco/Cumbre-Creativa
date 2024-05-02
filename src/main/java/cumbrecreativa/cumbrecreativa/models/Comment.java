package cumbrecreativa.cumbrecreativa.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Customer userComment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event eventComment;
    public Comment() {
    }

    public Comment(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getUserComment() {
        return userComment;
    }

    public void setUserComment(Customer userComment) {
        this.userComment = userComment;
    }

    public Event getEventComment() {
        return eventComment;
    }

    public void setEventComment(Event eventComment) {
        this.eventComment = eventComment;
    }
}