package cumbrecreativa.cumbrecreativa.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditorDTO {
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Long eventId;
    private boolean isActivated;

    public EventEditorDTO() {
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

    public Long getEventId() {
        return eventId;
    }

    public boolean isActivated() {
        return isActivated;
    }
}