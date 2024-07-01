package com.keyin.flight_api.service;

import com.keyin.flight_api.model.Airport;
import com.keyin.flight_api.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        return optionalAirport.orElse(null); // Return null if Airport is not found, handle accordingly
    }

    public Airport saveAirport(Airport airport) {
        airportRepository.save(airport);
        return airport;
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}

