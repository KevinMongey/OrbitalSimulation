package OrbitalSimulation;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set up window
        JFrame frame = new JFrame("Orbital Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        // Create planets
        Planet sun = new Planet(0, 0, 1.989e30, 20, 0, Color.YELLOW);
        Planet earth = new Planet(1.496e11, 0, 5.972e24, 10, 29780, Color.GREEN);
        Planet mars = new Planet(2.279e11, 0, 6.39e23, 8, 24130, Color.RED);
        Planet jupiter = new Planet(7.785e11, 0, 1.898e27, 15, 13070, Color.ORANGE);
        Planet mercury = new Planet(5.46e10, 0, 3.3e23, 5, 47000, Color.PINK);
        Planet venus = new Planet(10e10, 0, 4.87e24, 6, 35000, Color.GRAY);

        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(sun);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(mercury);
        planets.add(venus);

        // Create JPanel for drawing
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);

                // Draw all planets
                for (Planet planet : planets) {
                    planet.draw(g);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);

        // Start simulation loop
        while (true) {
            // Apply gravity between all planets
            for (int i = 0; i < planets.size(); i++) {
                for (int j = 0; j < planets.size(); j++) {
                    if (i != j) {
                        planets.get(i).applyGravity(planets.get(j));
                    }
                }
            }

            // Update positions
            for (Planet planet : planets) {
                planet.updatePosition();
            }

            // Repaint the panel
            panel.repaint();

            try{
                Thread.sleep(16);
            } catch(InterruptedException e){
                e.printStackTrace();
            }


        }
    }
}


