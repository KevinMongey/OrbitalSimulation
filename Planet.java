package OrbitalSimulation;

import java.awt.*;

public class Planet {


	//Constants
	final double G = 6.67430e-11;
	final double SCALE = 1.5e9;
	final double TIMESTEP = 1e5;


	// variables
	double x;
	double y;
	double mass;
	double radius;
	double vx;
	double vy;
	Color color;
	// Constructor class
	public Planet(double x, double y, double mass, double radius, double vy, Color color){
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.radius = radius;
		this.vx = 0;
		this.vy = 0;
		this.color = color;
		// color

	}
	// Initilize the planets Y velocity
	public void setYVelocity(double vy){
		this.vy = vy;
	}

	public void applyGravity(Planet other){

		// distance
		double dx = other.x - this.x;
		double dy = other.y - this.y;
		double distance = Math.sqrt(dx * dx + dy * dy );

		// force
		double force = (G * this.mass * other.mass) / (distance * distance);
		double fx = force * (dx / distance);
		double fy = force * (dy / distance);

		// acceleration
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.vx += ax * TIMESTEP;
		this.vy += ay * TIMESTEP;

	}

	public void draw(Graphics g) {
		g.setColor(color);
		double scaledX = (int)(x / SCALE) + (int)(1280 / 2);
		double scaledY = (int)(y / SCALE) + (int)(720 / 2);
		g.fillOval((int) scaledX, (int) scaledY, (int) radius, (int) radius);

	}


}