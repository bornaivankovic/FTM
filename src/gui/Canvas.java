package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import components.OpticalComponent;

public class Canvas extends JPanel{
	
	private ArrayList<OpticalComponent> komponente;

	public Canvas(){
		setBackground(Color.WHITE);
		komponente=new ArrayList<OpticalComponent>();
	}
	
	public void addComponent(OpticalComponent component,Point p){
		OpticalComponent c=new OpticalComponent(component);
		c.setP(p);
		komponente.add(c);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (OpticalComponent opticalComponent : komponente) {
			opticalComponent.draw(g);
		}
	}
}
