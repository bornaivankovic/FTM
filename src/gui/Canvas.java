package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import components.AddDropMux;
import components.Amplifier;
import components.Coupler;
import components.CrossConnect;
import components.Decoupler;
import components.Demultiplexer;
import components.Fiber;
import components.Filter;
import components.Multiplexer;
import components.OpticalComponent;
import components.Receiver;
import components.Transmitter;
import components.WavelengthConverter;
import util.ArrayListDeserialization;
import util.ArrayListSerialization;

public class Canvas extends JPanel {

	private static ArrayList<OpticalComponent> komponente;
	private static ArrayList<Fiber> vlakna;
	private static ArrayList<Transmitter> allTransmitters;

	private Point mousePt = new Point();
	private Rectangle mouseRect = new Rectangle();
	private boolean selecting = false;
	Console console = null;
	private double scale = 1;

	public Canvas() {
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				if (arg0.isControlDown()) {
					int i = arg0.getWheelRotation();
					if (i > 0) {
						for (int j = 0; j < i; j++) {
							scale += 0.1;
							repaint();
						}
					} else {
						for (int j = 0; j < Math.abs(i); j++) {
							scale -= 0.1;
							repaint();
						}
					}
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePt = e.getPoint();
				if (e.isShiftDown()) {
					selectToggle(mousePt);
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
		komponente = new ArrayList<>();
		vlakna = new ArrayList<>();
		allTransmitters = new ArrayList<>();
	}

	public void addComponent(OpticalComponent component, Point p) {
		// OpticalComponent c = new OpticalComponent(component);
		OpticalComponent c = null;
		if (component instanceof Transmitter) {
			c = new Transmitter(component);
			allTransmitters.add((Transmitter) c);
		} else if (component instanceof Receiver) {
			c = new Receiver(component, console);
		} else if (component instanceof AddDropMux) {
			c = new AddDropMux(component);
		} else if (component instanceof Amplifier) {
			c = new Amplifier(component);
		} else if (component instanceof Coupler) {
			c = new Coupler(component);
		} else if (component instanceof CrossConnect) {
			c = new CrossConnect(component);
		} else if (component instanceof Decoupler) {
			c = new Decoupler(component);
		} else if (component instanceof Demultiplexer) {
			c = new Demultiplexer(component);
		} else if (component instanceof Filter) {
			c = new Filter(component);
		} else if (component instanceof Multiplexer) {
			c = new Multiplexer(component);
		} else if (component instanceof WavelengthConverter) {
			c = new WavelengthConverter(component);
		} else {
			c = new OpticalComponent(component);
		}
		c.setP(p);

		int i = 1;
		for (OpticalComponent oc : komponente) {
			if (oc.getClass().equals(c.getClass())) {
				i++;
			}
		}
		c.setLabel(c.getLabel() + i);
		komponente.add(c);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int w = getWidth();// real width of canvas
		int h = getHeight();// real height of canvas
		// Translate used to make sure scale is centered
		g2.translate(w / 2, h / 2);
		g2.scale(scale, scale);
		g2.translate(-w / 2, -h / 2);
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
		for (Fiber f : vlakna) {
			f.setSelected(r.contains(f.getFiberP()));
		}
	}

	private boolean selectOne(Point p) {
		for (OpticalComponent c : komponente) {
			if (c.contains(p)) {
				if (!c.isSelected()) {
					selectNone();
					c.setSelected(true);
				}
				return true;
			}
		}
		for (Fiber f : vlakna) {
			if (f.contains(mousePt)) {
				if (!f.isSelected()) {
					selectNone();
					f.setSelected(true);
				}
				return true;
			}
		}
		return false;
	}

	private void selectNone() {
		for (OpticalComponent c : komponente) {
			c.setSelected(false);
		}
		for (Fiber f : vlakna) {
			f.setSelected(false);
		}
	}

	private void selectToggle(Point mousePt) {
		for (OpticalComponent c : komponente) {
			if (c.contains(mousePt)) {
				if (c.isSelected())
					c.setSelected(false);
				else
					c.setSelected(true);
			}
		}
		for (Fiber f : vlakna) {
			if (f.contains(mousePt)) {
				if (f.isSelected())
					f.setSelected(false);
				else
					f.setSelected(true);
			}
		}
	}

	private void showPopup(MouseEvent e) {
		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {
				Popup popup = new Popup(e, komponente);
			}
		}
		for (Fiber f : vlakna) {
			if (f.isSelected()) {
				FiberPopup fiberPopup = new FiberPopup(e, vlakna);
			}
		}
	}

	public void deleteSelected() {
		ArrayList<OpticalComponent> zaIzbrisatiC = new ArrayList<>();
		ArrayList<Fiber> zaIzbrisatiF = new ArrayList<>();
		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {
				zaIzbrisatiC.add(c);
				for (Fiber f : vlakna) {
					if (f.inC.equals(c) || f.outC.equals(c)) {
						zaIzbrisatiF.add(f);
					}
				}
			}
		}
		vlakna.removeAll(zaIzbrisatiF);
		komponente.removeAll(zaIzbrisatiC);
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

			if (c1.getP().x > c2.getP().x) {
				OpticalComponent tempC = c1;
				c1 = c2;
				c2 = tempC;
			}

			Fiber f = new Fiber(c1, c2);
			if (c1 instanceof Decoupler) {
				Decoupler dc = (Decoupler) c1;
				dc.addOutputFiber(f);
			} else if (c1 instanceof Demultiplexer) {
				Demultiplexer dm = (Demultiplexer) c1;
				dm.addOutputFiber(f);
			} else if (c1 instanceof CrossConnect) {
				CrossConnect cc = (CrossConnect) c1;
				cc.addOutPortFiber(f);
			}
			if (c2 instanceof CrossConnect) {
				f.setOwnReference(f);
				CrossConnect cc = (CrossConnect) c2;
				cc.addInPortFiber(f);
			} else if (c2 instanceof AddDropMux) {
				f.setOwnReference(f);
				AddDropMux adm = (AddDropMux) c2;
				adm.addInputFiber(f);
			} else if (c2 instanceof Multiplexer) {
				f.setOwnReference(f);
				Multiplexer mux = (Multiplexer) c2;
				mux.addInputFiber(f);
			} else if (c2 instanceof Coupler) {
				f.setOwnReference(f);
				Coupler coup = (Coupler) c2;
				coup.addInputFiber(f);
			}
			vlakna.add(f);
			c1.setOutConnector(f);
			c2.setInConnector(f);
			c2.setHasInConnector(true);
			c1.setSelected(false);
			c2.setSelected(false);
			repaint();
		}
	}

	public int getComponentListLength() {
		return komponente.size();
	}

	public int getFiberListLength() {
		return vlakna.size();
	}

	public void startSimulation() {
		for (Transmitter t : allTransmitters) {
			t.createSignal();
		}
	}

	public void resetSimulation() {
		Console.getConsoleInstance().println("Reseting...\n...\n...\n...\n...\nDone!");
		allTransmitters.clear();
		komponente.clear();
		vlakna.clear();
		repaint();
	}

	public void saveSimulation(File file) {
		ArrayListSerialization save = new ArrayListSerialization();
		save.serializeList(komponente, vlakna, allTransmitters, file);
		Console.getConsoleInstance().println("\nSaving...");
		Console.getConsoleInstance().println("Saved successfully to file: " + file.toString());
	}

	public void loadSimulation(File file) {
		Map<String, ArrayList> map;
		ArrayListDeserialization load = new ArrayListDeserialization();

		try {
			map = load.deserializeList(file);
			komponente = map.get("components");
			vlakna = map.get("fibers");
			allTransmitters = map.get("transmitters");
		} catch (Exception e) {
			e.printStackTrace();
		}
		repaint();
		Console.getConsoleInstance().println("Load complete!");
	}
}
