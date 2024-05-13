package cumbrecreativa.cumbrecreativa.models;

import jakarta.persistence.*;

@Entity
public class Assistance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte companion;
    private boolean isActive;
    private boolean isCancel;
    private String confirmationCode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Customer userAssistance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event eventAssistance;

    public Assistance() {
    }

    public Assistance(Byte companion, boolean isActive, boolean isCancel) {
        this.companion = companion;
        this.isActive = isActive;
        this.isCancel = isCancel;
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

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
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