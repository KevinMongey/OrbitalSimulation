import java.awt.*;
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //sets up window
        JFrame frame = new JFrame("Orbital Simulation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 1200, 800);

        //planets declaration here

        //Create jpanel to handle drawing and positioning of planets
        frame.add(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);

                //draw sun here

                //update and draw planets here
            }
        }
        );
        frame.setVisible(true);


    }
}
