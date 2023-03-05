package model;

//The budget for a given trip

import org.json.JSONObject;
import persistence.Writable;

public class Budget implements Writable {


    private double budget; //current budget

    private double spent; //current amount spent

    private double remaining;

    //REQUIRES: Budget must be > 100$
    //EFFECTS: Creates a new Budget instance; budget is set to given number and spent is set to 0
    public Budget(double budget) {
        this.budget = budget;
        this.spent = 0;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to budget
    public Boolean addBudget(double amount) {
        if (amount <= 0) {
            return false;
        } else {
            this.budget += amount;
            return true;
        }
    }

    //REQUIRES: amount >= 0, and less than current budget
    //MODIFIES: this
    //EFFECTS: lowers budget by amount
    public Boolean lowerBudget(double amount) {
        if (amount <= 0 || budget - amount <= 0) {
            return false;
        } else {
            this.budget -= amount;
            return true;
        }
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to spent
    public Boolean addSpent(double amount) {
        if (amount <= 0) {
            return false;
        } else {
            this.spent += amount;
            return true;
        }
    }


    //EFFECTS: Return your budget for trip
    public double getBudget() {
        return this.budget;
    }

    //EFFECTS: Return your current spending amount for trip;
    public double getSpent() {
        return this.spent;
    }

    //EFFECTS: Returns the remaining amount you have to spend on your trip
    public double getRemaining() {
        remaining = this.budget - this.spent;
        return this.remaining;
    }

    //EFFECTS: Returns this formatted as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("budget", budget);
        json.put("spent", spent);
        json.put("remaining", remaining);
        return json;
    }





}

