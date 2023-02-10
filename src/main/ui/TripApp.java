package ui;

import model.Trip;
import model.Trips;

import java.util.Scanner;

//Codes references TellerApp class from https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class TripApp {

    private Trips first;

    private Scanner input;

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
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                //TODO: function for command processings
            }
        }

        System.out.println("\nGoodbye!");

    }

    private void init() {
        first = new Trips();
        //TODO: add a trip for init
        first.addTrip();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add trip");
        System.out.println("\tv -> view trips");
        System.out.println("\tq -> quit");
    }


}




