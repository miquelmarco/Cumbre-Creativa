package cumbrecreativa.cumbrecreativa.models;

import jakarta.persistence.*;

@Entity
public class Assistance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte companion;
    private boolean isActive;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Customer userAssistance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event eventAssistance;

    public Assistance() {
    }

    public Assistance(Byte companion, boolean isActive) {
        this.companion = companion;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public Byte getCompanion() {
        return companion;
    }

    public void setCompanion(Byte companion) {
        this.companion = companion;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Customer getUserAssistance() {
        return userAssistance;
    }

    public void setUserAssistance(Customer userAssistance) {
        this.userAssistance = userAssistance;
    }

    public Event getEventAssistance() {
        return eventAssistance;
    }

    public void setEventAssistance(Event eventAssistance) {
        this.eventAssistance = eventAssistance;
    }
}