package gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar{
	
	public Toolbar(){
		this.add(new JButton("Start"));
		this.add(new JButton("Pause"));
		this.add(new JButton("Stop"));
		this.add(new JButton("Save"));
	}
}
