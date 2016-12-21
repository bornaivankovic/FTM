package components;

import java.awt.Point;
import java.util.ArrayList;

public class Multiplexer extends OpticalComponent{
	
	private int numOfInputs;
	private double multiPlexingLoss;
	private int min=0;
	private int max=1600;
	private ArrayList<Integer> minWavelength = new ArrayList<Integer>();
	private ArrayList<Integer> maxWavelength= new ArrayList<Integer>();;
	
	public Multiplexer(OpticalComponent c) {
		super(c);
		this.numOfInputs = 2;
		this.multiPlexingLoss = 0.25;
		
	}
	
	public Multiplexer(String str,Point p,int height,int width, int inputs, double loss) {
		super(str, p, height, width);
		this.numOfInputs = inputs;
		this.multiPlexingLoss = loss; 
		for (int i=0; i<inputs; i++) {
			minWavelength.add(i, min);
			maxWavelength.add(i, max);
		}
	}
	
	public void setChanBand (int chan, int min, int max) {
		minWavelength.add(chan, min);
		maxWavelength.add(chan, max);
	}
	
	public int getMinChanBand (int chan) {
		return minWavelength.get(chan);
	}
	
	public int getMaxChanBand (int chan) {
		return maxWavelength.get(chan);
	}
	
	//public double multiplexingLoss (double power) {
	//	return power-multiPlexingLoss;
	//}
	
	public void setMultiplexingLoss (double loss) {
		this.multiPlexingLoss = loss;
	}
	
	public double getMultiplexingLoss () {
		return multiPlexingLoss;
	}

}
