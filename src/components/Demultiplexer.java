package components;

import java.awt.Point;
import java.util.ArrayList;

public class Demultiplexer extends OpticalComponent {

	private int numOfOutputs;
	private double multiPlexingLoss;
	private int min = 0;
	private int max = 1600;
	private ArrayList<Integer> minWavelength = new ArrayList<Integer>();
	private ArrayList<Integer> maxWavelength = new ArrayList<Integer>();;

	public Demultiplexer(String str, Point p, int height, int width, int outputs, double loss) {
		super(str, p, height, width);
		numOfOutputs = outputs;
		multiPlexingLoss = loss;
		for (int i = 0; i < outputs; i++) {
			minWavelength.add(i, min);
			maxWavelength.add(i, max);
		}
	}

	public Demultiplexer(OpticalComponent c) {
		super(c);
		numOfOutputs = 2;
		multiPlexingLoss = 0.25;
		for (int i = 0; i < 2; i++) {
			minWavelength.add(i, min);
			maxWavelength.add(i, max);
		}
		setImgPath("D:\\Code\\Java\\FTM\\icons\\demux.png");

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

	public void setDemultiplexingLoss(double loss) {
		multiPlexingLoss = loss;
	}

	public double getDemultiplexingLoss() {
		return multiPlexingLoss;
	}

}
