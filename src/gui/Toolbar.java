package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

	private StartAction start = new StartAction("Start");
	public GUI gui = null;

	public Toolbar() {
		JButton button = new JButton(start);
		this.add(button);
		this.add(new JButton("Pause"));
		this.add(new JButton("Stop"));
		this.add(new JButton("Save"));
	}

	public Toolbar(GUI gui) {
		this.gui = gui;
		JButton button = new JButton(start);
		this.add(button);
		this.add(new JButton("Pause"));
		this.add(new JButton("Stop"));
		this.add(new JButton("Save"));
	}

	private class StartAction extends AbstractAction {
		public StartAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//gui.canvas.startSimulation();
		}

	}
}
