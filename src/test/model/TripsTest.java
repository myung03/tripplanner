package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripsTest {

    private Trips trips;

    @BeforeEach
    public void setUp() {
        trips = new Trips();

    }

    @Test
    public void testAddTrip() {
        //add trip
        Trip test = new Trip("Spring Break", "London",
                "2023-03-10", "2023-03-20", 1000);
        Trip test2 = new Trip("Summer Break", "Paris",
                "2023-04-10", "2023-04-20", 2000);
        trips.addTrip(test);
        assertEquals(trips.tripIndex(test), 0);



        ArrayList<Trip> same = new ArrayList<Trip>();
        same.add(test);
        assertEquals(same ,trips.getTrips());

        //add another trip, ensuring they are added in correct order
        trips.addTrip(test2);
        assertEquals(trips.tripIndex(test2), 0);

    }
}
