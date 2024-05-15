package cumbrecreativa.cumbrecreativa.services.implement;

import cumbrecreativa.cumbrecreativa.models.Rating;
import cumbrecreativa.cumbrecreativa.repositories.RatingRepository;
import cumbrecreativa.cumbrecreativa.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImplement implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }
}