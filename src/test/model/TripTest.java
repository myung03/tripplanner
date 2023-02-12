package model;

import components.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TripTest {

    private Trip trip;

    @BeforeEach
    public void setUp() {
        trip = new Trip("Spring Break", "London",
                "2023-03-10", "2023-03-20", 1000);
    }

    @Test
    public void changeName() {
        assertEquals("Spring Break", trip.getName());
        trip.changeName("Reading Break");
        assertEquals("Reading Break", trip.getName());
    }

    @Test
    public void changeLocation() {
        assertEquals("London", trip.getLocation());
        trip.changeLocation("Paris");
        assertEquals("Paris", trip.getLocation());
    }

    @Test
    public void changeStartEnd() {
        assertEquals("2023-03-10", trip.getStartDate());
        assertEquals("2023-03-20", trip.getEndDate());

        trip.changeStartDate("2023-03-15");
        trip.changeEndDate("2023-03-25");

        assertEquals("2023-03-15", trip.getStartDate());
        assertEquals("2023-03-25", trip.getEndDate());
    }

    @Test
    public void testBudget() {
        assertEquals(1000, trip.getBudget().getBudget());
    }


    @Test
    public void testPrintTrip() {
        assertEquals("Spring Break, at London. Started on 2023-03-10 and ended on 2023-03-20.",
                trip.printTrip());
    }
}
