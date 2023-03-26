package ui;

import model.Trip;
import model.Trips;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static javax.swing.BorderFactory.createEmptyBorder;

public class PlannerUI extends JFrame {


    private Trips history;

    private Trip current;

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;

    JLabel welcome;
    JLabel trip;
    JLabel persist;
    JFrame frame;
    JFrame prev;
    JFrame change;
    JPanel panel;
    JPanel infoPanel;
    JTextField name;
    JTextField location;
    JTextField startDate;
    JTextField endDate;
    JTextField budget;
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


        panel.setBorder(createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        frame.add(panel, BorderLayout.CENTER);
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
        if (trip == null) {
            trip = new JLabel("You are not currently on any trips. Press 'Add Trip' to start planning one.");
        }
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
            showPrev(e);
        });

        saveb.addActionListener(e -> {
            saveTrip(e);
        });

        loadb.addActionListener(e -> {
            loadTrip(e);
        });




    }

    //EFFECTS: takes user to frame for adding a new trip
    public void addTrip(ActionEvent e) {
        initAdd();
        JPanel infoPanel = initTextFields(new JPanel());
        change.add(infoPanel);
        JButton button = new JButton("Create");
        change.add(button);
        button.addActionListener(c -> {
            //TODO: create trip functionality on button click
        });
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

    //EFFECTS: takes user to frame with previous trips
    private void showPrev(ActionEvent e) {
        prev = new JFrame("Previous trips");
        initPrev();
        JLabel label = new JLabel("Here is a list of your previous trips.");
        JPanel tripContainer = new JPanel();
        JButton button = new JButton("Close window");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Border paddedBorder = BorderFactory.createCompoundBorder(border,
                createEmptyBorder(15, 30, 15, 30));
        tripContainer.setBorder(paddedBorder);
        tripContainer.setLayout(new GridLayout(0, 1));
        button.addActionListener(c -> closePrev(c));

        tripContainer.setBorder(paddedBorder);
        for (Trip trip : history.getTrips()) {
            tripContainer.add(new JLabel(trip.printTrip()));
        }
        prev.add(label);
        prev.add(tripContainer, BorderLayout.CENTER);
        prev.add(button);
    }

    //EFFECTS: saves current/past trips into local JSON store
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

    //EFFECTS: loads current/past trips from local JSON store
    private void loadTrip(ActionEvent e) {
        try {
            history = jsonReader.readTrips();
            jsonReader.changeDestination(CURRENT_STORE);
            current = jsonReader.readTrip();
            persist.setText("Loaded your current trip and previous trips!");
            persist.setVisible(true);
            trip.setText("<html>Your trip " + current.getName() + " at " + current.getLocation() + " starts on "
                    + current.getStartDate() + " and ends on " + current.getEndDate() + "." + " Your budget is "
                    + current.getBudget().getBudget() + ", and you have " + current.getBudget().getRemaining()
                    + " left to spend.<html>");
            trip.setHorizontalAlignment(SwingConstants.LEFT);
        } catch (IOException err) {
            persist.setText("Unable to read from file.");
            persist.setVisible(true);
        } catch (JSONException err) {
            persist.setText("Loaded your previous trips!");
            persist.setVisible(true);
        }
    }

    //EFFECTS: closes prev window and opens home window
    private void closePrev(ActionEvent e) {
        prev.setVisible(false);
        frame.setVisible(true);
    }

    private void initPrev() {
        prev.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prev.setResizable(false);
        prev.setLayout(new FlowLayout(FlowLayout.CENTER,0, 40));
        prev.setPreferredSize(new Dimension(600, 800));
        prev.setBackground(Color.blue);
        prev.pack();
        frame.setVisible(false);
        prev.setVisible(true);
    }

    private void initAdd() {
        change = new JFrame("Add a New Trip");
        change.setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter the following information to begin your trip!");
        change.add(label);
        change.setPreferredSize(new Dimension(400, 600));
        change.pack();
        frame.setVisible(false);
        change.setVisible(true);

    }

    //initialize text fields corresponding and labels
    private JPanel initTextFields(JPanel infoPanel) {
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30,30, 10, 30));
        JLabel label1 = new JLabel("Name of trip");
        JLabel label2 = new JLabel("Location");
        JLabel label3 = new JLabel("Start Date");
        JLabel label4 = new JLabel("End Date");
        JLabel label5 = new JLabel("Budget (must be over $100)");
        name = new JTextField(2);
        location = new JTextField(2);
        startDate = new JTextField(2);
        endDate = new JTextField(2);
        budget = new JTextField(2);
        infoPanel.add(label1);
        infoPanel.add(name);
        infoPanel.add(label2);
        infoPanel.add(location);
        infoPanel.add(label3);
        infoPanel.add(startDate);
        infoPanel.add(label4);
        infoPanel.add(endDate);
        infoPanel.add(label5);
        infoPanel.add(budget);
        return infoPanel;
    }





    public static void main(String[] args) {
        new PlannerUI();
    }

}