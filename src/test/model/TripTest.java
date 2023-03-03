package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


//tests for Trip class
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
        trip.changeBudget(500.00);
        assertEquals(500, trip.getBudget().getBudget());

    }


    @Test
    public void testPrintTrip() {
        assertEquals("Spring Break, at London. Started on 2023-03-10 and ended on 2023-03-20.",
                trip.printTrip());
    }
}

//how to write tests for JSON classes?
//how to make "fake data" for the test cases?

//since in my TripApp, I have a current trip and a list of past trips,
// how would I parse both into Java? Since it's two separate classes and not
// just a single class with nested objects inside
//alernatively would it be easier to just put "current trip" into the trips class?


//budget -> trip -> trips, do I have to have a JSON reader for EACH item if I have another class nested? ex.
//budget inside trips is another class