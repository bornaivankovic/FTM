package components;

import java.awt.Point;

public class Coupler extends OpticalComponent {

	private int numberOfInputs;
	private double couplingLoss;

	public Coupler(String str, Point p, int height, int width, int numberOfInputs, int couplingLoss) {
		super(str, p, height, width);
		this.numberOfInputs = numberOfInputs;
		this.couplingLoss = couplingLoss;
	}

	public Coupler(OpticalComponent c) {
		super(c);
		numberOfInputs = 2;
		couplingLoss = 0.2;
		setImgPath("D:\\Code\\Java\\FTM\\icons\\coup.png");
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

}
