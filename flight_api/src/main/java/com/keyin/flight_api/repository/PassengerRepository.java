package com.keyin.flight_api.repository;

import com.keyin.flight_api.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepository {
    private final List<Passenger> passengers = new ArrayList<>();
    private Long nextId = 1L;

    public List<Passenger> findAll() {
        return passengers;
    }

    public Optional<Passenger> findById(Long id) {
        return passengers.stream()
                .filter(passenger -> passenger.getId().equals(id))
                .findFirst();
    }

    public Passenger save(Passenger passenger) {
        if (passenger.getId() == null) {
            passenger.setId(nextId++);
            passengers.add(passenger);
        } else {
            passengers.removeIf(p -> p.getId().equals(passenger.getId()));
            passengers.add(passenger);
        }
        return passenger; // Ensure the saved passenger is returned
    }

    public void deleteById(Long id) {
        passengers.removeIf(passenger -> passenger.getId().equals(id));
    }
}
