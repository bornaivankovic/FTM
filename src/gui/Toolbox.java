package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import components.AddDropMux;
import components.Amplifier;
import components.Coupler;
import components.CrossConnect;
import components.Decoupler;
import components.Demultiplexer;
import components.Filter;
import components.Multiplexer;
import components.OpticalComponent;
import components.Receiver;
import components.Transmitter;
import components.WavelengthConverter;

public class Toolbox extends JPanel {
	// TODO dodaj JScrollPanel
	private final int WIDTH = 100;
	private final int HEIGHT = 655;
	private ArrayList<OpticalComponent> komponente = new ArrayList<OpticalComponent>();

	public Toolbox() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		OpticalComponent tmp = new OpticalComponent("L", new Point(0, 0), 50, 35);
		tmp.setLabel("ADM");
		tmp.setP(new Point(32, 0));
		komponente.add(new AddDropMux(tmp));
		tmp.setLabel("AMP");
		tmp.setP(new Point(32, 60));
		komponente.add(new Amplifier(tmp));
		tmp.setLabel("COUP");
		tmp.setP(new Point(32, 120));
		komponente.add(new Coupler(tmp));
		tmp.setLabel("XC");
		tmp.setP(new Point(32, 180));
		komponente.add(new CrossConnect(tmp));
		tmp.setLabel("DECOUP");
		tmp.setP(new Point(32, 240));
		komponente.add(new Decoupler(tmp));
		tmp.setLabel("DEMUX");
		tmp.setP(new Point(32, 300));
		komponente.add(new Demultiplexer(tmp));
		tmp.setLabel("FILTER");
		tmp.setP(new Point(32, 360));
		komponente.add(new Filter(tmp));
		tmp.setLabel("MUX");
		tmp.setP(new Point(32, 420));
		komponente.add(new Multiplexer(tmp));
		tmp.setLabel("Rx");
		tmp.setP(new Point(32, 480));
		komponente.add(new Receiver(tmp));
		tmp.setLabel("Tx");
		tmp.setP(new Point(32, 540));
		komponente.add(new Transmitter(tmp));
		tmp.setLabel("WAC");
		tmp.setP(new Point(32, 600));
		komponente.add(new WavelengthConverter(tmp));

		// for(int i=0;i<5;i++){
		// komponente.add(new OpticalComponent(String.valueOf(i),new
		// Point(0,i*60),50,50));
		// }
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (OpticalComponent opticalComponent : komponente) {
			opticalComponent.draw(g);
		}
	}

	public OpticalComponent getOpticalComponent(Point p) {
		int i = findComponent(p);
		OpticalComponent component = null;
		if (i >= 0)
			component = komponente.get(i);
		return component;
	}

	private int findComponent(Point p) {
		for (OpticalComponent opticalComponent : komponente) {
			if (new Rectangle(opticalComponent.getP(),
					new Dimension(opticalComponent.getWidth(), opticalComponent.getHeight())).contains(p))
				return komponente.indexOf(opticalComponent);
		}
		return -1;
	}

}
