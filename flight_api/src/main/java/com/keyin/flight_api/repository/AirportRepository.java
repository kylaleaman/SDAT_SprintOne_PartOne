package com.keyin.flight_api.repository;

import com.keyin.flight_api.model.Airport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AirportRepository {
    private final List<Airport> airports = new ArrayList<>();
    private Long nextId = 1L;

    public List<Airport> findAll() {
        return airports;
    }

    public Optional<Airport> findById(Long id) {
        return airports.stream()
                .filter(airport -> airport.getId().equals(id))
                .findFirst();
    }


    public Airport save(Airport airport) {
        if (airport.getId() == null) {
            airport.setId(nextId++);
            airports.add(airport);
        } else {
            airports.removeIf(a -> a.getId().equals(airport.getId()));
            airports.add(airport);
        }
        return airport;
    }

    public void deleteById(Long id) {
        airports.removeIf(airport -> airport.getId().equals(id));
    }
}

