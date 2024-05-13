package cumbrecreativa.cumbrecreativa.repositories;

import cumbrecreativa.cumbrecreativa.models.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Long> {
    Assistance findByConfirmationCode(String code);
}