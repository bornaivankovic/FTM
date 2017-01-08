package gui;

import java.awt.Color;

import javax.swing.JTextArea;

public class Console extends JTextArea {
	private static Console consoleInstance = null;
	private Console() {
		setAutoscrolls(false);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setRows(10);
	}
	
	public static Console getConsoleInstance () {
		if (consoleInstance == null)
			consoleInstance = new Console();
		return consoleInstance;
	}

	public void pr(String str) {
		setText(this.getText() + str);
	}

	public void println(String str) {
		setText(this.getText() + str + "\n");
	}
}
