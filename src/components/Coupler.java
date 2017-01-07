package components;

import java.awt.Point;
import java.util.ArrayList;

public class Coupler extends OpticalComponent {

	private int numberOfInputs;
	private double couplingLoss; // loss of signal's power in dB
	private int handleMethodCallTimes = 0;
	private ArrayList<Signal> inSignals = new ArrayList<Signal>();
	private Fiber fiberOut;

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
			double outputPower;
			double totalInputPower = 0;
			for (Signal sig : inSignals) {
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
			for (int i = 0; i < size; i++)
				outputSignal.addWavelength(s.getWavelength(i));
		}
		fiberOut.handleSignal(outputSignal);
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower - couplingLoss);
	}

}
