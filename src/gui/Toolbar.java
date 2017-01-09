package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

	private StartAction start = new StartAction("Start");
	private ResetAction reset = new ResetAction("Reset");
	private SaveAction save = new SaveAction("Save");
	private LoadAction load = new LoadAction("Load");
	public GUI gui = null;

	public Toolbar() {
		JButton startButton = new JButton(start);
		JButton resetButton = new JButton(reset);
		JButton saveButton = new JButton(save);
		JButton loadButton = new JButton(load);
		this.add(startButton);
		this.add(resetButton);
		this.add(saveButton);
		this.add(loadButton);
	}

	public Toolbar(GUI gui) {
		this.gui = gui;
		JButton startButton = new JButton(start);
		JButton resetButton = new JButton(reset);
		JButton saveButton = new JButton(save);
		JButton loadButton = new JButton(load);
		this.add(startButton);
		this.add(resetButton);
		this.add(saveButton);
		this.add(loadButton);
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
	
	private class SaveAction extends  AbstractAction{
		public SaveAction(String name) {
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			gui.canvas.saveSimulation();
		}
		
	}
	
	private class LoadAction extends  AbstractAction{
		public LoadAction(String name) {
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			gui.canvas.loadSimulation();
		}
		
	}
}
