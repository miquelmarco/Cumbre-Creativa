package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Comment;

import java.time.LocalDate;

public class CommentSimpleDTO {
    private Long id;
    private String text;
    private LocalDate date;

    public CommentSimpleDTO() {
    }

    public CommentSimpleDTO(Comment comment) {
        id = comment.getId();
        text = comment.getText();
        date = comment.getDate();
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
}