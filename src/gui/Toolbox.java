package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import components.OpticalComponent;

public class Toolbox extends JPanel {
	//TODO dodaj JScrollPanel
	private final int WIDTH = 100;
	private final int HEIGHT = 600;
	private ArrayList<OpticalComponent> komponente = new ArrayList<OpticalComponent>();
	public Toolbox(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		for(int i=0;i<5;i++){
			komponente.add(new OpticalComponent(String.valueOf(i),new Point(0,i*60),50,50));
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (OpticalComponent opticalComponent : komponente) {
			opticalComponent.draw(g);
		}
	}
	
	public OpticalComponent getOpticalComponent(Point p){
		int i=findComponent(p);
		OpticalComponent component=null;
		if(i>=0)
			component=komponente.get(i);
		return component;
	}
	
	private int findComponent(Point p){
		for (OpticalComponent opticalComponent : komponente) {
			if(new Rectangle(opticalComponent.getP(),new Dimension(opticalComponent.getWidth(), opticalComponent.getHeight())).contains(p))
				return komponente.indexOf(opticalComponent);
		}
		return -1;
	}
	
}
