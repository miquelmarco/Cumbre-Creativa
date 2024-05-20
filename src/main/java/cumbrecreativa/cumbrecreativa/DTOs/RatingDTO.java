package cumbrecreativa.cumbrecreativa.DTOs;

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
        userRating = new CustomerDTO(
                ratingdto.getUserRating().getName(),
                ratingdto.getUserRating().getLastName(),
                ratingdto.getUserRating().getUserName(),
                ratingdto.getUserRating().getBirthdate(),
                ratingdto.getUserRating().getGender(),
                ratingdto.getUserRating().getRol(),
                ratingdto.getUserRating().isActivate(),
                ratingdto.getUserRating().getEmail(),
                ratingdto.getUserRating().getVerification());
        eventRating = new EventDTO(
                ratingdto.getEventRating().getTitle(),
                ratingdto.getEventRating().getOrganizer(),
                ratingdto.getEventRating().getDescription(),
                ratingdto.getEventRating().getImg(),
                ratingdto.getEventRating().getDate(),
                ratingdto.getEventRating().getTime(),
                ratingdto.getEventRating().getRating(),
                ratingdto.getEventRating().isActivated(),
                ratingdto.getEventRating().isExpired());
        comment = new CommentDTO(
                ratingdto.getComment().getText(),
                ratingdto.getComment().getDate());
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

    public CommentDTO getComment() {
        return comment;
    }
}