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

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    //MODIFIES: this
    //EFFECTS: creates a trip and adds it to trips; if successful return true
    public void addTrip(Trip trip) {
        trips.add(0, trip);
    }

    public int tripIndex(Trip trip) {
        return trips.indexOf(trip);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("past trips", tripsToJson());
        return json;

    }
    private JSONArray tripsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trip t : trips) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
