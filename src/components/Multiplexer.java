package components;

import java.awt.Point;
import java.util.ArrayList;

public class Multiplexer extends OpticalComponent {

	private int numOfInputs;
	private double multiPlexingLoss; // loss of signal's power in dB
	private int min = 1500;
	private int max = 1600;
	private ArrayList<Integer> minWavelength = new ArrayList<>();
	private ArrayList<Integer> maxWavelength = new ArrayList<>();
	private ArrayList<Signal> inSignals = new ArrayList<>();
	private ArrayList<Integer> outputWavelengths = new ArrayList<>();
	private int handleMethodCallTimes = 0;
	private int selectedPort = 0;
	private ArrayList<Fiber> inputFibers = new ArrayList<>();

	public Multiplexer(OpticalComponent c) {
		super(c);
		numOfInputs = 4;
		multiPlexingLoss = 0.25;
		setImgPath("mux.png");
		setInsertionLoss(0.3);
		setReturnLoss(0.2);
		for (int i = 0; i < numOfInputs; i++) {
			minWavelength.add(i, min + i * 5);
			maxWavelength.add(i, max - i * 5);
		}
	}

	public Multiplexer(String str, Point p, int height, int width, int inputs, double loss) {
		super(str, p, height, width);
		numOfInputs = inputs;
		multiPlexingLoss = loss;
		setInsertionLoss(0.3);
		setReturnLoss(0.2);
		for (int i = 0; i < inputs; i++) {
			minWavelength.add(i, min);
			maxWavelength.add(i, max);
		}
	}

	public void addInputFiber(Fiber f) {
		inputFibers.add(f);
		numOfInputs = inputFibers.size();
	}

	public void setChanBand(int chan, int min, int max) {
		minWavelength.remove(chan);
		maxWavelength.remove(chan);
		minWavelength.add(chan, min);
		maxWavelength.add(chan, max);
	}

	public int getMinChanBand(int chan) {
		return minWavelength.get(chan);
	}

	public int getHandleMethodCallTimes() {
		return handleMethodCallTimes;
	}

	public void setHandleMethodCallTimes(int handleMethodCallTimes) {
		this.handleMethodCallTimes = handleMethodCallTimes;
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

	public int getSelectedPort() {
		return selectedPort;
	}

	public void setSelectedPort(int selectedPort) {
		this.selectedPort = selectedPort;
	}

	public int getNumOfInputs() {
		return numOfInputs;
	}

	public void setNumOfInputs(int numOfInputs) {
		this.numOfInputs = numOfInputs;
	}

	public void handleSignal(Signal s) {
		handleMethodCallTimes++;
		attenuateSignal(s);
		inSignals.add(s);
		if (handleMethodCallTimes == numOfInputs) {
			double outputPower;
			double totalInputPower = 0;
			for (Signal sig : inSignals) {
				totalInputPower += sig.getPower();
			}
			outputPower = totalInputPower / numOfInputs;
			sendSignal(outputPower);
			inSignals.clear();
			setHandleMethodCallTimes(0);

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
		getOutConnector().handleSignal(outputSignal);
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		outPower = outPower - multiPlexingLoss - getReturnLoss() - getInsertionLoss();
		s.setPower(outPower);
	}

}
