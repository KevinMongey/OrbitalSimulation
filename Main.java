package OrbitalSimulation;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //sets up window
        JFrame frame = new JFrame("Orbital Simulation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 1200, 800);

        //planets declaration here
        Planet sun = new Planet(600, 400, 1.989e30, 20, Color.YELLOW);
        //Create jpanel to handle drawing and positioning of planets
        frame.add(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);

                //draw sun here
                sun.draw(g);

                //update and draw planets here
            }
        }
        );
        frame.setVisible(true);


    }


}
