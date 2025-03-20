import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanetOrbitSimulator extends JPanel implements ActionListener {

    private Timer timer;
    private double angleMercury, angleVenus, angleEarth, angleMars;
    private final double mercuryOrbitalSpeed = 0.02, venusOrbitalSpeed = 0.015, earthOrbitalSpeed = 0.01, marsOrbitalSpeed = 0.008;

    public PlanetOrbitSimulator() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.black);
        timer = new Timer(16, this);  // 60 FPS (1000ms / 16ms ~ 60FPS)
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update angles of planets for their orbital motion
        angleMercury += mercuryOrbitalSpeed;
        angleVenus += venusOrbitalSpeed;
        angleEarth += earthOrbitalSpeed;
        angleMars += marsOrbitalSpeed;

        // Ensure the angles stay within 0 to 2π
        angleMercury %= (2 * Math.PI);
        angleVenus %= (2 * Math.PI);
        angleEarth %= (2 * Math.PI);
        angleMars %= (2 * Math.PI);

        repaint();  // Repaint the panel to update the positions of the planets
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the Sun in the center
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(375, 275, 50, 50);  // Sun at the center of the panel

        // Draw Mercury (inner orbit)
        g2d.setColor(Color.GRAY);
        int mercuryX = (int) (375 + 100 * Math.cos(angleMercury));
        int mercuryY = (int) (275 + 100 * Math.sin(angleMercury));
        g2d.fillOval(mercuryX, mercuryY, 10, 10);

        // Draw Venus (2nd orbit)
        g2d.setColor(Color.ORANGE);
        int venusX = (int) (375 + 150 * Math.cos(angleVenus));
        int venusY = (int) (275 + 150 * Math.sin(angleVenus));
        g2d.fillOval(venusX, venusY, 15, 15);

        // Draw Earth (3rd orbit)
        g2d.setColor(Color.BLUE);
        int earthX = (int) (375 + 200 * Math.cos(angleEarth));
        int earthY = (int) (275 + 200 * Math.sin(angleEarth));
        g2d.fillOval(earthX, earthY, 20, 20);

        // Draw Mars (4th orbit)
        g2d.setColor(Color.RED);
        int marsX = (int) (375 + 250 * Math.cos(angleMars));
        int marsY = (int) (275 + 250 * Math.sin(angleMars));
        g2d.fillOval(marsX, marsY, 18, 18);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Planet Orbit Simulator");
        PlanetOrbitSimulator panel = new PlanetOrbitSimulator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}