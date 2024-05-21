package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Comment;

import java.time.LocalDate;

public class CommentDTO {
    private Long id;
    private String text;
    private LocalDate date;
    private CustomerSimpleDTO userComment;
    private EventSimpleDTO eventComment;
    private RatingSimpleDTO rating;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        id = comment.getId();
        text = comment.getText();
        date = comment.getDate();
        userComment = new CustomerSimpleDTO(comment.getUserComment());
        eventComment = new EventSimpleDTO(comment.getEventComment());
        rating = new RatingSimpleDTO(comment.getRating());
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public CustomerSimpleDTO getUserComment() {
        return userComment;
    }

    public EventSimpleDTO getEventComment() {
        return eventComment;
    }

    public RatingSimpleDTO getRating() {
        return rating;
    }
}