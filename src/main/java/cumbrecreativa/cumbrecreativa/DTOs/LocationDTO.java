package cumbrecreativa.cumbrecreativa.DTOs;

import cumbrecreativa.cumbrecreativa.models.Location;

import java.util.Set;
import java.util.stream.Collectors;

public class LocationDTO {
    private String name;
    private String address;
    private String city;
    private String country;
    private String gps;
    private Set<EventDTO> eventsSet;

    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        name = location.getName();
        address = location.getAddress();
        city = location.getCity();
        country = location.getCountry();
        gps = location.getGps();
        eventsSet = location.getEventsSet().stream().map(EventDTO::new).collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getGps() {
        return gps;
    }

    public Set<EventDTO> getEventsSet() {
        return eventsSet;
    }
}