package cumbrecreativa.cumbrecreativa.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cumbrecreativa.cumbrecreativa.models.Rating;

import java.time.LocalDate;

public class RatingDTO {
    private Byte rating;
    private LocalDate date;
    private CustomerDTO userRating;
    private EventDTO eventRating;
    private CommentDTO comment;

    public RatingDTO() {
    }

    public RatingDTO(Rating ratingdto) {
        rating = ratingdto.getRating();
        date = ratingdto.getDate();
        userRating = new CustomerDTO(ratingdto.getUserRating());
        eventRating = new EventDTO(ratingdto.getEventRating());
        comment = new CommentDTO(ratingdto.getComment());
    }

    public Byte getRating() {
        return rating;
    }

    public LocalDate getDate() {
        return date;
    }

    @JsonIgnore
    public CustomerDTO getUserRating() {
        return userRating;
    }

    @JsonIgnore
    public EventDTO getEventRating() {
        return eventRating;
    }

    @JsonIgnore
    public CommentDTO getComment() {
        return comment;
    }
}