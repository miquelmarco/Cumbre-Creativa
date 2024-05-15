package cumbrecreativa.cumbrecreativa.services;

import cumbrecreativa.cumbrecreativa.models.Assistance;

public interface AssistanceService {
    void save(Assistance assistance);
    Assistance findById(Long id);
    Assistance findByConfirmationCode(String code);
}