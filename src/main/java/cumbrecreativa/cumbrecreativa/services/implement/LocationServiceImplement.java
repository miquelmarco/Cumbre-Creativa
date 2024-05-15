package cumbrecreativa.cumbrecreativa.services.implement;

import cumbrecreativa.cumbrecreativa.models.Location;
import cumbrecreativa.cumbrecreativa.repositories.LocationRepository;
import cumbrecreativa.cumbrecreativa.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImplement implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Override
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }
}