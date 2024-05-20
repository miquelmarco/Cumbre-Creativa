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
        userAssistance = new CustomerDTO(
                assistance.getUserAssistance().getName(),
                assistance.getUserAssistance().getLastName(),
                assistance.getUserAssistance().getUserName(),
                assistance.getUserAssistance().getBirthdate(),
                assistance.getUserAssistance().getGender(),
                assistance.getUserAssistance().getRol(),
                assistance.getUserAssistance().isActivate(),
                assistance.getUserAssistance().getEmail(),
                assistance.getUserAssistance().getVerification());
        eventAssistance = new EventDTO(
                assistance.getEventAssistance().getTitle(),
                assistance.getEventAssistance().getOrganizer(),
                assistance.getEventAssistance().getDescription(),
                assistance.getEventAssistance().getDate(),
                assistance.getEventAssistance().getTime(),
                assistance.getEventAssistance().getRating(),
                assistance.getEventAssistance().isActivated(),
                assistance.getEventAssistance().isExpired());
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
