package components;

import java.awt.Point;

public class Amplifier extends OpticalComponent {

	private double gain;
	private double gainSaturation; // neka snaga nakon koje nema više pojačanja

	public Amplifier(String str, Point p, int height, int width, double gain, double saturation) {
		super(str, p, height, width);
		this.gain = gain;
		gainSaturation = saturation;
		setImgPath("D:\\Code\\Java\\FTM\\icons\\amp.png");
	}

	public Amplifier(OpticalComponent c) {
		super(c);
		gain = 20;
		gainSaturation = 80;
		setImgPath("D:\\Code\\Java\\FTM\\icons\\amp.png");
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
