package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

import gui.Console;

public class Fiber implements Serializable{
	private double length; // length in km
	private double attenuance; // loss in dB/km
	public OpticalComponent inC;
	public OpticalComponent outC;
	private double connectorAttenuance; // loss on connector in dB
	// private ComponentList outCType;
	private boolean selected = false;
	private Point fiberP;
	private Fiber f;
	public Fiber(OpticalComponent c1, OpticalComponent c2) {
		inC = c1;
		outC = c2;
		length = 1;
		attenuance = 0.3;
		connectorAttenuance = 0.1;
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

	public double getConnectorAttenuance() {
		return connectorAttenuance;
	}

	public void setConnectorAttenuance(double connectorAttenuance) {
		this.connectorAttenuance = connectorAttenuance;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		if (selected) {
			g2.setColor(Color.PINK);
		}
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(inC.getP().x + 35, inC.getP().y + 25, outC.getP().x, outC.getP().y + 25);
		g2.setStroke(new BasicStroke());
		g2.setColor(Color.BLACK);
		g.drawString(String.valueOf(length), (inC.getP().x + outC.getP().x) / 2,
				(inC.getP().y + outC.getP().y) / 2 + 22);
		setFiberP(new Point((inC.getP().x + outC.getP().x) / 2, (inC.getP().y + outC.getP().y) / 2 + 22));
	}

	public void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		double loss = length * attenuance + 2 * connectorAttenuance;
		s.setPower(outPower - loss);
	}

	public void handleSignal(Signal s) {
		attenuateSignal(s);
		if (outC instanceof Transmitter) {
			System.out.println("ERROR");
		} else if (outC instanceof Receiver) {
			Receiver r = (Receiver) outC;
			r.handleSignal(s);
		} else if (outC instanceof Multiplexer) {
			Multiplexer m = (Multiplexer) outC;
			m.handleSignal(s);
		} else if (outC instanceof Demultiplexer) {
			Demultiplexer dm = (Demultiplexer) outC;
			dm.handleSignal(s);
		} else if (outC instanceof Filter) {
			Filter f = (Filter) outC;
			f.handleSignal(s);
		} else if (outC instanceof Coupler) {
			Coupler coup = (Coupler) outC;
			coup.handleSignal(s);
		} else if (outC instanceof Decoupler) {
			Decoupler decoup = (Decoupler) outC;
			decoup.handleSignal(s);
		} else if (outC instanceof AddDropMux) {
			AddDropMux admux = (AddDropMux) outC;
			admux.handleSignal(s);
		} else if (outC instanceof Amplifier) {
			Amplifier amp = (Amplifier) outC;
			amp.handleSignal(s);
		} else if (outC instanceof WavelengthConverter) {
			WavelengthConverter wc = (WavelengthConverter) outC;
			wc.handleSignal(s);
		} else if (outC instanceof CrossConnect) {
			CrossConnect cc = (CrossConnect) outC;
			cc.handleSignal(s, f);
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Point getFiberP() {
		return fiberP;
	}

	public void setFiberP(Point fiberP) {
		this.fiberP = fiberP;
	}
	
	public void setOwnReference (Fiber fib) {
		this.f=fib;
	}

}
