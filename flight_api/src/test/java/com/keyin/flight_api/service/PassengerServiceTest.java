import com.keyin.flight_api.model.Passenger;
import com.keyin.flight_api.repository.PassengerRepository;
import com.keyin.flight_api.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService passengerService;

    private Passenger passenger1;
    private Passenger passenger2;

    @BeforeEach
    void setUp() {
        passenger1 = new Passenger(1L, "John", "Doe", "1234567890");
        passenger2 = new Passenger(2L, "Jane", "Smith", "0987654321");
    }

    @Test
    void testGetPassengerById() {
        // Mocking findById method of passengerRepository
        when(passengerRepository.findById(anyLong())).thenReturn(Optional.of(passenger1));

        // Calling the service method that uses findById
        Passenger result = passengerService.getPassengerById(1L);

        // Asserting the expected result
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testSavePassenger() {
        // Mocking save method of passengerRepository
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger2);

        // Calling the service method that uses save
        Passenger savedPassenger = passengerService.savePassenger(passenger2);

        // Verifying the save method is called once
        verify(passengerRepository, times(1)).save(any(Passenger.class));

        // Asserting the saved passenger's details
        assertEquals("Jane", savedPassenger.getFirstName());
    }

    @Test
    void testDeletePassenger() {
        // Calling the service method that uses deleteById
        passengerService.deletePassenger(1L);

        // Verifying the deleteById method is called once with correct argument
        verify(passengerRepository, times(1)).deleteById(1L);
    }

    // Add more test cases as needed

}
