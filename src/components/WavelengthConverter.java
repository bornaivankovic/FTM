package components;

import java.awt.Point;

public class WavelengthConverter extends OpticalComponent {
	
	private int minBandwidth;
	private int maxBandwidth;
	private int minOutBand;
	private int maxOutBand;

	public WavelengthConverter(String str, Point p, int height, int width,int minBand, int maxBand, int minOutBand, int maxOutBand) {
		super(str, p, height, width);
		this.minBandwidth = minBand;
		this.maxBandwidth = maxBand;
		this.minOutBand = minOutBand;
		this.maxOutBand = maxOutBand;
	}
	
	public WavelengthConverter(OpticalComponent c) {
		super(c);
		this.minBandwidth = 1500;
		this.maxBandwidth = 1550;
		this.minOutBand = 1550;
		this.maxOutBand = 1600;
		this.setInsertionLoss(0.5);
		this.setReturnLoss(0.5);
		setImgPath("wc.png");
	}
	
	public int getMinBandwidth() {
		return this.minBandwidth;
	}
	
	public void setMinBandwidth(int minBand) {
		this.minBandwidth = minBand;
	}
	public int getMaxBandwidth() {
		return this.maxBandwidth;
	}
	
	public void setMaxBandwidth(int maxBand) {
		this.maxBandwidth = maxBand;
	}
	
	public int getOutMinBandwidth() {
		return this.minOutBand;
	}
	
	public void setOutMinBandwidth(int minBand) {
		this.minOutBand = minBand;
	}
	public int getOutMaxBandwidth() {
		return this.maxOutBand;
	}
	
	public void setOutMaxBandwidth(int maxBand) {
		this.maxOutBand = maxBand;
	}

	public void handleSignal(Signal s) {
		attenuateSignal(s);
		Signal outputSignal = new Signal (s.getPower());
		int offset; 
		int centW1 = (minBandwidth+maxBandwidth)/2;
		int centW2 = (minOutBand+maxOutBand)/2;
		if (centW1>centW2)
			offset = centW1-centW2;
		else
			offset = centW2-centW1;
		for (int i = s.getWavelengthListSize()-1; i > -1 ; i--) {
			if (s.getWavelength(i) < minBandwidth || s.getWavelength(i) > maxBandwidth) {
				s.dropWavelength(s.getWavelength(i));
			}
		}
		for (int i = 0; i < s.getWavelengthListSize(); i++) {
			int w = s.getWavelength(i);
			outputSignal.addWavelength(w+offset);
		}
		getOutConnector().handleSignal(outputSignal);
	}

	private void attenuateSignal(Signal s) {
		double pow = s.getPower();
		pow = pow - this.getReturnLoss() - this.getInsertionLoss();
		s.setPower(pow);
	}
	
}
	

