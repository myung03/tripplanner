package persistence;

import model.Trip;
import model.Trips;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    //Codes references JsonWriterTest class from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNoTrips() {
        try {
            Trips trips = new Trips();
            JsonWriter writer = new JsonWriter("./data/testNoPastTrips.json");
            writer.open();
            writer.writeTrips(trips);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNoPastTrips.json");
            trips = reader.readTrips();
            assertEquals(0, trips.getTrips().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRegularTrip() {
        try {
            Trip trip = new Trip("matthew", "HK", "2024", "2025", 1000);
            JsonWriter writer = new JsonWriter("./data/testRegCurrentTrip.json");
            writer.open();
            writer.writeTrip(trip);
            writer.close();

            JsonReader reader = new JsonReader("./data/testRegCurrentTrip.json");
            trip = reader.readTrip();
            assertEquals("matthew", trip.getName());
            assertEquals("HK", trip.getLocation());
            assertEquals("2024", trip.getStartDate());
            assertEquals("2025", trip.getEndDate());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRegularTripsAndChange() {
        try {
            Trips trips = new Trips();
            Trip trip1 = new Trip("a", "b", "c", "d", 100);
            Trip trip2 = new Trip("d", "c" ,"b" ,"a", 1000);
            Trip tripTest = new Trip("test", "1", "2", "3", 10000);
            trips.addTrip(trip1);
            trips.addTrip(trip2);

            JsonWriter writer = new JsonWriter("./data/testRegPastTrips.json");
            writer.open();
            writer.writeTrips(trips);
            writer.close();

            writer.changeDestination("./data/testRegCurrentTrip.json");
            writer.open();
            writer.writeTrip(tripTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testRegPastTrips.json");
            trips = reader.readTrips();
            assertEquals(2, trips.getTrips().size());

            reader.changeDestination("./data/testRegCurrentTrip.json");
            tripTest = reader.readTrip();
            assertEquals("test", tripTest.getName());
            assertEquals("1", tripTest.getLocation());
            assertEquals("2", tripTest.getStartDate());
            assertEquals("3", tripTest.getEndDate());
            assertEquals(10000, tripTest.getBudget().getBudget());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
