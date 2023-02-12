package model;

public class Budget {

    //The budget for a given trip

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
    public void addBudget(double amount) {
        this.budget += amount;
    }

    //REQUIRES: amount >= 0, and less than current budget
    //MODIFIES: this
    //EFFECTS: lowers budget by amount
    public void lowerBudget(double amount) {
        this.budget -= amount;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to spent
    public void addSpent(double amount) {
        this.spent += amount;
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


}

