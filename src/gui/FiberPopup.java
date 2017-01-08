package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import components.Fiber;

public class FiberPopup extends JFrame {

	private JTextField textFieldLength;
	private JTextField textFieldAttenuance;
	private JTextField textFieldConnectorAttenuance;

	public FiberPopup(MouseEvent e, ArrayList<Fiber> vlakna) {

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
					if (f.isSelected()) {
						f.setLength(Double.parseDouble(textFieldLength.getText()));
						f.setAttenuance(Double.parseDouble(textFieldAttenuance.getText()));
						f.setConnectorAttenuance(Double.parseDouble(textFieldConnectorAttenuance.getText()));
					}
				}
			}
		});

		JLabel lblFiberLength = new JLabel("Length");
		GridBagConstraints gbc_lblFiberLength = new GridBagConstraints();
		textFieldLength = new JTextField();
		GridBagConstraints gbc_textFiberLength = new GridBagConstraints();
		createField(lblFiberLength, gbc_lblFiberLength, 1, 1, textFieldLength, gbc_textFiberLength);

		JLabel lblFiberAttenuance = new JLabel("Attenuance");
		GridBagConstraints gbc_lblFiberAttenuance = new GridBagConstraints();
		textFieldAttenuance = new JTextField();
		GridBagConstraints gbc_textFiberAttenuance = new GridBagConstraints();
		createField(lblFiberAttenuance, gbc_lblFiberAttenuance, 1, 2, textFieldAttenuance, gbc_textFiberAttenuance);

		JLabel lblFiberConnecterAttenuance = new JLabel("Connector attenuance");
		GridBagConstraints gbc_lblFiberConnecterAttenuance = new GridBagConstraints();
		textFieldConnectorAttenuance = new JTextField();
		GridBagConstraints gbc_textFiberConnecterAttenuance = new GridBagConstraints();
		createField(lblFiberConnecterAttenuance, gbc_lblFiberConnecterAttenuance, 1, 3, textFieldConnectorAttenuance,
				gbc_textFiberConnecterAttenuance);

		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 10;
		getContentPane().add(btnSave, gbc_btnSave);
		for (Fiber f : vlakna) {
			if (f.isSelected()) {
				textFieldLength.setText(Double.toString(f.getLength()));
				textFieldAttenuance.setText(Double.toString(f.getAttenuance()));
				textFieldConnectorAttenuance.setText(Double.toString(f.getConnectorAttenuance()));
			}
		}

		setVisible(true);
		repaint();
	}

	private void createField(JLabel lbl, GridBagConstraints gbc_lbl, int gridX, int gridY, JTextField txtField,
			GridBagConstraints gbc_txtField) {

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
