package components;

import java.awt.Point;

public class Filter extends OpticalComponent {
	
	private double centralWavelength;
	private double wavelengthBandWidth; // nekakva peek-to-peek vrijednost pomocu koje cemo uzet pola vrijednosti manje od centralne i
	private double minBand;
	private double maxBand;					// pola vrijednosti iznad centralne valne uljine
	
	public Filter(String str, Point p, int height, int width, double centWave, double wavelengthBand) {
		super(str, p, height, width);
		this.centralWavelength = centWave;
		this.wavelengthBandWidth = wavelengthBand;
		this.minBand = this.centralWavelength - (this.wavelengthBandWidth/2);
		this.maxBand = this.centralWavelength + (this.wavelengthBandWidth/2);
	}
	
	public Filter(OpticalComponent c) {
		super(c);
		this.centralWavelength = 1550;
		this.wavelengthBandWidth = 100;
		this.minBand = 1500;
		this.maxBand = 1600;
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

}
