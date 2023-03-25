package ui;

import model.Trip;
import model.Trips;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PlannerUI extends JFrame {


    private Trips history;

    private Trip current;

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;

    JLabel welcome;
    JLabel trip;
    JLabel persist;
    JFrame frame;
    JFrame save;
    JFrame load;
    JPanel panel;

    JButton add;
    JButton edit;
    JButton view;
    JButton saveb;
    JButton loadb;

    private static final String HISTORY_STORE = "./data/history.json";

    private static final String CURRENT_STORE = "./data/current.json";

    public PlannerUI() {
        frame = new JFrame();
        frame.setBackground(Color.blue);
        frame.setPreferredSize(new Dimension(600, 600));
        panel = new JPanel();

        initUI();
        createText();
        createButtons();


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Trip Planner");
        frame.pack();
        frame.setVisible(true);

    }

    //EFFECTS: initialize trips and JSON stores
    private void initUI() {
        history = new Trips();
        Trip past = new Trip("Spring Break", "London",
                "2023-03-10", "2023-03-20", 1000);
        history.addTrip(past);
        jsonWriter = new JsonWriter(HISTORY_STORE);
        jsonReader = new JsonReader(HISTORY_STORE);
    }

    //EFFECTS: initialize buttons on home screen
    private void createButtons() {
        //TODO: Initalize buttons;
        add = new JButton("Add Trip");
        edit = new JButton("Edit Trip");
        view = new JButton("View Past Trips");
        saveb = new JButton("Save");
        loadb = new JButton("Load");
        addActionListeners();
        panel.add(add);
        panel.add(edit);
        panel.add(view);
        panel.add(saveb);
        panel.add(loadb);

    }


    //EFFECTS: initialize home screen text
    private void createText() {
        welcome = new JLabel("Welcome!");
        trip = new JLabel("You are not currently on any trips. Press 'Add Trip' to start planning one.");
        persist = new JLabel("");
        persist.setVisible(false);
        panel.add(welcome);
        panel.add(trip);
        panel.add(persist);
    }

    //EFFECTS: adds action listeners to each button on homescreen
    private void addActionListeners() {
        add.addActionListener(e -> {
            //TODO: Function for opening a new window to add trip
            addTrip(e);
        });

        edit.addActionListener(e -> {
            //TODO: Function for opening a new window to edit trip
            editTrip(e);
        });

        view.addActionListener(e -> {
            //TODO: Function for opening a new window to show previous trips
            showPrev(e);
        });

        saveb.addActionListener(e -> {
            //TODO: Function for saving trips/current
            saveTrip(e);
        });
        loadb.addActionListener(e -> {
            //TODO: Function for loading trips/current
            loadTrip(e);

        });


    }

    public void addTrip(ActionEvent e) {
        //TODO: add a button and a function for adding a trip
        JFrame newFrame = new JFrame("Add a New Trip");
        JLabel label = new JLabel("This is a new window!");
        newFrame.add(label);
        newFrame.pack();
        frame.setVisible(false);
        newFrame.setVisible(true);
    }

    public void editTrip(ActionEvent e) {
        //TODO: add a button and a function for editing trip information
        JFrame newFrame = new JFrame("Edit your current trip");
        JLabel label = new JLabel("This is a new window!");
        newFrame.add(label);
        newFrame.pack();
        frame.setVisible(false);
        newFrame.setVisible(true);
    }

    public void showPrev(ActionEvent e) {
        //TODO: add a button and a function show previous trips
        JFrame newFrame = new JFrame("Previous trips");
        JLabel label = new JLabel("This is a new window!");
        newFrame.add(label);
        newFrame.pack();
        frame.setVisible(false);
        newFrame.setVisible(true);
    }

    public void saveTrip(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.writeTrips(history);
            jsonWriter.close();

            if (current == null) {
                persist.setText("Saved trips!");
                persist.setVisible(true);
                return;
            }
            jsonWriter.changeDestination(CURRENT_STORE);
            jsonWriter.open();
            jsonWriter.writeTrip(current);
            jsonWriter.close();
            persist.setText("Saved current and previous trips!");
            persist.setVisible(true);
        } catch (FileNotFoundException err) {
            persist.setText("Unable to save history because file was not found");
        }

    }

    public void loadTrip(ActionEvent e) {
        try {
            history = jsonReader.readTrips();
            jsonReader.changeDestination(CURRENT_STORE);
            current = jsonReader.readTrip();
            persist.setText("Loaded your current trip and previous trips!");
            persist.setVisible(true);
        } catch (IOException err) {
            persist.setText("Unable to read from file.");
            persist.setVisible(true);
        } catch (JSONException err) {
            persist.setText("Loaded your previous trips!");
            persist.setVisible(true);
        }

    }



    public static void main(String[] args) {
        new PlannerUI();
    }

}