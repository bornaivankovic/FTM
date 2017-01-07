package components;

import java.awt.Point;

import gui.Console;

public class Receiver extends OpticalComponent {

	private double minSensitivity;
	private double maxSensitivity;
	private double minWavelength;
	private double maxWavelength;

	private Console console;

	public Receiver(String str, Point p, int height, int width, double minSens, double maxSens, double minWave,
			double maxWave) {
		super(str, p, height, width);
		maxSensitivity = maxSens;
		minSensitivity = minSens;
		maxWavelength = maxWave;
		minWavelength = minWave;
	}

	public Receiver(OpticalComponent c) {
		super(c);
		minSensitivity = -10;
		maxSensitivity = 50;
		minWavelength = 1500;
		maxWavelength = 1600;
		setImgPath("rx.png");
	}

	public Receiver(OpticalComponent c, Console con) {
		super(c);
		minSensitivity = -10;
		maxSensitivity = 50;
		minWavelength = 1500;
		maxWavelength = 1600;
		console = con;
		setImgPath("rx.png");
	}

	public double getMinSensitivity() {
		return minSensitivity;
	}

	public void setMinSensitivity(double minSensitivity) {
		this.minSensitivity = minSensitivity;
	}

	public double getMaxSensitivity() {
		return maxSensitivity;
	}

	public void setMaxSensitivity(double maxSensitivity) {
		this.maxSensitivity = maxSensitivity;
	}

	public double getMinWavelength() {
		return minWavelength;
	}

	public void setMinWavelength(double minWavelength) {
		this.minWavelength = minWavelength;
	}

	public double getMaxWavelength() {
		return maxWavelength;
	}

	public void setMaxWavelength(double maxWavelength) {
		this.maxWavelength = maxWavelength;
	}

	public void handleSignal(Signal s) {
		if (s.getPower() < minSensitivity || s.getPower() > maxSensitivity)
			console.println("No Signal detected");
		else {
			int size = s.getWavelengthListSize();
			for (int i = 0; i < size; i++) {
				if (s.getWavelength(i) < minWavelength || s.getWavelength(i) > maxWavelength)
					console.println("Wavelength out of detectable range...");
				else
					console.println("Detected " + s.getWavelength(i) + " nm...");
			}
		}
	}

}
