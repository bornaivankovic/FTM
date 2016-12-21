package components;

import java.awt.Point;

public class Decoupler extends OpticalComponent {

	private int numberOfOutputs;
	private double couplingLoss;
	@SuppressWarnings("unused")
	private double decouplingRatio;

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

}
