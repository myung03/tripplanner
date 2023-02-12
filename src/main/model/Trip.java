package model;


//all details about a trip, including name, duration, location, and budget
public class Trip {
    private String name;
    private String location;

    private String startDate;

    private String endDate;

    private Budget budget;

    public Trip(String name, String location, String startDate, String endDate, double budget) {
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
    public String getStartDate() {
        return this.startDate;
    }

    //EFFECTS: returns end date of Trip
    public String getEndDate() {
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
    public void changeStartDate(String date) {
        this.startDate = date;
    }

    //REQUIRES: must be in format YYYY-MM-DD
    //MODIFIES: this
    //EFFECTS: changes endDate of Trip
    public void changeEndDate(String date) {
        this.endDate = date;
    }

    //MODIFIES: this
    //EFFECTS: changes location of Trip
    public void changeLocation(String location) {
        this.location = location;

    }

    public void changeBudget(Double budget) {
        this.budget = new Budget(budget);
    }

    //add agenda functionality?

    //EFFECT: formats Trip into a string
    public String printTrip() {
        return name + ", at " +  location + ". Started on " + startDate + " and ended on " + endDate + ".";

    }
}

