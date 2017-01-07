package components;

import java.awt.Point;

public class Filter extends OpticalComponent {

	private double centralWavelength;
	private double wavelengthBandWidth; // nekakva peek-to-peek vrijednost
										// pomocu koje cemo uzet pola
										// vrijednosti manje od centralne i
	private double minBand;
	private double maxBand; // pola vrijednosti iznad centralne valne uljine
	private Fiber fiberOut;
	private double powerLoss = 0.2;  // DEFAULTNI gubitak na komponenti

	public Filter(String str, Point p, int height, int width, double centWave, double wavelengthBand) {
		super(str, p, height, width);
		centralWavelength = centWave;
		wavelengthBandWidth = wavelengthBand;
		minBand = centralWavelength - wavelengthBandWidth / 2;
		maxBand = centralWavelength + wavelengthBandWidth / 2;
	}

	public Filter(OpticalComponent c) {
		super(c);
		centralWavelength = 1550;
		wavelengthBandWidth = 100;
		minBand = 1500;
		maxBand = 1600;
		// setImgPath("D:\\Code\\Java\\FTM\\icons\\filter.png");
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
		for (int i=0; i<s.getWavelengthListSize(); i++) {
			if (s.getWavelength(i)<minBand || s.getWavelength(i)>maxBand)
				s.dropWavelength(s.getWavelength(i));
		}
		fiberOut.handleSignal(s);
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower-powerLoss);
		
	}

	public double getPowerLoss() {
		return powerLoss;
	}

	public void setPowerLoss(double powerLoss) {
		this.powerLoss = powerLoss;
	}
	
	

}
