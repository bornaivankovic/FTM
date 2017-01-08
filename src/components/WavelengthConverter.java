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
		this.maxBandwidth = 1600;
		this.minOutBand = 1600;
		this.maxOutBand = 1700;
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
	
}
	

