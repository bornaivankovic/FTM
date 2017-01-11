package components;

import java.awt.Point;

import gui.Console;

public class Amplifier extends OpticalComponent {

	private double gain;
	private double gainSaturation; // neka snaga nakon koje nema više pojačanja

	public Amplifier(String str, Point p, int height, int width, double gain, double saturation) {
		super(str, p, height, width);
		this.gain = gain;
		gainSaturation = saturation;
		this.setInsertionLoss(0.3);
		this.setReturnLoss(0.2);
	}

	public Amplifier(OpticalComponent c) {
		super(c);
		gain = 20;
		gainSaturation = 80;
		this.setInsertionLoss(0.3);
		this.setReturnLoss(0.2);
		setImgPath("amp.png");
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

	public void handleSignal(Signal s) {
		Console.getConsoleInstance().println("_____________");
		Console.getConsoleInstance().println("Amplifier:");
		Console.getConsoleInstance().println("Input signal's power " + s.getPower() + "dB.");
		amplifySignal(s);
		sendSignal(s);
	}

	private void sendSignal(Signal s) {
		getOutConnector().handleSignal(s);
	}

	public void amplifySignal(Signal s) {
		double tempPower = s.getPower()+gain;
		tempPower = tempPower - this.getReturnLoss() - this.getInsertionLoss();
		if (tempPower>gainSaturation)
			s.setPower(gainSaturation);
		else
			s.setPower(tempPower);
		Console.getConsoleInstance().println("Signal amplified...");
		Console.getConsoleInstance().println("Output signal's power " + s.getPower() + "dB.");
	}

}
