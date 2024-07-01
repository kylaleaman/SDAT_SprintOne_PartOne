import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.flight_api.controller.PassengerController;
import com.keyin.flight_api.model.Passenger;
import com.keyin.flight_api.service.PassengerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PassengerControllerTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllPassengers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
        Passenger passenger1 = new Passenger(1L, "John", "Doe", "1234567890");
        Passenger passenger2 = new Passenger(2L, "Jane", "Smith", "0987654321");

        when(passengerService.getAllPassengers()).thenReturn(Arrays.asList(passenger1, passenger2));

        mockMvc.perform(get("/api/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    void testGetPassengerById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
        Passenger passenger = new Passenger(1L, "John", "Doe", "1234567890");

        when(passengerService.getPassengerById(1L)).thenReturn(passenger);

        mockMvc.perform(get("/api/passengers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testAddPassenger() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
        Passenger newPassenger = new Passenger(null, "Alex", "Johnson", "9876543210");

        doNothing().when(passengerService).savePassenger(any(Passenger.class));

        mockMvc.perform(post("/api/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPassenger)))
                .andExpect(status().isOk());

        // You can add further assertions if needed
    }

    @Test
    void testUpdatePassenger() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
        Passenger updatedPassenger = new Passenger(1L, "Updated", "Name", "9999999999");

        doNothing().when(passengerService).savePassenger(any(Passenger.class));

        mockMvc.perform(put("/api/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPassenger)))
                .andExpect(status().isOk());

        // You can add further assertions if needed
    }

    @Test
    void testDeletePassenger() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();

        doNothing().when(passengerService).deletePassenger(1L);

        mockMvc.perform(delete("/api/passengers/1"))
                .andExpect(status().isOk());

        // You can add further assertions if needed
    }
}

