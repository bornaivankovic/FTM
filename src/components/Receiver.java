package components;

import java.awt.Point;
import java.io.Serializable;

import gui.Console;

public class Receiver extends OpticalComponent implements Serializable {

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
		this.setInsertionLoss(0.3);
		this.setReturnLoss(0.2);
	}

	public Receiver(OpticalComponent c) {
		super(c);
		minSensitivity = -10;
		maxSensitivity = 50;
		minWavelength = 1500;
		maxWavelength = 1600;
		setImgPath("Rx.png");
		this.setInsertionLoss(0.3);
		this.setReturnLoss(0.2);
	}

	public Receiver(OpticalComponent c, Console con) {
		super(c);
		minSensitivity = -10;
		maxSensitivity = 50;
		minWavelength = 1500;
		maxWavelength = 1600;
		console = con;
		setImgPath("Rx.png");
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
		attenuateSignal(s);
		Console.getConsoleInstance().println("_______________");
		Console.getConsoleInstance().println(getLabel() + ":");
		if (s.getPower() < minSensitivity || s.getPower() > maxSensitivity)
			Console.getConsoleInstance().println("No Signal detected");
		else {
			Console.getConsoleInstance().println("Input signal's power: " + s.getPower() + "dB.");
			int size = s.getWavelengthListSize();
			for (int i = 0; i < size; i++) {
				if (s.getWavelength(i) < minWavelength || s.getWavelength(i) > maxWavelength)
					Console.getConsoleInstance().println("Wavelength out of detectable range...");
				else
					Console.getConsoleInstance().println("Detected " + s.getWavelength(i) + " nm...");
			}
		}
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		outPower = outPower - this.getReturnLoss() - this.getInsertionLoss();
		s.setPower(outPower);
	}

}
