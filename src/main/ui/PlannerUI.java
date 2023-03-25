package ui;

import javax.swing.*;
import java.awt.*;

public class PlannerUI extends JFrame {

    private static final int WIDTH = 1000;

    private static final int HEIGHT = 800;

    JLabel welcome;
    JLabel current;
    JFrame frame;
    JPanel panel;

    JButton add;
    JButton view;
    JButton save;
    JButton load;
    JButton edit;

    public PlannerUI() {
        frame = new JFrame();
        panel = new JPanel();
        welcome = new JLabel("Welcome!");
        current = new JLabel("W");

        frame.setSize(WIDTH, HEIGHT);
        add = new JButton("Add Trip");


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        frame.add(panel,BorderLayout.CENTER);
        panel.add(welcome);
        panel.add(add);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Trip Planner");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new PlannerUI();
    }

}