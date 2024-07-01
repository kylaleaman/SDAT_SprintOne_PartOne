package com.keyin.flight_api.controller;

import com.keyin.flight_api.model.Airport;
import com.keyin.flight_api.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @PostMapping
    public void addAirport(@RequestBody Airport airport) {
        airportService.saveAirport(airport);
    }

    @PutMapping("/{id}")
    public void updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        airport.setId(id);
        airportService.saveAirport(airport);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }
}

