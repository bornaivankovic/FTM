package components;

import java.awt.Point;

public class Transmitter extends OpticalComponent{
	private double outputPower;
	private double centralWavelength;
	private double minWavelengthBand;
	private double maxWavelengthBand;
	private int numberOfMods;
	private double connectorAttenuance;
	
	public Transmitter(String str,Point p,int height,int width, double wavelength, double power, double minBand, double maxBand, int mods, double attenuance) {
		super(str, p, height, width);
		this.outputPower=power;
		this.centralWavelength=wavelength;
		this.minWavelengthBand = minBand;
		this.maxWavelengthBand = maxBand;
		this.numberOfMods = mods;
		this.connectorAttenuance = attenuance;
	}
	
	public Transmitter (OpticalComponent c) {
		super(c);
	}

	public double getOutputPower() {
		return outputPower;
	}

	public void setOutputPower(double outputPower) {
		this.outputPower = outputPower;
	}

	public double getCentralWavelength() {
		return centralWavelength;
	}

	public void setCentralWavelength(double centralWavelength) {
		this.centralWavelength = centralWavelength;
	}

	public double getMinWavelengthBand() {
		return minWavelengthBand;
	}

	public void setMinWavelengthBand(double minWavelengthBand) {
		this.minWavelengthBand = minWavelengthBand;
	}

	public double getMaxWavelengthBand() {
		return maxWavelengthBand;
	}

	public void setMaxWavelengthBand(double maxWavelengthBand) {
		this.maxWavelengthBand = maxWavelengthBand;
	}

	public int getNumberOfMods() {
		return numberOfMods;
	}

	public void setNumberOfMods(int numberOfMods) {
		this.numberOfMods = numberOfMods;
	}

	public double getConnectorAttenuance() {
		return connectorAttenuance;
	}

	public void setConnectorAttenuance(double connectorAttenuance) {
		this.connectorAttenuance = connectorAttenuance;
	}
	
	
}
