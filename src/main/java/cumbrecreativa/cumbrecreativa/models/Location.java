package cumbrecreativa.cumbrecreativa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String gps;
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private Set<Event> eventsSet = new HashSet<>();

    public Location() {
    }

    public Location(String name, String address, String city, String country, String gps) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.gps = gps;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @JsonIgnore
    public Set<Event> getEventsSet() {
        return eventsSet;
    }

    @JsonIgnore
    public void setEventsSet(Set<Event> eventsSet) {
        this.eventsSet = eventsSet;
    }

    //add methods
    public void addEvent(Event event) {
        event.setLocation(this);
        eventsSet.add(event);
    }
}