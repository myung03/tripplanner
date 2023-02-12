package ui;

import model.Trip;
import model.Trips;

import java.util.Scanner;

//Codes references TellerApp class from https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class TripApp {

    private Trips first;

    private Trip current;
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
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

    }

    private void init() {
        first = new Trips();
        //TODO: add a trip for init
        Trip past = new Trip("Spring Break", "London", 2023 - 03 - 10, 2023 - 03 - 20, 1000);
        first.addTrip(past);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add trip");
        System.out.println("\tc -> view/edit current trip");
        System.out.println("\tv -> view past trips");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            //TODO: add trip interface
        } else if (command.equals("c")) {
            //TODO: view/edit current trip interface
        } else if (command.equals("v")) {
            //TODO: view past trips interface
        } else {
            System.out.println("Your selection is not valid.");
        }
    }


}




