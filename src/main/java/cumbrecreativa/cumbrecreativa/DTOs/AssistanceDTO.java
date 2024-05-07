package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Assistance;

public class AssistanceDTO {
    private CustomerDTO userAssistance;
    private EventDTO eventAssistance;

    public AssistanceDTO() {
    }

    public AssistanceDTO(Assistance assistance) {
        userAssistance = new CustomerDTO(assistance.getUserAssistance());
        eventAssistance = new EventDTO(assistance.getEventAssistance());
    }

    public CustomerDTO getUserAssistance() {
        return userAssistance;
    }

    public EventDTO getEventAssistance() {
        return eventAssistance;
    }
}