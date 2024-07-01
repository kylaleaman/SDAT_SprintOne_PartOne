package com.keyin.flight_api.repository;

import com.keyin.flight_api.model.City;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository {
    private final List<City> cities = new ArrayList<>();
    private Long nextId = 1L;

    public List<City> findAll() {
        return cities;
    }

    public Optional<City> findById(Long id) {
        return cities.stream()
                .filter(city -> city.getId().equals(id))
                .findFirst();
    }


    public void save(City city) {
        if (city.getId() == null) {
            city.setId(nextId++);
            cities.add(city);
        } else {
            cities.removeIf(c -> c.getId().equals(city.getId()));
            cities.add(city);
        }
    }

    public void deleteById(Long id) {
        cities.removeIf(city -> city.getId().equals(id));
    }
}
