package com.keyin.flight_api.controller;

import com.keyin.flight_api.model.City;
import com.keyin.flight_api.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCities() {
        City city1 = new City(1L, "New York City", "New York", 8623000);
        City city2 = new City(2L, "Los Angeles", "California", 3990000);
        List<City> cities = Arrays.asList(city1, city2);

        when(cityService.getAllCities()).thenReturn(cities);

        List<City> result = cityController.getAllCities();

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("New York City");
        assertThat(result.get(1).getName()).isEqualTo("Los Angeles");

        verify(cityService, times(1)).getAllCities();
        verifyNoMoreInteractions(cityService);
    }

    @Test
    public void testGetCityById() {
        Long cityId = 1L;
        City city = new City(cityId, "Chicago", "Illinois", 2716000);

        when(cityService.getCityById(cityId)).thenReturn(city);

        City result = cityController.getCityById(cityId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(cityId);
        assertThat(result.getName()).isEqualTo("Chicago");
        assertThat(result.getState()).isEqualTo("Illinois");

        verify(cityService, times(1)).getCityById(cityId);
        verifyNoMoreInteractions(cityService);
    }

    @Test
    public void testAddCity() {
        City city = new City(null, "Houston", "Texas", 2320000);

        cityController.addCity(city);

        verify(cityService, times(1)).saveCity(any(City.class));
        verifyNoMoreInteractions(cityService);
    }

    @Test
    public void testUpdateCity() {
        Long cityId = 1L;
        City city = new City(cityId, "Denver", "Colorado", 716500);

        cityController.updateCity(cityId, city);

        verify(cityService, times(1)).saveCity(any(City.class));
        verifyNoMoreInteractions(cityService);
    }

    @Test
    public void testDeleteCity() {
        Long cityId = 1L;

        cityController.deleteCity(cityId);

        verify(cityService, times(1)).deleteCity(cityId);
        verifyNoMoreInteractions(cityService);
    }
}
