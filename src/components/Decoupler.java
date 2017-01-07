package components;

import java.awt.Point;
import java.util.ArrayList;

public class Decoupler extends OpticalComponent {

	private int numberOfOutputs;
	private double couplingLoss;   // loss of signal's power in dB
	@SuppressWarnings("unused")
	private double decouplingRatio;
	private ArrayList<Fiber> outputFibers = new ArrayList<Fiber>();

	public Decoupler(String str, Point p, int height, int width, int numberOfOutputs, int couplingLoss) {
		super(str, p, height, width);
		this.numberOfOutputs = numberOfOutputs;
		this.couplingLoss = couplingLoss;
		decouplingRatio = 1 / numberOfOutputs;
	}

	public Decoupler(OpticalComponent c) {
		super(c);
		couplingLoss = 0.25;
		numberOfOutputs = 2;
		decouplingRatio = 0.5;
		setImgPath("D:\\Code\\Java\\FTM\\icons\\decoup.png");
	}

	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}

	public void setNumberOutputs(int i) {
		numberOfOutputs = i;
		decouplingRatio = 1 / numberOfOutputs;
	}

	public double getCouplingLoss() {
		return couplingLoss;
	}

	public void setCouplingLoss(double l) {
		couplingLoss = l;
	}

	public void handleSignal(Signal s) {
		attenuateSignal(s);
		for (int i=0; i<outputFibers.size(); i++) {
			Fiber fOut = outputFibers.get(i);
			Signal sigOut = new Signal(s.getPower()/numberOfOutputs);
			for (int j=0; j<s.getWavelengthListSize(); j++) {
					sigOut.addWavelength(s.getWavelength(j));
			}
			fOut.handleSignal(sigOut);
		}	
	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		s.setPower(outPower-couplingLoss);
	}
}
