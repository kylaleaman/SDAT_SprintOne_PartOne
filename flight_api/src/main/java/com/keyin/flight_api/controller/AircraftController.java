package com.keyin.flight_api.controller;

import com.keyin.flight_api.model.Aircraft;
import com.keyin.flight_api.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircrafts")
public class AircraftController {
    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable Long id) {
        return aircraftService.getAircraftById(id);
    }

    @PostMapping
    public void addAircraft(@RequestBody Aircraft aircraft) {
        aircraftService.saveAircraft(aircraft);
    }

    @PutMapping("/{id}")
    public void updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft) {
        aircraft.setId(id);
        aircraftService.saveAircraft(aircraft);
    }

    @DeleteMapping("/{id}")
    public void deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
    }
}
