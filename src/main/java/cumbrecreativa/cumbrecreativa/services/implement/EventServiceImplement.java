package cumbrecreativa.cumbrecreativa.services.implement;

import cumbrecreativa.cumbrecreativa.DTOs.EventDTO;
import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.repositories.EventRepository;
import cumbrecreativa.cumbrecreativa.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class EventServiceImplement implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public boolean existById(Long id) {
        return eventRepository.existsById(id);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Set<EventDTO> findAll() {
        return eventRepository.findAll().stream().filter(Event::isActivated).map(EventDTO::new).collect(Collectors.toSet());
    }
}