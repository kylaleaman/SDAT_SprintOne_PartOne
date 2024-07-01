package com.keyin.flight_api.repository;

import com.keyin.flight_api.model.Aircraft;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AircraftRepository {
    private final List<Aircraft> aircrafts = new ArrayList<>();
    private Long nextId = 1L;

    public List<Aircraft> findAll() {
        return aircrafts;
    }

    public Aircraft findById(Long id) {
        Optional<Aircraft> aircraftOptional = aircrafts.stream()
                .filter(aircraft -> aircraft.getId().equals(id))
                .findFirst();
        return aircraftOptional.orElse(null);
    }

    public Aircraft save(Aircraft aircraft) {
        if (aircraft.getId() == null) {
            aircraft.setId(nextId++);
            aircrafts.add(aircraft);
        } else {
            aircrafts.removeIf(a -> a.getId().equals(aircraft.getId()));
            aircrafts.add(aircraft);
        }
        return aircraft;  // Return the saved aircraft
    }

    public void deleteById(Long id) {
        aircrafts.removeIf(aircraft -> aircraft.getId().equals(id));
    }
}

