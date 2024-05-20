package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Comment;

import java.time.LocalDate;

public class CommentDTO {
    private String text;
    private LocalDate date;
    private CustomerDTO userComment;
    private EventDTO eventComment;
    private RatingDTO rating;

    public CommentDTO() {
    }

    public CommentDTO(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    public CommentDTO(Comment comment) {
        text = comment.getText();
        date = comment.getDate();
        userComment = new CustomerDTO(
                comment.getUserComment().getName(),
                comment.getUserComment().getLastName(),
                comment.getUserComment().getUserName(),
                comment.getUserComment().getBirthdate(),
                comment.getUserComment().getGender(),
                comment.getUserComment().getRol(),
                comment.getUserComment().isActivate(),
                comment.getUserComment().getEmail(),
                comment.getUserComment().getVerification());
        eventComment = new EventDTO(
                comment.getEventComment().getTitle(),
                comment.getEventComment().getOrganizer(),
                comment.getEventComment().getDescription(),
                comment.getEventComment().getImg(),
                comment.getEventComment().getDate(),
                comment.getEventComment().getTime(),
                comment.getEventComment().getRating(),
                comment.getEventComment().isActivated(),
                comment.getEventComment().isExpired());
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