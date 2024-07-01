package com.keyin.flight_api.service;

import com.keyin.flight_api.model.Passenger;
import com.keyin.flight_api.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long id) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(id);
        return passengerOptional.orElse(null); // Or throw an exception if needed
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
