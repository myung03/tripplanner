package model;

import components.Budget;

import java.util.ArrayList;

//all details about a trip, including name, duration, location, and budget
public class Trip {
    private String name;
    private String location;

    private int startDate;

    private int endDate;

    private ArrayList<String> notes;
    private Budget budget;

    public Trip(String name, String location, int startDate, int endDate, double budget) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
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


    //EFFECTS: returns start date of Trip
    public int getStartDate() {
        return this.startDate;
    }

    //EFFECTS: returns end date of Trip
    public int getEndDate() {
        return this.endDate;
    }

    public Budget getBudget() {
        return this.budget;
    }

    //MODIFIES: this
    //EFFECTS: changes name of Trip
    public void changeName(String name) {
        this.name = name;
    }

    //REQUIRES: must be in format YYYY-MM-DD
    //MODIFIES: this
    //EFFECTS: changes startDate of Trip
    public void changeStartDate(int date) {
        this.startDate = date;
    }

    //REQUIRES: must be in format YYYY-MM-DD
    //MODIFIES: this
    //EFFECTS: changes endDate of Trip
    public void changeEndDate(int date) {
        this.endDate = date;
    }

    //MODIFIES: this
    //EFFECTS: changes location of Trip
    public void changeLocation(String location) {
        this.location = location;

    }

    //add agenda functionality?
}
