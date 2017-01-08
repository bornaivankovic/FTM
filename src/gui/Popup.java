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

import components.Amplifier;
import components.OpticalComponent;
import components.Receiver;
import components.Transmitter;

public class Popup extends JFrame {
	//common vars
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//transmitter vars
	private JTextField textFieldMinW;
	private JTextField textFieldMaxW;
	private JTextField textFieldNumMods;
	//receiver vars
	private JTextField textRecMinSens;
	private JTextField textRecMaxSens;
	private JTextField textRecNumMinW;
	private JTextField textRecNumMaxW;
	//amplifier vars
	private JTextField textAmpGain;
	private JTextField textAmpGainSat;

	public Popup(MouseEvent e, ArrayList<OpticalComponent> komponente) {
		setAlwaysOnTop(true);
		this.setLocation(e.getPoint());
		this.setSize(300, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 31, 64, 86, 0 };
		gridBagLayout.rowHeights = new int[] { 31, 20, 20, 20, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

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
						c.setInsertionLoss(Double.parseDouble(textField_1.getText()));
						c.setReturnLoss(Double.parseDouble(textField_2.getText()));
						if (c instanceof Transmitter) {
							Transmitter tx = (Transmitter)c;
							tx.setMinWavelengthBand(Double.parseDouble(textFieldMinW.getText()));
							tx.setMaxWavelengthBand(Double.parseDouble(textFieldMaxW.getText()));
							tx.setNumberOfMods(Integer.parseInt(textFieldNumMods.getText()));
						}
						else if (c instanceof Receiver) {
							Receiver rx = (Receiver)c;
							rx.setMinSensitivity(Double.parseDouble(textRecMinSens.getText()));
							rx.setMaxSensitivity(Double.parseDouble(textRecMaxSens.getText()));
							rx.setMinWavelength(Double.parseDouble(textRecNumMinW.getText()));
							rx.setMaxWavelength(Double.parseDouble(textRecNumMaxW.getText()));
						}
						else if (c instanceof Amplifier) {
							Amplifier amp = (Amplifier)c;
							amp.setGain(Double.parseDouble(textAmpGain.getText()));
							amp.setGainSaturation(Double.parseDouble(textAmpGainSat.getText()));
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 8;
		getContentPane().add(btnSave, gbc_btnSave);
		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {
				textField.setText(c.getLabel());
				textField_1.setText(Double.toString(c.getInsertionLoss()));
				textField_2.setText(Double.toString(c.getReturnLoss()));
				setTitle(c.getLabel());
				//System.out.println(c.getInsertionLoss());
				if (c instanceof Transmitter) {
					Transmitter tx = (Transmitter)c;
					
					JLabel lblLabelMinW = new JLabel("Minimal Wavelenght");
					GridBagConstraints gbc_lblLabelMinW = new GridBagConstraints();
					gbc_lblLabelMinW.anchor = GridBagConstraints.EAST;
					gbc_lblLabelMinW.insets = new Insets(0, 0, 5, 5);
					gbc_lblLabelMinW.gridx = 1;
					gbc_lblLabelMinW.gridy = 4;
					getContentPane().add(lblLabelMinW, gbc_lblLabelMinW);

					textFieldMinW = new JTextField();
					GridBagConstraints gbc_textFieldMinW = new GridBagConstraints();
					gbc_textFieldMinW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldMinW.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldMinW.gridx = 2;
					gbc_textFieldMinW.gridy = 4;
					getContentPane().add(textFieldMinW, gbc_textFieldMinW);
					textFieldMinW.setColumns(10);
					
					JLabel lblLabelMaxW = new JLabel("Maximal Wavelenght");
					GridBagConstraints gbc_lblLabelMaxW = new GridBagConstraints();
					gbc_lblLabelMaxW.anchor = GridBagConstraints.EAST;
					gbc_lblLabelMaxW.insets = new Insets(0, 0, 5, 5);
					gbc_lblLabelMaxW.gridx = 1;
					gbc_lblLabelMaxW.gridy = 5;
					getContentPane().add(lblLabelMaxW, gbc_lblLabelMaxW);

					textFieldMaxW = new JTextField();
					GridBagConstraints gbc_textFieldMaxW = new GridBagConstraints();
					gbc_textFieldMaxW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldMaxW.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldMaxW.gridx = 2;
					gbc_textFieldMaxW.gridy = 5;
					getContentPane().add(textFieldMaxW, gbc_textFieldMaxW);
					textFieldMaxW.setColumns(10);
					
					JLabel lblLabelNumMods = new JLabel("Mods");
					GridBagConstraints gbc_lblLabelNumMods = new GridBagConstraints();
					gbc_lblLabelNumMods.anchor = GridBagConstraints.EAST;
					gbc_lblLabelNumMods.insets = new Insets(0, 0, 5, 5);
					gbc_lblLabelNumMods.gridx = 1;
					gbc_lblLabelNumMods.gridy = 6;
					getContentPane().add(lblLabelNumMods, gbc_lblLabelNumMods);

					textFieldNumMods = new JTextField();
					GridBagConstraints gbc_textFieldNumMods = new GridBagConstraints();
					gbc_textFieldNumMods.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldNumMods.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldNumMods.gridx = 2;
					gbc_textFieldNumMods.gridy = 6;
					getContentPane().add(textFieldNumMods, gbc_textFieldNumMods);
					textFieldNumMods.setColumns(10);
					
					textFieldMinW.setText(Double.toString(tx.getMinWavelengthBand()));
					textFieldMaxW.setText(Double.toString(tx.getMaxWavelengthBand()));
					textFieldNumMods.setText(Double.toString(tx.getNumberOfMods()));
				}
				else if (c instanceof Receiver) {
					Receiver rx = (Receiver) c;
					
					JLabel lblRecMinSens = new JLabel("Min sensitivity");
					GridBagConstraints gbc_lblRecMinSens = new GridBagConstraints();
					gbc_lblRecMinSens.anchor = GridBagConstraints.EAST;
					gbc_lblRecMinSens.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecMinSens.gridx = 1;
					gbc_lblRecMinSens.gridy = 4;
					getContentPane().add(lblRecMinSens, gbc_lblRecMinSens);

					textRecMinSens = new JTextField();
					GridBagConstraints gbc_textRecMinSens = new GridBagConstraints();
					gbc_textRecMinSens.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecMinSens.insets = new Insets(0, 0, 5, 0);
					gbc_textRecMinSens.gridx = 2;
					gbc_textRecMinSens.gridy = 4;
					getContentPane().add(textRecMinSens, gbc_textRecMinSens);
					textRecMinSens.setColumns(10);
					
					JLabel lblRecMaxSens = new JLabel("Max sensitivity");
					GridBagConstraints gbc_lblRecMaxSens = new GridBagConstraints();
					gbc_lblRecMaxSens.anchor = GridBagConstraints.EAST;
					gbc_lblRecMaxSens.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecMaxSens.gridx = 1;
					gbc_lblRecMaxSens.gridy = 5;
					getContentPane().add(lblRecMaxSens, gbc_lblRecMaxSens);

					textRecMaxSens = new JTextField();
					GridBagConstraints gbc_textRecMaxSens = new GridBagConstraints();
					gbc_textRecMaxSens.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecMaxSens.insets = new Insets(0, 0, 5, 0);
					gbc_textRecMaxSens.gridx = 2;
					gbc_textRecMaxSens.gridy = 5;
					getContentPane().add(textRecMaxSens, gbc_textRecMaxSens);
					textRecMaxSens.setColumns(10);
					
					JLabel lblRecNumMinW = new JLabel("Min wavelenght");
					GridBagConstraints gbc_lblRecNumMinW = new GridBagConstraints();
					gbc_lblRecNumMinW.anchor = GridBagConstraints.EAST;
					gbc_lblRecNumMinW.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecNumMinW.gridx = 1;
					gbc_lblRecNumMinW.gridy = 6;
					getContentPane().add(lblRecNumMinW, gbc_lblRecNumMinW);

					textRecNumMinW = new JTextField();
					GridBagConstraints gbc_textRecNumMinW = new GridBagConstraints();
					gbc_textRecNumMinW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecNumMinW.insets = new Insets(0, 0, 5, 0);
					gbc_textRecNumMinW.gridx = 2;
					gbc_textRecNumMinW.gridy = 6;
					getContentPane().add(textRecNumMinW, gbc_textRecNumMinW);
					textRecNumMinW.setColumns(10);
					
					JLabel lblRecNumMaxW = new JLabel("Max wavelenght");
					GridBagConstraints gbc_lblRecNumMaxW = new GridBagConstraints();
					gbc_lblRecNumMaxW.anchor = GridBagConstraints.EAST;
					gbc_lblRecNumMaxW.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecNumMaxW.gridx = 1;
					gbc_lblRecNumMaxW.gridy = 7;
					getContentPane().add(lblRecNumMaxW, gbc_lblRecNumMaxW);

					textRecNumMaxW = new JTextField();
					GridBagConstraints gbc_textRecNumMaxW = new GridBagConstraints();
					gbc_textRecNumMaxW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecNumMaxW.insets = new Insets(0, 0, 5, 0);
					gbc_textRecNumMaxW.gridx = 2;
					gbc_textRecNumMaxW.gridy = 7;
					getContentPane().add(textRecNumMaxW, gbc_textRecNumMaxW);
					textRecNumMaxW.setColumns(10);
					
					textRecMinSens.setText(Double.toString(rx.getMinSensitivity()));
					textRecMaxSens.setText(Double.toString(rx.getMaxSensitivity()));
					textRecNumMinW.setText(Double.toString(rx.getMinWavelength()));
					textRecNumMaxW.setText(Double.toString(rx.getMaxWavelength()));
				}
				else if (c instanceof Amplifier) {
					Amplifier amp = (Amplifier)c;
					
					JLabel lblAmpGain = new JLabel("Gain");
					GridBagConstraints gbc_lblAmpGain = new GridBagConstraints();
					gbc_lblAmpGain.anchor = GridBagConstraints.EAST;
					gbc_lblAmpGain.insets = new Insets(0, 0, 5, 5);
					gbc_lblAmpGain.gridx = 1;
					gbc_lblAmpGain.gridy = 4;
					getContentPane().add(lblAmpGain, gbc_lblAmpGain);

					textAmpGain = new JTextField();
					GridBagConstraints gbc_textAmpGain = new GridBagConstraints();
					gbc_textAmpGain.anchor = GridBagConstraints.NORTHWEST;
					gbc_textAmpGain.insets = new Insets(0, 0, 5, 0);
					gbc_textAmpGain.gridx = 2;
					gbc_textAmpGain.gridy = 4;
					getContentPane().add(textAmpGain, gbc_textAmpGain);
					textAmpGain.setColumns(10);
					
					JLabel lblAmpGainSat = new JLabel("Gain saturation");
					GridBagConstraints gbc_lblAmpGainSat = new GridBagConstraints();
					gbc_lblAmpGainSat.anchor = GridBagConstraints.EAST;
					gbc_lblAmpGainSat.insets = new Insets(0, 0, 5, 5);
					gbc_lblAmpGainSat.gridx = 1;
					gbc_lblAmpGainSat.gridy = 5;
					getContentPane().add(lblAmpGainSat, gbc_lblAmpGainSat);

					textAmpGainSat = new JTextField();
					GridBagConstraints gbc_textAmpGainSat = new GridBagConstraints();
					gbc_textAmpGainSat.anchor = GridBagConstraints.NORTHWEST;
					gbc_textAmpGainSat.insets = new Insets(0, 0, 5, 0);
					gbc_textAmpGainSat.gridx = 2;
					gbc_textAmpGainSat.gridy = 5;
					getContentPane().add(textAmpGainSat, gbc_textAmpGainSat);
					textAmpGainSat.setColumns(10);
					
					textAmpGain.setText(Double.toString(amp.getGain()));
					textAmpGainSat.setText(Double.toString(amp.getGainSaturation()));
				}
			}
		}

		setVisible(true);
		repaint();
	}
}
