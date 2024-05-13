package cumbrecreativa.cumbrecreativa.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCreatorDTO {
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Long locationId;

    public EventCreatorDTO() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Long getLocationId() {
        return locationId;
    }
}