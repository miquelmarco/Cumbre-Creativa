package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Rating;

import java.time.LocalDate;

public class RatingDTO {
    private Byte rating;
    private LocalDate date;
    private CustomerDTO userRating;
    private EventDTO eventRating;

    public RatingDTO() {
    }

    public RatingDTO(Rating ratingdto) {
        rating = ratingdto.getRating();
        date = ratingdto.getDate();
        userRating = new CustomerDTO(ratingdto.getUserRating());
        eventRating = new EventDTO(ratingdto.getEventRating());
    }

    public Byte getRating() {
        return rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public CustomerDTO getUserRating() {
        return userRating;
    }

    public EventDTO getEventRating() {
        return eventRating;
    }
}