package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Rating;

import java.time.LocalDate;

public class RatingDTO {
    private Long id;
    private Byte rating;
    private LocalDate date;
    private CustomerSimpleDTO userRating;
    private EventSimpleDTO eventRating;
    private CommentSimpleDTO comment;

    public RatingDTO() {
    }

    public RatingDTO(Rating ratingdto) {
        id = ratingdto.getId();
        rating = ratingdto.getRating();
        date = ratingdto.getDate();
        userRating = new CustomerSimpleDTO(ratingdto.getUserRating());
        eventRating = new EventSimpleDTO(ratingdto.getEventRating());
        comment = new CommentSimpleDTO(ratingdto.getComment());
    }

    public Long getId() {
        return id;
    }

    public Byte getRating() {
        return rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public CustomerSimpleDTO getUserRating() {
        return userRating;
    }

    public EventSimpleDTO getEventRating() {
        return eventRating;
    }

    public CommentSimpleDTO getComment() {
        return comment;
    }
}