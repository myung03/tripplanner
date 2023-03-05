package persistence;

import model.Budget;
import model.Trip;
import model.Trips;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    //Codes references JsonReader class from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    //TODO -> function that changes the source
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public void changeDestination(String next) {
        this.source = next;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Trips readTrips() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrips(jsonObject);
    }

    public Trip readTrip() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrip(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Trips parseTrips(JSONObject jsonObject) {
        Trips trips = new Trips();
        addTrips(trips, jsonObject);
        return trips;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTrips(Trips trips, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("past trips");
        for (Object json : jsonArray) {
            JSONObject nextTrip = (JSONObject) json;
            addTrip(trips, nextTrip);
        }
    }

    // MODIFIES: Trips
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addTrip(Trips trips, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        String startDate = jsonObject.getString("startDate");
        String endDate = jsonObject.getString("endDate");
        Trip trip = new Trip(name, location, startDate, endDate, 0);
        trips.addTrip(trip);
    }

    private Trip parseTrip(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        String startDate = jsonObject.getString("startDate");
        String endDate = jsonObject.getString("endDate");
        double budget = jsonObject.getJSONObject("budget").getDouble("budget");
        double spent = jsonObject.getJSONObject("budget").getDouble("spent");
        Trip trip = new Trip(name, location, startDate, endDate, budget);
        trip.getBudget().addSpent(spent);

        return trip;
    }
}

