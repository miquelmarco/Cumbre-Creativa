package cumbrecreativa.cumbrecreativa.repositories;

import cumbrecreativa.cumbrecreativa.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByUserName(String username);
    Customer findByVerification(String verification);
    Customer findByResetPasswordToken(String resetPasswordToken);
}