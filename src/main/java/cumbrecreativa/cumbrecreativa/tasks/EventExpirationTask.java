package cumbrecreativa.cumbrecreativa.tasks;

import cumbrecreativa.cumbrecreativa.models.Event;
import cumbrecreativa.cumbrecreativa.repositories.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventExpirationTask {
    private final EventRepository eventRepository;

    public EventExpirationTask(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Scheduled(fixedRate = 3600000)
    public void updateExpiredEvents() {
        Iterable<Event> events = eventRepository.findAll();
        for (Event event : events) {
            event.updateIsExpired();
            eventRepository.save(event);
        }
    }
}