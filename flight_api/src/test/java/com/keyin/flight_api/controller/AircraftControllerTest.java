package com.keyin.flight_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.flight_api.model.Aircraft;
import com.keyin.flight_api.service.AircraftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AircraftController.class)
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @Autowired
    private ObjectMapper objectMapper;

    private Aircraft aircraft1;
    private Aircraft aircraft2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        aircraft1 = new Aircraft(1L, "Type1", "Airline1", 100);
        aircraft2 = new Aircraft(2L, "Type2", "Airline2", 150);
    }

    @Test
    public void testGetAllAircrafts() throws Exception {
        List<Aircraft> aircrafts = Arrays.asList(aircraft1, aircraft2);

        when(aircraftService.getAllAircrafts()).thenReturn(aircrafts);

        mockMvc.perform(get("/api/aircrafts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].type", is("Type1")))
                .andExpect(jsonPath("$[1].type", is("Type2")));

        verify(aircraftService, times(1)).getAllAircrafts();
        verifyNoMoreInteractions(aircraftService);
    }

    @Test
    public void testGetAircraftById() throws Exception {
        Long aircraftId = 1L;

        when(aircraftService.getAircraftById(aircraftId)).thenReturn(aircraft1);

        mockMvc.perform(get("/api/aircrafts/{id}", aircraftId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("Type1")))
                .andExpect(jsonPath("$.airlineName", is("Airline1")))
                .andExpect(jsonPath("$.numberOfPassengers", is(100)));

        verify(aircraftService, times(1)).getAircraftById(aircraftId);
        verifyNoMoreInteractions(aircraftService);
    }

    @Test
    public void testAddAircraft() throws Exception {
        when(aircraftService.saveAircraft(any(Aircraft.class))).thenReturn(aircraft1);

        mockMvc.perform(post("/api/aircrafts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aircraft1)))
                .andExpect(status().isOk());

        verify(aircraftService, times(1)).saveAircraft(any(Aircraft.class));
        verifyNoMoreInteractions(aircraftService);
    }

    @Test
    public void testUpdateAircraft() throws Exception {
        Long aircraftId = 1L;

        when(aircraftService.saveAircraft(any(Aircraft.class))).thenReturn(aircraft1);

        mockMvc.perform(put("/api/aircrafts/{id}", aircraftId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aircraft1)))
                .andExpect(status().isOk());

        verify(aircraftService, times(1)).saveAircraft(any(Aircraft.class));
        verifyNoMoreInteractions(aircraftService);
    }

    @Test
    public void testDeleteAircraft() throws Exception {
        Long aircraftId = 1L;

        doNothing().when(aircraftService).deleteAircraft(aircraftId);

        mockMvc.perform(delete("/api/aircrafts/{id}", aircraftId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(aircraftService, times(1)).deleteAircraft(aircraftId);
        verifyNoMoreInteractions(aircraftService);
    }
}




