package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

public class EventDTO {
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
    private CustomerSimpleDTO userEvent;
    private LocationDTO location;
    private Set<CommentDTO> commentSet;
    private Set<AssistanceDTO> assistanceSet;
    private Set<RatingDTO> ratingSet;

    public EventDTO() {
    }

    public EventDTO(Event event) {
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
        userEvent = new CustomerSimpleDTO(event.getUserEvent());
        location = new LocationDTO(event.getLocation());
        commentSet = event.getCommentSet().stream().map(CommentDTO::new).collect(Collectors.toSet());
        assistanceSet = event.getAssistanceSet().stream().map(AssistanceDTO::new).collect(Collectors.toSet());
        ratingSet = event.getRatingSet().stream().map(RatingDTO::new).collect(Collectors.toSet());
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

    public CustomerSimpleDTO getUserEvent() {
        return userEvent;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public Set<CommentDTO> getCommentSet() {
        return commentSet;
    }

    public Set<AssistanceDTO> getAssistanceSet() {
        return assistanceSet;
    }

    public Set<RatingDTO> getRatingSet() {
        return ratingSet;
    }
}