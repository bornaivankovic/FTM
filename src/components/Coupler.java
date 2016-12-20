package components;

import java.awt.Point;

public class Coupler extends OpticalComponent {
	
	private int numberOfInputs;
	private double couplingLoss;

	public Coupler(String str, Point p, int height, int width, int numberOfInputs,int couplingLoss) {
		super(str, p, height, width);
		this.numberOfInputs = numberOfInputs;
		this.couplingLoss = couplingLoss;
	}

	public Coupler(OpticalComponent c) {
		super(c);
		
	}
	
	public int getNumberOfInputs() {
		return this.numberOfInputs;
	}
	
	public void setNumberOfInputs(int i) {
		this.numberOfInputs = i;
	}
	
	public double getCouplingLoss () {
		return this.couplingLoss;
	}
	
	public void setCouplingLoss(double l) {
		this.couplingLoss = l;
	}

	
}
