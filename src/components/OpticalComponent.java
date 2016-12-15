package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OpticalComponent{
	private double insertionLoss;
	private double returnLoss;
	private double connectorAttenuance;
	private String label;
	private Point p;
	private int height;
	private int width;
	private boolean selected=false;
	
	
	public OpticalComponent(String str,Point p,int height,int width){
		this.label=str;
		this.p=p;
		this.height=height;
		this.width=width;
	}
	
	public OpticalComponent(OpticalComponent c){
		this.insertionLoss=c.getInsertionLoss();
		this.returnLoss=c.getReturnLoss();
		this.connectorAttenuance=c.getConnectorAttenuance();
		this.label=c.getLabel();
		this.p=c.getP();
		this.height=c.getHeight();
		this.width=c.getWidth();
	}
	
	public double getInsertionLoss() {
		return insertionLoss;
	}
	public void setInsertionLoss(double insertionLoss) {
		this.insertionLoss = insertionLoss;
	}
	public double getReturnLoss() {
		return returnLoss;
	}
	public void setReturnLoss(double returnLoss) {
		this.returnLoss = returnLoss;
	}
	public double getConnectorAttenuance() {
		return connectorAttenuance;
	}
	public void setConnectorAttenuance(double connectorAttenuance) {
		this.connectorAttenuance = connectorAttenuance;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Sad_Pepe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(img!=null){
			if(selected){;
				g.drawImage(img,p.x,p.y,width,height,Color.PINK,null);
				
			}
			else{
				g.drawImage(img,p.x,p.y,width,height,null);
			}
		}
		else{
			g.fillRect(p.x, p.y, width, height);
		}
		if(label!=null){
			g.setColor(Color.WHITE);
			g.drawString(label, p.x+width/2, p.y+width/2);
		}
	}
	
	public boolean contains(Point p){
		if(Math.abs(this.getP().x-p.x)<=width && Math.abs(this.getP().y-p.y)<=height)
				return true;
		else return false;
	}
}
