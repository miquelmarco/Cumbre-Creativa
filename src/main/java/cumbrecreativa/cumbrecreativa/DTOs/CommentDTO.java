package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Comment;

import java.time.LocalDate;

public class CommentDTO {
    private String text;
    private LocalDate date;
    private CustomerDTO userComment;
    private EventDTO eventComment;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        text = comment.getText();
        date = comment.getDate();
        userComment = new CustomerDTO(comment.getUserComment());
        eventComment = new EventDTO(comment.getEventComment());
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
}