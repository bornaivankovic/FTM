package components;

import java.awt.Point;

public class Filter extends OpticalComponent {
	
	private double centralWavelength;
	private double wavelengthBandWidth; // nekakva peek-to-peek vrijednost pomocu koje cemo uzet pola vrijednosti manje od centralne i
										// pola vrijednosti iznad centralne valne uljine
	
	public Filter(String str, Point p, int height, int width, double centWave, double wavelengthBand) {
		super(str, p, height, width);
		this.centralWavelength = centWave;
		this.wavelengthBandWidth = wavelengthBand;
	}
	
	public Filter(OpticalComponent c) {
		super(c);
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
