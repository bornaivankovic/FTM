package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import components.Fiber;
import components.OpticalComponent;
import components.Receiver;
import components.Signal;
import components.Transmitter;

public class Canvas extends JPanel {

	private static ArrayList<OpticalComponent> komponente;
	private static ArrayList<Fiber> vlakna;

	private Point mousePt = new Point(800 / 2, 600 / 2);
	private Rectangle mouseRect = new Rectangle();
	private boolean selecting = false;
	Console console = null;

	public Canvas() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePt = e.getPoint();
				if (e.isShiftDown()) {
					// Node.selectToggle(nodes, mousePt);
				} else if (e.isPopupTrigger()) {
					selectOne(mousePt);
					showPopup(e);
				} else if (selectOne(mousePt)) {
					selecting = false;
				} else {
					selectNone();
					selecting = true;
				}
				e.getComponent().repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				selecting = false;
				mouseRect.setBounds(0, 0, 0, 0);
				if (e.isPopupTrigger()) {
					showPopup(e);
				}
				e.getComponent().repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			Point delta = new Point();

			@Override
			public void mouseDragged(MouseEvent e) {
				if (selecting) {
					mouseRect.setBounds(Math.min(mousePt.x, e.getX()), Math.min(mousePt.y, e.getY()),
							Math.abs(mousePt.x - e.getX()), Math.abs(mousePt.y - e.getY()));
					selectRect(mouseRect);
				} else {
					delta.setLocation(e.getX() - mousePt.x, e.getY() - mousePt.y);
					updatePosition(delta);
					mousePt = e.getPoint();
				}
				e.getComponent().repaint();
			}
		});

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"), "delete");
		getActionMap().put("delete", new DeleteAction("delete"));
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), "connect");
		getActionMap().put("connect", new ConnectAction("connect"));

		setBackground(Color.WHITE);
		komponente = new ArrayList<OpticalComponent>();
		vlakna = new ArrayList<Fiber>();
	}

	public void addComponent(OpticalComponent component, Point p) {
		// OpticalComponent c = new OpticalComponent(component);
		OpticalComponent c = null;
		if (component instanceof Transmitter) {
			c = new Transmitter(component);
		} else if (component instanceof Receiver) {
			c = new Receiver(component);
		} else {
			c = new OpticalComponent(component);
		}
		c.setP(p);
		komponente.add(c);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (OpticalComponent opticalComponent : komponente) {
			opticalComponent.draw(g);
		}
		for (Fiber f : vlakna) {
			f.draw(g);
		}
		if (selecting) {
			g.setColor(Color.darkGray);
			g.drawRect(mouseRect.x, mouseRect.y, mouseRect.width, mouseRect.height);
		}
	}

	private void updatePosition(Point delta) {
		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {
				c.setP(new Point(c.getP().x + delta.x, c.getP().y + delta.y));
			}
		}
	}

	private void selectRect(Rectangle r) {
		for (OpticalComponent c : komponente) {
			c.setSelected(r.contains(c.getP()));
		}
	}

	private boolean selectOne(Point p) {
		for (OpticalComponent c : komponente) {
			if (c.contains(p)) {
				if (!c.isSelected()) {
					selectNone();
					c.setSelected(true);
				}
				// console.println(c.getLabel() + "|" + c.getInsertionLoss() +
				// "|" + c.getReturnLoss());
				return true;
			}
		}
		return false;
	}

	private void selectNone() {
		for (OpticalComponent c : komponente) {
			c.setSelected(false);
		}
	}

	private void showPopup(MouseEvent e) {
		Popup popup = new Popup(e, komponente);
	}

	public void deleteSelected() {
		ArrayList<OpticalComponent> zaIzbrisati = new ArrayList<>();
		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {
				zaIzbrisati.add(c);
			}
		}
		komponente.removeAll(zaIzbrisati);
	}

	private class DeleteAction extends AbstractAction {

		public DeleteAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteSelected();
			repaint();
		}

	}

	private class ConnectAction extends AbstractAction {
		public ConnectAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			OpticalComponent c1 = null, c2 = null;
			for (OpticalComponent c : komponente) {
				if (c.isSelected()) {
					if (c1 == null)
						c1 = c;
					else
						c2 = c;
				}
			}
			vlakna.add(new Fiber(c1, c2));
			repaint();
		}
	}

	public int getComponentListLength() {
		return komponente.size();
	}

	public int getFiberListLength() {
		return vlakna.size();
	}

	void startSimulation() {
		Signal sig1 = new Signal();
		for (int i = 0; i < komponente.size(); i++) {
			OpticalComponent tmp = komponente.get(i);
			String label = tmp.getLabel();
			// switch (label) {
			// case "ADM":
			//
			// break;
			// case "AMP":
			//
			// break;
			// case "COUP":
			//
			// break;
			// case "XC":
			//
			// break;
			// case "DECOUP":
			//
			// break;
			// case "DEMUX":
			//
			// break;
			// case "FILTER":
			//
			// break;
			// case "MUX":
			//
			// break;
			// case "RX":
			// Receiver rx = (Receiver) tmp;
			// int min = (int) rx.getMinSensitivity();
			// int max = (int) rx.getMaxSensitivity();
			// for (int j = 0; j < sig1.getWavelengthListSize(); j++) {
			// if (min < sig1.getWavelength(j) && sig1.getWavelength(j) < max)
			// console.println("Prijamnik je detektirao valnu duljinu: " +
			// sig1.getWavelength(j));
			// }
			// break;
			// case "TX":
			// Transmitter tx = (Transmitter) tmp;
			// sig1.setPower(tx.getOutputPower());
			// int temp = (int) ((tx.getMaxWavelengthBand() -
			// tx.getMinWavelengthBand()) / tx.getNumberOfMods());
			// int temp2 = (int) tx.getMinWavelengthBand();
			// console.println("Izlazna snaga signala predajnika je : " +
			// sig1.getPower() + "dB");
			// console.println("valne duljine signala su (u nanometrima): ");
			// for (int j = 0; j < tx.getNumberOfMods(); j++) {
			// temp2 = temp2 + temp;
			// sig1.addWavelength(temp2);
			// console.println("Valna duljina: " + temp2);
			// }
			//
			// break;
			// case "WAC":
			//
			// break;
			// default:
			// break;
			// }
			if (label.equals("Rx")) {
				Receiver rx = (Receiver) tmp;
				int min = (int) rx.getMinWavelength();
				int max = (int) rx.getMaxWavelength();

				Fiber f = findFiber(komponente.get(i - 1), rx);
				sig1.setPower(sig1.getPower() - f.getAttenuance() * f.getLength());
				if (sig1.getPower() >= rx.getMinSensitivity() && sig1.getPower() <= rx.getMaxSensitivity()) {
					console.println("Snaga primljenog signala: " + sig1.getPower() + "dB");
				} else {
					console.println("Prijamnik ne moze detektirati signal dane snage(" + sig1.getPower() + ")");
					continue;
				}

				String s = "";
				for (int j = 0; j < sig1.getWavelengthListSize(); j++) {
					if (min < sig1.getWavelength(j) && sig1.getWavelength(j) < max)
						s += sig1.getWavelength(j) + ",";
				}
				console.println("Prijamnik je detektirao valne duljine: " + s);
			} else if (label.equals("Tx")) {
				Transmitter tx = (Transmitter) tmp;
				sig1.setPower(tx.getOutputPower());
				int temp = (int) ((tx.getMaxWavelengthBand() - tx.getMinWavelengthBand()) / tx.getNumberOfMods());
				int temp2 = (int) tx.getMinWavelengthBand();
				console.println("Izlazna snaga signala predajnika je : " + sig1.getPower() + "dB");
				String s = "";
				for (int j = 0; j < tx.getNumberOfMods(); j++) {
					temp2 = temp2 + temp;
					sig1.addWavelength(temp2);
					s += temp2 + ",";
				}
				console.println("valne duljine signala su (u nanometrima): " + s);
			}
		}
	}

	private Fiber findFiber(OpticalComponent c1, OpticalComponent c2) {
		for (Fiber f : vlakna) {
			if (f.c1.equals(c1) && f.c2.equals(c2) || f.c1.equals(c2) && f.c2.equals(c1)) {
				return f;
			}
		}
		return null;
	}

}
