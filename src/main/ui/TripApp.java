package ui;

import model.Trip;
import model.Trips;

import java.util.Scanner;

//Trip planner application
//Codes references TellerApp class from https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class TripApp {

    private Trips history;

    private Trip current;
    private Scanner input;

    //EFFECTS: starts app
    public TripApp() {
        runTrip();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runTrip() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

    }

    //MODIFIES: this
    //EFFECTS: initializes trips
    private void init() {
        history = new Trips();
        Trip past = new Trip("Spring Break", "London",
                "2023-03-10", "2023-03-20", 1000);
        history.addTrip(past);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: prints main menu onto console
    private void mainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add trip");
        System.out.println("\tc -> view/edit current trip");
        System.out.println("\te -> end current trip");
        System.out.println("\tv -> view past trips");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS; processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            createTrip();
        } else if (command.equals("c")) {
            editTrip();
        } else if (command.equals("e")) {
            endTrip();
        } else if (command.equals("v")) {
            viewPastTrips();
        } else {
            System.out.println("Your selection is not valid.");
        }
    }

    private void createTrip() {
        if (current == null) {
            current = new Trip("", "", "", "", 0.0);
            selectName();
            selectLocation();
            selectStartDate();
            selectEndDate();
            selectBudget();
        } else {
            System.out.println("You are already on a trip! End current trip before adding a new one.");
        }

        System.out.println("Your trip " + current.getName() + " at " + current.getLocation() + " starts on "
                + current.getStartDate() + " and ends on " + current.getEndDate() + ".");

    }

    private void selectName() {
        String selection = "";
        System.out.println("Enter a name for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeName(selection);
    }

    private void selectLocation() {
        String selection = "";
        System.out.println("Enter a location for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeLocation(selection);
    }

    private void selectStartDate() {
        String selection = "";
        System.out.println("Enter a start date in the form of YYYY-MM-DD for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeStartDate(selection);
    }

    private void selectEndDate() {
        String selection = "";
        System.out.println("Enter a end date in the form of YYYY-MM-DD for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeEndDate(selection);
    }

    private void selectBudget() {
        Double selection = 0.0;
        System.out.print("Enter a budget for your trip (must be greater to or equal to $100): $");
        selection = input.nextDouble();

        if (selection < 100) {
            System.out.println("You must enter a value higher than $100.");
            System.out.print("Enter a budget for your trip (must be greater to or equal to $100): $");
        }

        current.changeBudget(selection);
    }


    private void editTrip() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            editMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("r")) {
                keepGoing = false;
            } else {
                processEdit(command);
            }
        }
    }

    private void processEdit(String command) {
        if (current == null) {
            System.out.println("You are not currently planning a trip. You can add one in the 'add trip' option");
        } else if (command.equals("c")) {
            System.out.println("Your trip " + current.getName() + " at " + current.getLocation() + " starts on "
                    + current.getStartDate() + " and ends on " + current.getEndDate() + "." + " Your budget is "
                    + current.getBudget().getBudget() + ", and you have " + current.getBudget().getRemaining()
                    + " left to spend.");
        } else if (command.equals("e")) {
            editInfo();
        } else if (command.equals("b")) {
            addSpending();
        } else {
            System.out.println("Your selection is not valid.");
        }
    }



    private void editMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> view current trip");
        System.out.println("\te -> edit trip information");
        System.out.println("\tb -> edit budget");
        System.out.println("\tr -> return to previous");
    }

    private void editInfo() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            infoMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("r")) {
                keepGoing = false;
            } else {
                processInfo(command);
            }
        }
    }

    private void infoMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> change trip name");
        System.out.println("\tl -> change trip location");
        System.out.println("\ts -> change trip start date");
        System.out.println("\te -> change trip end date");
        System.out.println("\tr -> return to previous");
    }

    private void processInfo(String command) {
        if (command.equals("n")) {
            selectName();
        } else if (command.equals("l")) {
            selectLocation();
        } else if (command.equals("s")) {
            selectStartDate();
        } else if (command.equals("e")) {
            selectEndDate();
        } else {
            System.out.println("Your selection is not valid.");
        }
    }

    private void addSpending() {
        Double selection;
        Double budget = current.getBudget().getBudget();
        Double spent = current.getBudget().getSpent();
        System.out.print("Enter a spending for your trip: $");
        selection = input.nextDouble();

        if (selection > budget || selection + spent > budget) {
            System.out.print("You cannot spend more than your budget!");
        } else if (selection < 0) {
            System.out.print("You cannot enter a negative value for your spending.");
        } else {
            current.getBudget().addSpent(selection);
        }
    }

    private void endTrip() {
        if (current == null) {
            System.out.println("You are not currently planning a trip. You can add one in the 'add trip' option");
        } else {
            Trip past = current;
            history.addTrip(past);
            current = null;
            System.out.println("Your current trip has ended. You can view it in the 'view past trips' option.");
        }
    }


    private void viewPastTrips() {
        for (Trip trip: history.getTrips()) {
            System.out.println(trip.printTrip());
        }
    }



}




