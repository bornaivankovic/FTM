package components;

import java.awt.Point;

public class Filter extends OpticalComponent {

	private double centralWavelength;
	private double wavelengthBandWidth; // nekakva peek-to-peek vrijednost
										// pomocu koje cemo uzet pola
										// vrijednosti manje od centralne i
										// pola vrijednosti iznad centralne valne uljine
	private double minBand;
	private double maxBand; 
	private double powerLoss = 0.2; // DEFAULTNI gubitak na komponenti

	public Filter(String str, Point p, int height, int width, double centWave, double wavelengthBand) {
		super(str, p, height, width);
		centralWavelength = centWave;
		wavelengthBandWidth = wavelengthBand;
		minBand = centralWavelength - wavelengthBandWidth / 2;
		maxBand = centralWavelength + wavelengthBandWidth / 2;
	}

	public Filter(OpticalComponent c) {
		super(c);
		centralWavelength = 1570;
		wavelengthBandWidth = 20;
		minBand = 1560;
		maxBand = 1590;
		setImgPath("filter.png");
	}

	public double getCentralWavelength() {
		return centralWavelength;
	}

	public void setCentralWavelength(double centralWavelength) {
		this.centralWavelength = centralWavelength;
	}

	public double getWavelengthBandWidth() {
		return wavelengthBandWidth;
	}

	public void setWavelengthBandWidth(double wavelengthBandWidth) {
		this.wavelengthBandWidth = wavelengthBandWidth;
	}

	public void handleSignal(Signal s) {
		attenuateSignal(s);
		sendSignal(s);
	}

	private void sendSignal(Signal s) {
		for (int i = s.getWavelengthListSize()-1; i > -1 ; i--) {
			if (s.getWavelength(i) < minBand || s.getWavelength(i) > maxBand) {
				s.dropWavelength(s.getWavelength(i));
			}
			
		}
		getOutConnector().handleSignal(s);
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower - powerLoss);

	}

	public double getPowerLoss() {
		return powerLoss;
	}

	public void setPowerLoss(double powerLoss) {
		this.powerLoss = powerLoss;
	}

	public double getMinBand() {
		return minBand;
	}

	public void setMinBand(double minBand) {
		this.minBand = minBand;
	}

	public double getMaxBand() {
		return maxBand;
	}

	public void setMaxBand(double maxBand) {
		this.maxBand = maxBand;
	}
}
