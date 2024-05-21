package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventSimpleDTO {
    private Long id;
    private String title;
    private String organizer;
    private String description;
    private String img;
    private LocalDate date;
    private LocalTime time;
    private Byte rating;
    private boolean isActivated;
    private boolean isExpired;

    public EventSimpleDTO() {
    }

    public EventSimpleDTO(Event event) {
        id = event.getId();
        title = event.getTitle();
        organizer = event.getOrganizer();
        description = event.getDescription();
        img = event.getImg();
        date = event.getDate();
        time = event.getTime();
        rating = event.getRating();
        isActivated = event.isActivated();
        isExpired = event.isExpired();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Byte getRating() {
        return rating;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public boolean isExpired() {
        return isExpired;
    }
}