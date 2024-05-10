package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Assistance;

public class AssistanceDTO {
    private Byte companion;
    private CustomerDTO userAssistance;
    private EventDTO eventAssistance;

    public AssistanceDTO() {
    }

    public AssistanceDTO(Assistance assistance) {
        companion = assistance.getCompanion();
        userAssistance = new CustomerDTO(assistance.getUserAssistance());
        eventAssistance = new EventDTO(assistance.getEventAssistance());
    }

    public Byte getCompanion() {
        return companion;
    }

    public CustomerDTO getUserAssistance() {
        return userAssistance;
    }

    public EventDTO getEventAssistance() {
        return eventAssistance;
    }
}