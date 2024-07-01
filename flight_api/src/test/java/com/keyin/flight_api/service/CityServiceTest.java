package com.keyin.flight_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.keyin.flight_api.model.City;
import com.keyin.flight_api.repository.CityRepository;
import com.keyin.flight_api.service.CityService;

public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCityById() {
        Long cityId = 1L;
        City city = new City(cityId, "Chicago", "Illinois", 2716000);

        // Mock behavior of cityRepository.findById(cityId)
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        // Call the method under test
        City result = cityService.getCityById(cityId);

        // Assert the result
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(cityId);
        assertThat(result.getName()).isEqualTo("Chicago");
        assertThat(result.getState()).isEqualTo("Illinois");
        assertThat(result.getPopulation()).isEqualTo(2716000);

        // Verify interactions with mocks
        verify(cityRepository, times(1)).findById(cityId);
        verifyNoMoreInteractions(cityRepository);
    }
}


