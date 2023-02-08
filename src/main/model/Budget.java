package model;

//The budget of a trip
public class Budget {

    private double budget; //current budget

    private double spent; //current amount spent

    private double remaining;

    //REQUIRES: Budget must be > 100$
    //EFFECTS: Creates a new Budget instance; budget is set to given number and spent is set to 0
    public Budget(double budget) {
        this.budget = budget;
        this.spent = 0;
    }

    public double getBudget() {
        return this.budget;
    }

    public double getSpent() {
        return this.spent;
    }

    //EFFECTS: Returns the remaining amount you have to spend on your trip
    public double getRemaining() {
        remaining = this.budget - this.spent;
        return this.remaining;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to budget
    public double addBudget(double amount) {
        return 0;
    }

    //REQUIRES: amount >= 0, and less than current budget
    //MODIFIES: this
    //EFFECTS: lowers budget by amount
    public double lowerBudget(double amount) {
        return 0;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to spent
    public double addSpent(double amount) {
        return 0;
    }

}

