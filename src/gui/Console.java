package gui;

import java.awt.Color;

import javax.swing.JTextArea;

public class Console extends JTextArea {
	
	private static Console instance = null;
	
	private Console(){
		setAutoscrolls(false);
		setBackground(Color.LIGHT_GRAY);
		setRows(4);
	}
	
	public static Console getInstance () {
		if (instance==null)
			instance = new Console();
		return instance;
	}
	
	public void pr(String str){
		this.setText(this.getText()+str);
	}
	
	public void println(String str){
		this.setText(this.getText()+str+"\n");
	}
}
