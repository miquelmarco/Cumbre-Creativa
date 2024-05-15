package cumbrecreativa.cumbrecreativa.services.implement;

import cumbrecreativa.cumbrecreativa.models.Assistance;
import cumbrecreativa.cumbrecreativa.repositories.AssistanceRepository;
import cumbrecreativa.cumbrecreativa.services.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanceServiceImplement implements AssistanceService {
    @Autowired
    private AssistanceRepository assistanceRepository;

    @Override
    public void save(Assistance assistance) {
        assistanceRepository.save(assistance);
    }

    @Override
    public Assistance findById(Long id) {
        return assistanceRepository.findById(id).orElse(null);
    }

    @Override
    public Assistance findByConfirmationCode(String code) {
        return assistanceRepository.findByConfirmationCode(code);
    }
}