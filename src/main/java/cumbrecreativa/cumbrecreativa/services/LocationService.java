package cumbrecreativa.cumbrecreativa.services;

import cumbrecreativa.cumbrecreativa.models.Location;

public interface LocationService {
    void save(Location location);
    Location findById(Long id);
}