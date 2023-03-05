package persistence;

import model.Trip;
import model.Trips;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {

    //Codes references JsonWriter class from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private static final int TAB = 4;

    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //EFFECTS: changes destination file
    public void changeDestination(String next) {
        this.destination = next;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void writeTrips(Trips trips) {
        JSONObject json = trips.toJson();
        saveToFile(json.toString(TAB));
    }

    public void writeTrip(Trip trip) {
        JSONObject json = trip.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
