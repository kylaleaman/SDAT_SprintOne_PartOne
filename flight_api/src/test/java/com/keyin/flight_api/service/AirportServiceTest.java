package com.keyin.flight_api.service;

import com.keyin.flight_api.model.Airport;
import com.keyin.flight_api.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAirportById() {
        Long airportId = 1L;
        Airport airport = new Airport(airportId, "SFO Airport", "SFO");

        when(airportRepository.findById(airportId)).thenReturn(Optional.of(airport));

        Airport result = airportService.getAirportById(airportId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(airportId);
        assertThat(result.getName()).isEqualTo("SFO Airport");
        assertThat(result.getCode()).isEqualTo("SFO");

        verify(airportRepository, times(1)).findById(airportId);
        verifyNoMoreInteractions(airportRepository);
    }
}
