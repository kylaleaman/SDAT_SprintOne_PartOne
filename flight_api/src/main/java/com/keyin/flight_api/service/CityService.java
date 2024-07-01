package com.keyin.flight_api.service;

import com.keyin.flight_api.model.City;
import com.keyin.flight_api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.orElse(null); // or handle appropriately based on your application logic
    }


    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}



