package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Rating;

import java.time.LocalDate;

public class RatingSimpleDTO {
    private Long id;
    private Byte ratingTotal;
    private LocalDate date;

    public RatingSimpleDTO() {
    }
    public RatingSimpleDTO(Rating rating) {
        id = rating.getId();
        ratingTotal = rating.getRating();
        date = rating.getDate();
    }

    public Long getId() {
        return id;
    }

    public Byte getRatingTotal() {
        return ratingTotal;
    }

    public LocalDate getDate() {
        return date;
    }
}