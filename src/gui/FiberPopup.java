package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import components.Fiber;
import components.OpticalComponent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FiberPopup extends JFrame {
	public FiberPopup (MouseEvent e, ArrayList<Fiber> vlakna) {

		setAlwaysOnTop(true);
		this.setLocation(e.getPoint());
		this.setSize(300, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 31, 64, 86, 0 };
		gridBagLayout.rowHeights = new int[] { 31, 20, 20, 20, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (Fiber f : vlakna) {
					
				}
			}
		});
		
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 10;
		getContentPane().add(btnSave, gbc_btnSave);
		setVisible(true);
		repaint();
	}
	
	private void createField (JLabel lbl, GridBagConstraints gbc_lbl, int gridX, int gridY, JTextField txtField, GridBagConstraints gbc_txtField) {
		
		gbc_lbl.anchor = GridBagConstraints.EAST;
		gbc_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lbl.gridx = gridX;
		gbc_lbl.gridy = gridY;
		getContentPane().add(lbl, gbc_lbl);

		gbc_txtField.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtField.insets = new Insets(0, 0, 5, 0);
		gbc_txtField.gridx = gridX + 1;
		gbc_txtField.gridy = gridY;
		getContentPane().add(txtField, gbc_txtField);
		txtField.setColumns(10);
	}
}
