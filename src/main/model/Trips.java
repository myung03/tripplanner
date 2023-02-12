package model;

import java.util.ArrayList;

//list of trips that someone has previously been on
public class Trips {
    private ArrayList<Trip> trips;

    public Trips() {
        trips = new ArrayList<Trip>();
    }

    //MODIFIES: this
    //EFFECTS: creates a trip and adds it to trips; if successful return true
    public void addTrip(Trip trip) {
        trips.add(0, trip);
    }

    public int tripIndex(Trip trip) {
        return trips.indexOf(trip);
    }
}
