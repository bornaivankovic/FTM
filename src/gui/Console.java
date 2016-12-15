package gui;

import java.awt.Color;

import javax.swing.JTextArea;

public class Console extends JTextArea {
	
	public Console(){
		setAutoscrolls(false);
		setBackground(Color.LIGHT_GRAY);
		setRows(4);
	}
	
	public void pr(String str){
		this.setText(this.getText()+str);
	}
	
	public void println(String str){
		this.setText(this.getText()+str+"\n");
	}
}
