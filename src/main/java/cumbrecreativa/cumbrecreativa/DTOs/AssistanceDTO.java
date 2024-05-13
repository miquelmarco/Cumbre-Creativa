package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Assistance;

public class AssistanceDTO {
    private Byte companion;
    private boolean isActive;
    private boolean isCancel;
    private String confirmationCode;
    private CustomerDTO userAssistance;
    private EventDTO eventAssistance;

    public AssistanceDTO() {
    }

    public AssistanceDTO(Assistance assistance) {
        companion = assistance.getCompanion();
        isActive = assistance.isActive();
        isCancel = assistance.isCancel();
        confirmationCode = assistance.getConfirmationCode();
        userAssistance = new CustomerDTO(assistance.getUserAssistance());
        eventAssistance = new EventDTO(assistance.getEventAssistance());
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

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public CustomerDTO getUserAssistance() {
        return userAssistance;
    }

    public EventDTO getEventAssistance() {
        return eventAssistance;
    }
}