package components;

import java.awt.Point;

public class Amplifier extends OpticalComponent {
	
	private double gain;
	private double gainSaturation; //neka snaga nakon koje nema više pojačanja
	
	public Amplifier(String str, Point p, int height, int width, double gain, double saturation) {
		super(str, p, height, width);
		this.gain = gain;
		this.gainSaturation = saturation;
	}
	
	public Amplifier(OpticalComponent c) {
		super(c);
		this.gain = 20;
		this.gainSaturation = 80;
	}

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

	public double getGainSaturation() {
		return gainSaturation;
	}

	public void setGainSaturation(double gainSaturation) {
		this.gainSaturation = gainSaturation;
	}

}
