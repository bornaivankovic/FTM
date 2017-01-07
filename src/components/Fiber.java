package components;

import java.awt.Color;
import java.awt.Graphics;

public class Fiber {
	private double length; // length in km
	private double attenuance; // loss in dB/km
	public OpticalComponent inC;
	public OpticalComponent outC;
	private double connectorAttenuance; // loss on connector in dB
	//private ComponentList outCType;

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

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(inC.getP().x, inC.getP().y, outC.getP().x, outC.getP().y);
		g.drawString(String.valueOf(length), (inC.getP().x + outC.getP().x) / 2, (inC.getP().y + outC.getP().y) / 2);
	}

	public void transferSignalOverFiber(Signal s) {
		double outPower = s.getPower();
		double loss = length * attenuance + 2 * connectorAttenuance;
		s.setPower(outPower);
	}

	public void handleSignal(Signal s) {
		transferSignalOverFiber(s);
		if (outC instanceof Transmitter) {
			System.out.println("ERROR");
		}
		else if (outC instanceof Receiver) {
			Receiver r = (Receiver)outC;
			r.handleSignal(s);
		}
		else if (outC instanceof Multiplexer) {
			Multiplexer m = (Multiplexer)outC;
			m.handleSignal(s);
		}
		else if (outC instanceof Demultiplexer) {
			Demultiplexer dm = (Demultiplexer)outC;
			dm.handleSignal(s);
		}
		else if (outC instanceof Filter) {
			Filter f = (Filter) outC;
			f.handleSignal(s);
		}
		else if (outC instanceof Coupler) {
			Coupler coup = (Coupler)outC;
			coup.handleSignal(s);
		}
		else if (outC instanceof Decoupler) {
			Decoupler decoup = (Decoupler)outC;
			decoup.handleSignal(s);
		}
		else if (outC instanceof AddDropMux) {
			AddDropMux admux = (AddDropMux)outC;
			admux.handleSignal(s);
		}
		else if (outC instanceof Amplifier) {
			Amplifier amp = (Amplifier)outC;
			amp.handleSignal(s);
		}
		else if (outC instanceof WavelengthConverter) {
			
		}
		else if (outC instanceof CrossConnect) {
	
		}
		
		/*
		 switch (outCType) {
		 case TX:
		 System.out.println("ERROR");
		 case RX:
		 Receiver r = (Receiver)outC;
		 r.handleSignal(s);
		 case MUX:
		 Multiplexer m = (Multiplexer)outC;
		 m.handleSignal(s);
		 case DMUX:
		 Demultiplexer dm = (Demultiplexer)outC;
		 dm.handleSignal(s);
		 case FILTER:
		 Filter f = (Filter) outC;
		 f.handleSignal(s);
		 case COUP:
		 Coupler coup = (Coupler)outC;
		 coup.handleSignal(s);
		 case DECOUP:
		 Decoupler decoup = (Decoupler)outC;
		 decoup.handleSignal(s);
		 case ADMUX:
		 AddDropMux admux = (AddDropMux)outC;
		 admux.handleSignal(s);
		 case AMP:
		 Amplifier amp = (Amplifier)outC;
		 amp.handleSignal(s);
		 case WLCONV:
		 case CROSSC:
		 }
		 */

	}

}
