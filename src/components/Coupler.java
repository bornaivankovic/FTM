package components;

import java.awt.Point;
import java.util.ArrayList;

import gui.Console;

public class Coupler extends OpticalComponent {

	private int numberOfInputs;
	private double couplingLoss; // loss of signal's power in dB
	private int handleMethodCallTimes = 0;
	private ArrayList<Signal> inSignals = new ArrayList<Signal>();

	public Coupler(String str, Point p, int height, int width, int numberOfInputs, int couplingLoss) {
		super(str, p, height, width);
		this.numberOfInputs = numberOfInputs;
		this.couplingLoss = couplingLoss;
	}

	public Coupler(OpticalComponent c) {
		super(c);
		numberOfInputs = 2;
		couplingLoss = 0.2;
		setImgPath("coup.png");
	}

	public int getNumberOfInputs() {
		return numberOfInputs;
	}

	public void setNumberOfInputs(int i) {
		numberOfInputs = i;
	}

	public double getCouplingLoss() {
		return couplingLoss;
	}

	public void setCouplingLoss(double l) {
		couplingLoss = l;
	}

	public void handleSignal(Signal s) {
		handleMethodCallTimes++;
		attenuateSignal(s);
		inSignals.add(s);
		if (handleMethodCallTimes == numberOfInputs) {
			Console.getConsoleInstance().println("_____________");
			Console.getConsoleInstance().println("Coupler:");
			double outputPower;
			double totalInputPower = 0;
			for (Signal sig : inSignals) {
				Console.getConsoleInstance().println("Input signal's power " + sig.getPower() + "dB.");
				totalInputPower += sig.getPower();
			}
			outputPower = totalInputPower / numberOfInputs;
			sendSignal(outputPower);
		}
	}

	private void sendSignal(double power) {
		Signal outputSignal = new Signal(power);
		for (Signal s : inSignals) {
			int size = s.getWavelengthListSize();
			for (int i = 0; i < size; i++) {
				int w1 = s.getWavelength(i);
				if (!outputSignal.hasWavelenght(w1))
					outputSignal.addWavelength(s.getWavelength(i));
			}
		}
		outputSignal.sortWavelenght();
		Console.getConsoleInstance().println("Output signal's power " + outputSignal.getPower() + "dB.");
		getOutConnector().handleSignal(outputSignal);
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower - couplingLoss);
	}

}
