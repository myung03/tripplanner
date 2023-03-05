package persistence;

import model.Trip;
import model.Trips;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    //Codes references JsonReaderTest class from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


    @Test
    void testReaderNonExistentFileTrips() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Trips trips = reader.readTrips();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderNonExistentFileTrip() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Trip trip = reader.readTrip();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneraTrips() {
        JsonReader reader = new JsonReader("./data/testRegPastTrips.json");
        try {
            Trips trips = reader.readTrips();
            assertEquals(2, trips.getTrips().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTrip() {
        JsonReader reader = new JsonReader("./data/testRegCurrentTrip.json");
        try {
            Trip trip = reader.readTrip();
            assertEquals("matthew's trip", trip.getName());
            assertEquals("singapore", trip.getLocation());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReadableAfterChanged() {
        JsonReader reader = new JsonReader("./data/testRegCurrentTrip.json");

        try {
            Trip trip = reader.readTrip();
            assertEquals("matthew's trip", trip.getName());
            assertEquals("singapore", trip.getLocation());

            reader.changeDestination("./data/testRegPastTrips.json");
            Trips trips = reader.readTrips();
            assertEquals(2, trips.getTrips().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
