package components;

import java.awt.Point;
import java.util.ArrayList;

public class Multiplexer extends OpticalComponent {

	private int numOfInputs;
	private double multiPlexingLoss;              // loss of signal's power in dB
	private int min = 0;
	private int max = 1600;
	private ArrayList<Integer> minWavelength = new ArrayList<Integer>();
	private ArrayList<Integer> maxWavelength = new ArrayList<Integer>();
	private ArrayList<Signal> inSignals = new ArrayList<Signal>();
	private ArrayList<Integer> outputWavelengths = new ArrayList<Integer>();
	private int handleMethodCallTimes = 0;
	private Fiber fiberOut;

	public Multiplexer(OpticalComponent c) {
		super(c);
		numOfInputs = 2;
		multiPlexingLoss = 0.25;
		setImgPath("D:\\Code\\Java\\FTM\\icons\\mux.png");
	}

	public Multiplexer(String str, Point p, int height, int width, int inputs, double loss) {
		super(str, p, height, width);
		numOfInputs = inputs;
		multiPlexingLoss = loss;
		for (int i = 0; i < inputs; i++) {
			minWavelength.add(i, min);
			maxWavelength.add(i, max);
		}
	}

	public void setChanBand(int chan, int min, int max) {
		minWavelength.add(chan, min);
		maxWavelength.add(chan, max);
	}

	public int getMinChanBand(int chan) {
		return minWavelength.get(chan);
	}

	public int getMaxChanBand(int chan) {
		return maxWavelength.get(chan);
	}

	// public double multiplexingLoss (double power) {
	// return power-multiPlexingLoss;
	// }

	public void setMultiplexingLoss(double loss) {
		multiPlexingLoss = loss;
	}

	public double getMultiplexingLoss() {
		return multiPlexingLoss;
	}

	public void handleSignal(Signal s) {
		handleMethodCallTimes++;
		attenuateSignal(s); 
		inSignals.add(s);
		if (handleMethodCallTimes == numOfInputs) {
			double outputPower;
			double totalInputPower=0;
			for (Signal sig : inSignals) {
				totalInputPower += sig.getPower();
			}
			outputPower = totalInputPower / numOfInputs ;
			sendSignal(outputPower);
		}
	}

	private void sendSignal(double power) {
		Signal outputSignal = new Signal(power);
		for (Signal s : inSignals) {
			int size = s.getWavelengthListSize();
			for (int i = 0; i<size; i++)
				outputSignal.addWavelength(s.getWavelength(i));
		}
		fiberOut.handleSignal(outputSignal);
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower-multiPlexingLoss);
	}

}
