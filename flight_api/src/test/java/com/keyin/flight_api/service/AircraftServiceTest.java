package com.keyin.flight_api.service;

import com.keyin.flight_api.model.Aircraft;
import com.keyin.flight_api.repository.AircraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService aircraftService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAircrafts() {
        Aircraft aircraft1 = new Aircraft(1L, "Type1", "Airline1", 100);
        Aircraft aircraft2 = new Aircraft(2L, "Type2", "Airline2", 150);
        List<Aircraft> aircrafts = Arrays.asList(aircraft1, aircraft2);

        when(aircraftRepository.findAll()).thenReturn(aircrafts);

        List<Aircraft> result = aircraftService.getAllAircrafts();

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getType()).isEqualTo("Type1");
        assertThat(result.get(1).getType()).isEqualTo("Type2");

        verify(aircraftRepository, times(1)).findAll();
        verifyNoMoreInteractions(aircraftRepository);
    }

    @Test
    public void testGetAircraftById() {
        Long aircraftId = 1L;
        Aircraft aircraft = new Aircraft(aircraftId, "Type1", "Airline1", 100);

        when(aircraftRepository.findById(aircraftId)).thenReturn(aircraft);

        Aircraft result = aircraftService.getAircraftById(aircraftId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(aircraftId);
        assertThat(result.getType()).isEqualTo("Type1");
        assertThat(result.getAirlineName()).isEqualTo("Airline1");
        assertThat(result.getNumberOfPassengers()).isEqualTo(100);

        verify(aircraftRepository, times(1)).findById(aircraftId);
        verifyNoMoreInteractions(aircraftRepository);
    }

    @Test
    public void testSaveAircraft() {
        Aircraft aircraft = new Aircraft(null, "Type1", "Airline1", 100);

        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(aircraft);

        Aircraft result = aircraftService.saveAircraft(aircraft);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("Type1");
        assertThat(result.getAirlineName()).isEqualTo("Airline1");
        assertThat(result.getNumberOfPassengers()).isEqualTo(100);

        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
        verifyNoMoreInteractions(aircraftRepository);
    }

    @Test
    public void testDeleteAircraft() {
        Long aircraftId = 1L;

        doNothing().when(aircraftRepository).deleteById(aircraftId);

        aircraftService.deleteAircraft(aircraftId);

        verify(aircraftRepository, times(1)).deleteById(aircraftId);
        verifyNoMoreInteractions(aircraftRepository);
    }
}
