package ui;

import model.Trip;
import model.Trips;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Trip planner application

//Codes references TellerApp & WorkRoomApp class from https://github.students.cs.ubc.ca/CPSC210/TellerApp
// & https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class TripApp {

    private static final String HISTORY_STORE = "./data/history.json";

    private static final String CURRENT_STORE = "./data/current.json";
    private Trips history;

    private Trip current;
    private Scanner input;

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;

    //EFFECTS: starts app
    public TripApp() {
        history = new Trips();
        Trip past = new Trip("Spring Break", "London",
                "2023-03-10", "2023-03-20", 1000);
        history.addTrip(past);

        jsonWriter = new JsonWriter(HISTORY_STORE);
        jsonReader = new JsonReader(HISTORY_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        runTrip();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runTrip() {
        boolean keepGoing = true;
        String command = null;

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

    //EFFECTS: prints main menu onto console
    private void mainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add trip");
        System.out.println("\tc -> view/edit current trip");
        System.out.println("\tv -> view past trips");
        System.out.println("\ts -> save trips to file");
        System.out.println("\tl -> load trips from file");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            createTrip();
        } else if (command.equals("c")) {
            editTrip();
        } else if (command.equals("v")) {
            viewPastTrips();
        } else if (command.equals("s")) {
            saveTrips();
        } else if (command.equals("l")) {
            loadTrips();
        } else {
            System.out.println("Your selection is not valid.");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a trip if one does not already exist
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


    //MODIFIES: this
    //EFFECTS: changes the name of the current trip
    private void selectName() {
        String selection = "";
        System.out.println("Enter a name for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeName(selection);
    }

    //MODIFIES: this
    //EFFECTS: changes the location of the current trip
    private void selectLocation() {
        String selection = "";
        System.out.println("Enter a location for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeLocation(selection);
    }

    //MODIFIES: this
    //EFFECTS: changes the start date of the current trip
    private void selectStartDate() {
        String selection = "";
        System.out.println("Enter a start date in the form of YYYY-MM-DD for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeStartDate(selection);
    }

    //MODIFIES: this
    //EFFECTS: changes the end date of the current trip
    private void selectEndDate() {
        String selection = "";
        System.out.println("Enter a end date in the form of YYYY-MM-DD for your trip.");

        while (selection.isEmpty()) {
            selection = input.nextLine();
        }

        current.changeEndDate(selection);
    }

    //MODIFIES: this
    //EFFECTS: sets the budget for current trip
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


    //MODIFIES: this
    //EFFECTS: processes user input
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

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processEdit(String command) {
        if (current == null) {
            System.out.println("You are not currently planning a trip. You can add one in the 'add trip' option");
        } else if (command.equals("c")) {
            System.out.println("Your trip " + current.getName() + " at " + current.getLocation() + " starts on "
                    + current.getStartDate() + " and ends on " + current.getEndDate() + "." + " Your budget is "
                    + current.getBudget().getBudget() + ", and you have " + current.getBudget().getRemaining()
                    + " left to spend.");
        } else if (command.equals("i")) {
            editInfo();
        } else if (command.equals("b")) {
            addSpending();
        } else if (command.equals("e")) {
            endTrip();
        } else {
            System.out.println("Your selection is not valid.");
        }
    }



    //EFFECTS: prints edit menu onto console
    private void editMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> view current trip");
        System.out.println("\ti -> edit trip information");
        System.out.println("\tb -> edit budget");
        System.out.println("\te -> end current trip");
        System.out.println("\tr -> return to previous");
    }

    //MODIFIES: this
    //EFFECTS: processes user input
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

    //EFFECTS: prints info menu onto console
    private void infoMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> change trip name");
        System.out.println("\tl -> change trip location");
        System.out.println("\ts -> change trip start date");
        System.out.println("\te -> change trip end date");
        System.out.println("\tr -> return to previous");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
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

    //MODIFIES: this
    //EFFECTS: edits spending for current trip
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

    //MODIFIES: this
    //EFFECTS: ends current trip, reset it to null, and adds it to past Trips
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


    //EFFECTS: prints Trips array onto console
    private void viewPastTrips() {
        for (Trip trip: history.getTrips()) {
            System.out.println(trip.printTrip());
        }
    }

    //EFFECTS: save your current and previous trips into a JSON file
    //if current empty, will only save previous
    private void saveTrips() {
        try {
            jsonWriter.open();
            jsonWriter.writeTrips(history);
            jsonWriter.close();

            if (current == null) {
                System.out.println("Saved trips!");
                return;
            }
            jsonWriter.changeDestination(CURRENT_STORE);
            jsonWriter.open();
            jsonWriter.writeTrip(current);
            jsonWriter.close();
            System.out.println("Saved current and previous trips!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save history because file was not found");
        }
    }


    //MODIFIES: this
    //EFFECTS: load your current and previous trips from JSON file
    private void loadTrips() {
        try {
            history = jsonReader.readTrips();
            jsonReader.changeDestination(CURRENT_STORE);
            current = jsonReader.readTrip();
            System.out.println("Loaded your current trip and previous trips!");
        } catch (IOException e) {
            System.out.println("Unable to read from file.");
        } catch (JSONException e) {
            System.out.println("Loaded your previous trips!");
        }

    }



}




