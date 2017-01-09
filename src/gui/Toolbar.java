package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

	private StartAction start = new StartAction("Start");
	private ResetAction reset = new ResetAction("Reset");
	public GUI gui = null;

	public Toolbar() {
		JButton startButton = new JButton(start);
		JButton resetButton = new JButton(reset); 
		this.add(startButton);
		this.add(resetButton);
		this.add(new JButton("Pause"));
		this.add(new JButton("Save"));
		this.add(new JButton("Load"));
	}

	public Toolbar(GUI gui) {
		this.gui = gui;
		JButton startButton = new JButton(start);
		JButton resetButton = new JButton(reset);
		this.add(startButton);
		this.add(resetButton);
		this.add(new JButton("Pause"));
		this.add(new JButton("Save"));
		this.add(new JButton("Load"));
	}

	private class StartAction extends AbstractAction {
		public StartAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			gui.canvas.startSimulation();
		}

	}
	
	private class ResetAction extends  AbstractAction{
		public ResetAction(String name) {
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			gui.canvas.resetSimulation();
		}
		
	}
}
