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

import components.OpticalComponent;

public class Popup extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private ArrayList<OpticalComponent> komponente;

	public Popup(MouseEvent e, ArrayList<OpticalComponent> komponente) {
		this.setLocation(e.getPoint());
		this.setSize(300, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 31, 64, 86, 0 };
		gridBagLayout.rowHeights = new int[] { 31, 20, 20, 20, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		this.komponente = komponente;

		JLabel lblNewLabel = new JLabel("Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Insertion loss");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblReturnLoss = new JLabel("Return loss");
		GridBagConstraints gbc_lblReturnLoss = new GridBagConstraints();
		gbc_lblReturnLoss.anchor = GridBagConstraints.EAST;
		gbc_lblReturnLoss.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnLoss.gridx = 1;
		gbc_lblReturnLoss.gridy = 3;
		getContentPane().add(lblReturnLoss, gbc_lblReturnLoss);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (OpticalComponent c : komponente) {
					if (c.isSelected()) {
						c.setLabel(textField.getText());
					}
				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 7;
		getContentPane().add(btnSave, gbc_btnSave);
		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {
				textField.setText(c.getLabel());
				textField_1.setText(Double.toString(c.getInsertionLoss()));
				textField_2.setText(Double.toString(c.getReturnLoss()));
			}
		}
		setVisible(true);
	}
}
