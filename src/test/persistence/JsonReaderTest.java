package persistence;

import model.Trip;
import model.Trips;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

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

}
