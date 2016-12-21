package components;

import java.awt.Point;

public class Receiver extends OpticalComponent{
	
	private double minSensitivity;
	private double maxSensitivity;
	private double minWavelength;
	private double maxWavelength;

	public Receiver(String str, Point p, int height, int width, double minSens, double maxSens, double minWave, double maxWave) {
		super(str, p, height, width);
		this.maxSensitivity = maxSens;
		this.minSensitivity = minSens;
		this.maxWavelength = maxWave;
		this.minWavelength = minWave;
	}
	
	public Receiver(OpticalComponent c) {
		super(c);
		this.minSensitivity = -10;
		this.maxSensitivity = 50;
		this.minWavelength = 1500;
		this.maxWavelength = 1600;
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
	
	
}
