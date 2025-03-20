package OrbitalSimulation;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //sets up window
        JFrame frame = new JFrame("Orbital Simulation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 1280, 720);

        //planets declaration here
        Planet sun = new Planet(0, 0, 1.989e30, 20, 0, Color.YELLOW);

        Planet earth = new Planet(1.496e11, 0, 5.972e24, 10, 29780, Color.GREEN);
        Planet mars = new Planet(2.279e11, 0, 6.39e23, 8, 24130, Color.RED);
        Planet jupiter = new Planet(7.785e11, 0, 1.898e27, 15, 13070, Color.ORANGE);
        Planet mercury = new Planet(5.46e10, 0, 3.3e23, 5, 47000, Color.PINK);
        Planet venus = new Planet(10e10, 0, 4.87e24, 6, 35000, Color.gray);

        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(sun);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(mercury);
        planets.add(venus);

        //Create jpanel to handle drawing and positioning of planets
        frame.add(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);

                //draw sun here
                sun.draw(g);

                //update and draw planets here
                earth.draw(g);
                mars.draw(g);
                jupiter.draw(g);
                mercury.draw(g);
                venus.draw(g);
            }
        }
        );
        frame.setVisible(true);


    }


}
