package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Comment;
import cumbrecreativa.cumbrecreativa.models.Rating;

import java.time.LocalDate;

public class CommentDTO {
    private String text;
    private LocalDate date;
    private CustomerDTO userComment;
    private EventDTO eventComment;
    private RatingDTO rating;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        text = comment.getText();
        date = comment.getDate();
        userComment = new CustomerDTO(comment.getUserComment());
        eventComment = new EventDTO(comment.getEventComment());
        rating = new RatingDTO(comment.getRating());
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public CustomerDTO getUserComment() {
        return userComment;
    }

    public EventDTO getEventComment() {
        return eventComment;
    }

    public RatingDTO getRating() {
        return rating;
    }
}