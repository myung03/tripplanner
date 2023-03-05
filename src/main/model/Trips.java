package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//list of trips that someone has previously been on
public class Trips {
    private ArrayList<Trip> trips;

    public Trips() {
        trips = new ArrayList<Trip>();
    }

    //EFFECTS: return trips
    public ArrayList<Trip> getTrips() {
        return trips;
    }

    //MODIFIES: this
    //EFFECTS: creates a trip and adds it to trips; if successful return true
    public void addTrip(Trip trip) {
        trips.add(0, trip);
    }

    //EFFECTS: returns index of given trip
    public int tripIndex(Trip trip) {
        return trips.indexOf(trip);
    }

    //EFFECTS: returns this as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("past trips", tripsToJson());
        return json;

    }

    //EFFECTS: returns a JSONArray of each trip inside list of trips
    private JSONArray tripsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trip t : trips) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
