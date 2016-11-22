package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;

public class Console extends JTextArea {
	private final int WIDTH = 800;
	private final int HEIGHT = 50;
	
	public Console(){
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void print(String str){
		this.setText(this.getText()+str);
	}
	
	public void println(String str){
		this.setText(this.getText()+str+"\n");
	}
}
