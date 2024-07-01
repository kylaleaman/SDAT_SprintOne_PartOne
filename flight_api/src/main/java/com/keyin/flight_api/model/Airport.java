package com.keyin.flight_api.model;

import java.util.HashSet;
import java.util.Set;

public class Airport {
    private Long id;
    private String name;
    private String code;
    private City city;
    private Set<Aircraft> aircrafts;

    // Constructors
    public Airport() {
        this.aircrafts = new HashSet<>();
    }

    public Airport(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.aircrafts = new HashSet<>();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(Set<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }
}
