package com.keyin.flight_api.controller;

import com.keyin.flight_api.model.City;
import com.keyin.flight_api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        List<City> cities = cityService.getAllCities();
        if (cities.isEmpty()) {
            return Collections.emptyList();
        }
        return cities;
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    public void addCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @PutMapping("/{id}")
    public void updateCity(@PathVariable Long id, @RequestBody City city) {
        city.setId(id);
        cityService.saveCity(city);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
