package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Assistance;

public class AssistanceDTO {
    private Long id;
    private Byte companion;
    private boolean isActive;
    private boolean isCancel;
    private CustomerSimpleDTO userAssistance;
    private EventSimpleDTO eventAssistance;

    public AssistanceDTO() {
    }

    public AssistanceDTO(Assistance assistance) {
        id = assistance.getId();
        companion = assistance.getCompanion();
        isActive = assistance.isActive();
        isCancel = assistance.isCancel();
        userAssistance = new CustomerSimpleDTO(assistance.getUserAssistance());
        eventAssistance = new EventSimpleDTO(assistance.getEventAssistance());
    }

    public Long getId() {
        return id;
    }

    public Byte getCompanion() {
        return companion;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public CustomerSimpleDTO getUserAssistance() {
        return userAssistance;
    }

    public EventSimpleDTO getEventAssistance() {
        return eventAssistance;
    }
}