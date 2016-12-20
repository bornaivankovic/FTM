package components;

import java.awt.Point;

public class WavelengthConverter extends OpticalComponent {
	
	private int minBandwidth;
	private int maxBandwidth;

	public WavelengthConverter(String str, Point p, int height, int width,int minBand, int maxBand) {
		super(str, p, height, width);
		this.minBandwidth = minBand;
		this.maxBandwidth = maxBand;
	}
	
	public WavelengthConverter(OpticalComponent c) {
		super(c);
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
	
}
	

