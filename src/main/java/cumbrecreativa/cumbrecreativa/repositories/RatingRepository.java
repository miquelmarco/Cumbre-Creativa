package cumbrecreativa.cumbrecreativa.repositories;

import cumbrecreativa.cumbrecreativa.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}