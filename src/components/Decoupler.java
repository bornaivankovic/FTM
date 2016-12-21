package components;

import java.awt.Point;

public class Decoupler extends OpticalComponent {
	
	private int numberOfOutputs;
	private double couplingLoss;
	@SuppressWarnings("unused")
	private double decouplingRatio;

	public Decoupler(String str, Point p, int height, int width, int numberOfOutputs,int couplingLoss) {
		super(str, p, height, width);
		this.numberOfOutputs = numberOfOutputs;
		this.couplingLoss = couplingLoss;
		this.decouplingRatio = 1/numberOfOutputs;
	}

	public Decoupler(OpticalComponent c) {
		super(c);
		this.couplingLoss = 0.25;
		this.numberOfOutputs = 2;
		this.decouplingRatio = 0.5;
		
	}
	
	public int getNumberOfOutputs() {
		return this.numberOfOutputs;
	}
	
	public void setNumberOutputs(int i) {
		this.numberOfOutputs = i;
		this.decouplingRatio = 1/numberOfOutputs;
	}
	
	public double getCouplingLoss () {
		return this.couplingLoss;
	}
	
	public void setCouplingLoss(double l) {
		this.couplingLoss = l;
	}

	
}
