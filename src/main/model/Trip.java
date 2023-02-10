package model;

import components.Budget;

import java.util.ArrayList;

public class Trip {

    private static int nextAccountId = 1;

    private int id;
    private String name;
    private String location;
    private String date;

    private ArrayList<String> notes;
    private Budget budget;

    public Trip(String name, String location, String date, double budget) {
        id = nextAccountId++;
        this.name = name;
        this.location = location;
        this.date = date;
        this.budget = new Budget(budget);
    }

    //EFFECTS: returns name of Trip
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns location of Trip
    public String getLocation() {
        return this.location;
    }


    //EFFECTS: returns date of Trip
    public String getDate() {
        return this.date;
    }

    //MODIFIES: this
    //EFFECTS: changes name of Trip
    private void changeName(String name) {
        this.name = name;
    }
    //REQUIRES: Date be in following String format: "Month Day, Year"
    //MODIFIES: this
    //EFFECTS: changes date of Trip
    private void changeDate(String date) {
        this.date = date;
    }

    //MODIFIES: this
    //EFFECTS: changes location of Trip
    private void changeLocation(String location) {
        this.location = location;

    }

    //MODIFIES: this
    //EFFECTS: adds a note to this trip
    public void addNotes(String note) {
        //push note onto array of notes

    }

    //MODIFIES: this
    //EFFECTS: removes a trip's note at specified position
    public void removeNote(int pos) {
        //remove note from array of notes
    }


}
