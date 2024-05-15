package cumbrecreativa.cumbrecreativa.services;

import cumbrecreativa.cumbrecreativa.DTOs.EventDTO;
import cumbrecreativa.cumbrecreativa.models.Event;

import java.util.Set;

public interface EventService {
    void save(Event event);
    boolean existById(Long id);
    Event findById(Long id);
    Set<EventDTO> findAll();
}