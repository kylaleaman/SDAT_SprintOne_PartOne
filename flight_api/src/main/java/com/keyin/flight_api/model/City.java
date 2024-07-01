package com.keyin.flight_api.model;

import java.util.ArrayList;
import java.util.List;

public class City {
    private Long id;
    private String name;
    private String state;
    private int population;
    private List<Airport> airports;

    // Constructors
    public City() {
        this.airports = new ArrayList<>();
    }

    public City(Long id, String name, String state, int population) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.population = population;
        this.airports = new ArrayList<>();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
}