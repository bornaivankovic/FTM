package components;

import java.awt.Point;
import java.util.ArrayList;

public class Demultiplexer extends OpticalComponent {

	private int numOfOutputs;
	private double demultiPlexingLoss; // loss of signa's power in dB
	private int min = 0;
	private int max = 1600;
	private ArrayList<Integer> minWavelength = new ArrayList<Integer>();
	private ArrayList<Integer> maxWavelength = new ArrayList<Integer>();
	private ArrayList<Fiber> outputFibers = new ArrayList<Fiber>();
	private int selectedPort = 0;

	public Demultiplexer(String str, Point p, int height, int width, int outputs, double loss) {
		super(str, p, height, width);
		numOfOutputs = outputs;
		demultiPlexingLoss = loss;
		for (int i = 0; i < outputs; i++) {
			minWavelength.add(i, min);
			maxWavelength.add(i, max);
		}
	}

	public Demultiplexer(OpticalComponent c) {
		super(c);
		numOfOutputs = 4;
		demultiPlexingLoss = 0.25;
		
		for (int i = 0; i < numOfOutputs; i++) {
			minWavelength.add(i, min + i*5);
			maxWavelength.add(i, max - i*5);
		}
		setImgPath("demux.png");

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

	public int getNumOfOutputs() {
		return numOfOutputs;
	}

	public void setNumOfOutputs(int numOfOutputs) {
		this.numOfOutputs = numOfOutputs;
	}

	public int getSelectedPort() {
		return selectedPort;
	}

	public void setSelectedPort(int selectedPort) {
		this.selectedPort = selectedPort;
	}

	public int getMaxChanBand(int chan) {
		return maxWavelength.get(chan);
	}

	public void setDemultiplexingLoss(double loss) {
		demultiPlexingLoss = loss;
	}

	public double getDemultiplexingLoss() {
		return demultiPlexingLoss;
	}

	public void handleSignal(Signal s) {
		attenuateSignal(s);
		for (int i = 0; i < outputFibers.size(); i++) {
			Fiber fOut = outputFibers.get(i);
			Signal sigOut = new Signal(s.getPower());
			for (int j = 0; j < s.getWavelengthListSize(); j++) {
				if (s.getWavelength(j) > minWavelength.get(i) && s.getWavelength(j) < maxWavelength.get(i))
					sigOut.addWavelength(s.getWavelength(j));
			}
			fOut.handleSignal(sigOut);
		}
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower - demultiPlexingLoss);
	}
	
	public void addOutputFiber (Fiber f) {
		outputFibers.add(f);
	}

}
