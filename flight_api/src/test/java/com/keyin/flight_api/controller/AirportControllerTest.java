package com.keyin.flight_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.flight_api.model.Airport;
import com.keyin.flight_api.service.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }

    @Test
    public void testGetAllAirports() throws Exception {
        Airport airport1 = new Airport(1L, "JFK Airport", "JFK");
        Airport airport2 = new Airport(2L, "LAX Airport", "LAX");
        List<Airport> airports = Arrays.asList(airport1, airport2);

        when(airportService.getAllAirports()).thenReturn(airports);

        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("JFK Airport"))
                .andExpect(jsonPath("$[0].code").value("JFK"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("LAX Airport"))
                .andExpect(jsonPath("$[1].code").value("LAX"));

        verify(airportService, times(1)).getAllAirports();
        verifyNoMoreInteractions(airportService);
    }

    // Add other test methods for getAirportById, addAirport, updateAirport, deleteAirport
}
