package components;

import java.awt.Color;
import java.awt.Graphics;

public class Fiber {
	private double length;
	private double attenuance;
	public OpticalComponent c1;
	public OpticalComponent c2;
	
	public Fiber(OpticalComponent c1,OpticalComponent c2){
		this.c1=c1;
		this.c2=c2;
	}
	
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getAttenuance() {
		return attenuance;
	}
	public void setAttenuance(double attenuance) {
		this.attenuance = attenuance;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(c1.getP().x, c1.getP().y, c2.getP().x, c2.getP().y);
	}

}
