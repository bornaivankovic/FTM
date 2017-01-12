package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import components.AddDropMux;
import components.Amplifier;
import components.Coupler;
import components.CrossConnect;
import components.Decoupler;
import components.Demultiplexer;
import components.Filter;
import components.Multiplexer;
import components.OpticalComponent;
import components.Receiver;
import components.Transmitter;
import components.WavelengthConverter;

public class Popup extends JFrame {
	// common vars
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	// transmitter vars
	private JTextField textFieldMinW;
	private JTextField textFieldMaxW;
	private JTextField textFieldNumMods;
	// receiver vars
	private JTextField textRecMinSens;
	private JTextField textRecMaxSens;
	private JTextField textRecNumMinW;
	private JTextField textRecNumMaxW;
	// amplifier vars
	private JTextField textAmpGain;
	private JTextField textAmpGainSat;
	// filter vars
	private JTextField textFiltCentW;
	private JTextField textFiltWBand;
	private JTextField textFiltMinW;
	private JTextField textFiltMaxW;
	// coupler vars
	private JTextField textCoupLoss;
	// mux vars
	private JTextField textMuxNumOfInputs;
	private JTextField textMuxLoss;
	private JTextField textMuxInConnector;
	private JTextField textMuxMinW;
	private JTextField textMuxMaxW;
	// demux vars
	private JTextField textDmuxNumOfInputs;
	private JTextField textDmuxLoss;
	private JTextField textDmuxInConnector;
	private JTextField textDmuxMinW;
	private JTextField textDmuxMaxW;
	// decoup vars
	private JTextField textDcoupLoss;
	// waveConverter vars
	private JTextField textWCMinW;
	private JTextField textWCMaxW;
	private JTextField textWCMinOutW;
	private JTextField textWCMaxOutW;

	//ADM vars
	private JTextField textADMadd;
	private JTextField textADMrem;
	

	// crossConnect vars
	private JTextField textInPorts;
	private JTextField textOutPorts;

	private int Gy = 1;


	public Popup(MouseEvent e, ArrayList<OpticalComponent> komponente) {

		setAlwaysOnTop(true);
		this.setLocation(e.getPoint());
		this.setSize(320, 400);
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
				for (OpticalComponent c : komponente) {
					if (c.isSelected()) {
						c.setLabel(textField.getText());
						c.setInsertionLoss(Double.parseDouble(textField_1.getText()));
						c.setReturnLoss(Double.parseDouble(textField_2.getText()));
						if (c instanceof Transmitter) {
							Transmitter tx = (Transmitter) c;
							tx.setMinWavelengthBand(Double.parseDouble(textFieldMinW.getText()));
							tx.setMaxWavelengthBand(Double.parseDouble(textFieldMaxW.getText()));
							tx.setNumberOfMods(Integer.parseInt(textFieldNumMods.getText()));
						} else if (c instanceof Receiver) {
							Receiver rx = (Receiver) c;
							rx.setMinSensitivity(Double.parseDouble(textRecMinSens.getText()));
							rx.setMaxSensitivity(Double.parseDouble(textRecMaxSens.getText()));
							rx.setMinWavelength(Double.parseDouble(textRecNumMinW.getText()));
							rx.setMaxWavelength(Double.parseDouble(textRecNumMaxW.getText()));
						} else if (c instanceof Amplifier) {
							Amplifier amp = (Amplifier) c;
							amp.setGain(Double.parseDouble(textAmpGain.getText()));
							amp.setGainSaturation(Double.parseDouble(textAmpGainSat.getText()));
						} else if (c instanceof Filter) {
							Filter f = (Filter) c;
							f.setCentralWavelength(Double.parseDouble(textFiltCentW.getText()));
							f.setWavelengthBandWidth(Double.parseDouble(textFiltWBand.getText()));
							f.setMinBand(Double.parseDouble(textFiltMinW.getText()));
							f.setMaxBand(Double.parseDouble(textFiltMaxW.getText()));
						} else if (c instanceof Coupler) {
							Coupler coup = (Coupler) c;
							coup.setCouplingLoss(Double.parseDouble(textCoupLoss.getText()));
						} else if (c instanceof Multiplexer) {
							Multiplexer mux = (Multiplexer) c;
							mux.setNumOfInputs(Integer.parseInt(textMuxNumOfInputs.getText()));
							mux.setMultiplexingLoss(Double.parseDouble(textMuxLoss.getText()));
							int s = Integer.parseInt(textMuxInConnector.getText());
							int sMax = mux.getNumOfInputs();
							if (s > -1 && s < sMax)
								mux.setSelectedPort(Integer.parseInt(textMuxInConnector.getText()));
							else
								mux.setSelectedPort(sMax - 1);
							mux.setChanBand(s, Integer.parseInt(textMuxMinW.getText()),
									Integer.parseInt(textMuxMaxW.getText()));
						} else if (c instanceof Demultiplexer) {
							Demultiplexer dmux = (Demultiplexer) c;
							dmux.setNumOfOutputs(Integer.parseInt(textDmuxNumOfInputs.getText()));
							dmux.setDemultiplexingLoss(Double.parseDouble(textDmuxLoss.getText()));
							int s = Integer.parseInt(textDmuxInConnector.getText());
							int sMax = dmux.getNumOfOutputs();
							if (s > -1 && s < sMax)
								dmux.setSelectedPort(Integer.parseInt(textDmuxInConnector.getText()));
							else
								dmux.setSelectedPort(sMax - 1);
							dmux.setChanBand(s, Integer.parseInt(textDmuxMinW.getText()),
									Integer.parseInt(textDmuxMaxW.getText()));
						} else if (c instanceof Decoupler) {
							Decoupler d = (Decoupler) c;
							d.setCouplingLoss(Double.parseDouble(textDcoupLoss.getText()));
						} else if (c instanceof WavelengthConverter) {
							WavelengthConverter wc = (WavelengthConverter) c;
							wc.setMinBandwidth(Integer.parseInt(textWCMinW.getText()));
							wc.setMaxBandwidth(Integer.parseInt(textWCMaxW.getText()));
							wc.setOutMinBandwidth(Integer.parseInt(textWCMinOutW.getText()));
							wc.setOutMaxBandwidth(Integer.parseInt(textWCMaxOutW.getText()));
						} else if (c instanceof CrossConnect) {
							CrossConnect cc = (CrossConnect) c;
							cc.setNumInputs(Integer.parseInt(textInPorts.getText()));
							cc.setNumOutputs(Integer.parseInt(textOutPorts.getText()));
						}

						
						

					}
				}
			}
		});

		for (OpticalComponent c : komponente) {
			if (c.isSelected()) {

				JLabel lblNewLabel = new JLabel("Name");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = ++Gy;
				getContentPane().add(lblNewLabel, gbc_lblNewLabel);

				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.anchor = GridBagConstraints.NORTHWEST;
				gbc_textField.insets = new Insets(0, 0, 5, 0);
				gbc_textField.gridx = 2;
				gbc_textField.gridy = Gy;
				getContentPane().add(textField, gbc_textField);
				textField.setColumns(10);

				JLabel lblNewLabel_1 = new JLabel("Insertion loss");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = ++Gy;
				getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

				textField_1 = new JTextField();
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.anchor = GridBagConstraints.NORTHWEST;
				gbc_textField_1.insets = new Insets(0, 0, 5, 0);
				gbc_textField_1.gridx = 2;
				gbc_textField_1.gridy = Gy;
				getContentPane().add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);

				JLabel lblReturnLoss = new JLabel("Return loss");
				GridBagConstraints gbc_lblReturnLoss = new GridBagConstraints();
				gbc_lblReturnLoss.anchor = GridBagConstraints.EAST;
				gbc_lblReturnLoss.insets = new Insets(0, 0, 5, 5);
				gbc_lblReturnLoss.gridx = 1;
				gbc_lblReturnLoss.gridy = ++Gy;
				getContentPane().add(lblReturnLoss, gbc_lblReturnLoss);

				textField_2 = new JTextField();
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.insets = new Insets(0, 0, 5, 0);
				gbc_textField_2.anchor = GridBagConstraints.NORTHWEST;
				gbc_textField_2.gridx = 2;
				gbc_textField_2.gridy = Gy;
				getContentPane().add(textField_2, gbc_textField_2);
				textField_2.setColumns(10);

				textField.setText(c.getLabel());
				textField_1.setText(Double.toString(c.getInsertionLoss()));
				textField_2.setText(Double.toString(c.getReturnLoss()));
				setTitle(c.getLabel());
				if (c instanceof Transmitter) {
					Transmitter tx = (Transmitter) c;

					JLabel lblLabelMinW = new JLabel("Minimal Wavelenght");
					GridBagConstraints gbc_lblLabelMinW = new GridBagConstraints();
					gbc_lblLabelMinW.anchor = GridBagConstraints.EAST;
					gbc_lblLabelMinW.insets = new Insets(0, 0, 5, 5);
					gbc_lblLabelMinW.gridx = 1;
					gbc_lblLabelMinW.gridy = ++Gy;
					getContentPane().add(lblLabelMinW, gbc_lblLabelMinW);

					textFieldMinW = new JTextField();
					GridBagConstraints gbc_textFieldMinW = new GridBagConstraints();
					gbc_textFieldMinW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldMinW.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldMinW.gridx = 2;
					gbc_textFieldMinW.gridy = Gy;
					getContentPane().add(textFieldMinW, gbc_textFieldMinW);
					textFieldMinW.setColumns(10);

					JLabel lblLabelMaxW = new JLabel("Maximal Wavelenght");
					GridBagConstraints gbc_lblLabelMaxW = new GridBagConstraints();
					gbc_lblLabelMaxW.anchor = GridBagConstraints.EAST;
					gbc_lblLabelMaxW.insets = new Insets(0, 0, 5, 5);
					gbc_lblLabelMaxW.gridx = 1;
					gbc_lblLabelMaxW.gridy = ++Gy;
					getContentPane().add(lblLabelMaxW, gbc_lblLabelMaxW);

					textFieldMaxW = new JTextField();
					GridBagConstraints gbc_textFieldMaxW = new GridBagConstraints();
					gbc_textFieldMaxW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldMaxW.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldMaxW.gridx = 2;
					gbc_textFieldMaxW.gridy = Gy;
					getContentPane().add(textFieldMaxW, gbc_textFieldMaxW);
					textFieldMaxW.setColumns(10);

					JLabel lblLabelNumMods = new JLabel("Mods");
					GridBagConstraints gbc_lblLabelNumMods = new GridBagConstraints();
					gbc_lblLabelNumMods.anchor = GridBagConstraints.EAST;
					gbc_lblLabelNumMods.insets = new Insets(0, 0, 5, 5);
					gbc_lblLabelNumMods.gridx = 1;
					gbc_lblLabelNumMods.gridy = ++Gy;
					getContentPane().add(lblLabelNumMods, gbc_lblLabelNumMods);

					textFieldNumMods = new JTextField();
					GridBagConstraints gbc_textFieldNumMods = new GridBagConstraints();
					gbc_textFieldNumMods.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldNumMods.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldNumMods.gridx = 2;
					gbc_textFieldNumMods.gridy = Gy;
					getContentPane().add(textFieldNumMods, gbc_textFieldNumMods);
					textFieldNumMods.setColumns(10);

					textFieldMinW.setText(Double.toString(tx.getMinWavelengthBand()));
					textFieldMaxW.setText(Double.toString(tx.getMaxWavelengthBand()));
					textFieldNumMods.setText(Integer.toString(tx.getNumberOfMods()));
				} else if (c instanceof Receiver) {
					Receiver rx = (Receiver) c;

					JLabel lblRecMinSens = new JLabel("Min sensitivity");
					GridBagConstraints gbc_lblRecMinSens = new GridBagConstraints();
					gbc_lblRecMinSens.anchor = GridBagConstraints.EAST;
					gbc_lblRecMinSens.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecMinSens.gridx = 1;
					gbc_lblRecMinSens.gridy = ++Gy;
					getContentPane().add(lblRecMinSens, gbc_lblRecMinSens);

					textRecMinSens = new JTextField();
					GridBagConstraints gbc_textRecMinSens = new GridBagConstraints();
					gbc_textRecMinSens.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecMinSens.insets = new Insets(0, 0, 5, 0);
					gbc_textRecMinSens.gridx = 2;
					gbc_textRecMinSens.gridy = Gy;
					getContentPane().add(textRecMinSens, gbc_textRecMinSens);
					textRecMinSens.setColumns(10);

					JLabel lblRecMaxSens = new JLabel("Max sensitivity");
					GridBagConstraints gbc_lblRecMaxSens = new GridBagConstraints();
					gbc_lblRecMaxSens.anchor = GridBagConstraints.EAST;
					gbc_lblRecMaxSens.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecMaxSens.gridx = 1;
					gbc_lblRecMaxSens.gridy = ++Gy;
					getContentPane().add(lblRecMaxSens, gbc_lblRecMaxSens);

					textRecMaxSens = new JTextField();
					GridBagConstraints gbc_textRecMaxSens = new GridBagConstraints();
					gbc_textRecMaxSens.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecMaxSens.insets = new Insets(0, 0, 5, 0);
					gbc_textRecMaxSens.gridx = 2;
					gbc_textRecMaxSens.gridy = Gy;
					getContentPane().add(textRecMaxSens, gbc_textRecMaxSens);
					textRecMaxSens.setColumns(10);

					JLabel lblRecNumMinW = new JLabel("Min wavelenght");
					GridBagConstraints gbc_lblRecNumMinW = new GridBagConstraints();
					gbc_lblRecNumMinW.anchor = GridBagConstraints.EAST;
					gbc_lblRecNumMinW.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecNumMinW.gridx = 1;
					gbc_lblRecNumMinW.gridy = ++Gy;
					getContentPane().add(lblRecNumMinW, gbc_lblRecNumMinW);

					textRecNumMinW = new JTextField();
					GridBagConstraints gbc_textRecNumMinW = new GridBagConstraints();
					gbc_textRecNumMinW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecNumMinW.insets = new Insets(0, 0, 5, 0);
					gbc_textRecNumMinW.gridx = 2;
					gbc_textRecNumMinW.gridy = Gy;
					getContentPane().add(textRecNumMinW, gbc_textRecNumMinW);
					textRecNumMinW.setColumns(10);

					JLabel lblRecNumMaxW = new JLabel("Max wavelenght");
					GridBagConstraints gbc_lblRecNumMaxW = new GridBagConstraints();
					gbc_lblRecNumMaxW.anchor = GridBagConstraints.EAST;
					gbc_lblRecNumMaxW.insets = new Insets(0, 0, 5, 5);
					gbc_lblRecNumMaxW.gridx = 1;
					gbc_lblRecNumMaxW.gridy = ++Gy;
					getContentPane().add(lblRecNumMaxW, gbc_lblRecNumMaxW);

					textRecNumMaxW = new JTextField();
					GridBagConstraints gbc_textRecNumMaxW = new GridBagConstraints();
					gbc_textRecNumMaxW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textRecNumMaxW.insets = new Insets(0, 0, 5, 0);
					gbc_textRecNumMaxW.gridx = 2;
					gbc_textRecNumMaxW.gridy = Gy;
					getContentPane().add(textRecNumMaxW, gbc_textRecNumMaxW);
					textRecNumMaxW.setColumns(10);

					textRecMinSens.setText(Double.toString(rx.getMinSensitivity()));
					textRecMaxSens.setText(Double.toString(rx.getMaxSensitivity()));
					textRecNumMinW.setText(Double.toString(rx.getMinWavelength()));
					textRecNumMaxW.setText(Double.toString(rx.getMaxWavelength()));
				} else if (c instanceof Amplifier) {
					Amplifier amp = (Amplifier) c;

					JLabel lblAmpGain = new JLabel("Gain");
					GridBagConstraints gbc_lblAmpGain = new GridBagConstraints();
					gbc_lblAmpGain.anchor = GridBagConstraints.EAST;
					gbc_lblAmpGain.insets = new Insets(0, 0, 5, 5);
					gbc_lblAmpGain.gridx = 1;
					gbc_lblAmpGain.gridy = ++Gy;
					getContentPane().add(lblAmpGain, gbc_lblAmpGain);

					textAmpGain = new JTextField();
					GridBagConstraints gbc_textAmpGain = new GridBagConstraints();
					gbc_textAmpGain.anchor = GridBagConstraints.NORTHWEST;
					gbc_textAmpGain.insets = new Insets(0, 0, 5, 0);
					gbc_textAmpGain.gridx = 2;
					gbc_textAmpGain.gridy = Gy;
					getContentPane().add(textAmpGain, gbc_textAmpGain);
					textAmpGain.setColumns(10);

					JLabel lblAmpGainSat = new JLabel("Gain saturation");
					GridBagConstraints gbc_lblAmpGainSat = new GridBagConstraints();
					gbc_lblAmpGainSat.anchor = GridBagConstraints.EAST;
					gbc_lblAmpGainSat.insets = new Insets(0, 0, 5, 5);
					gbc_lblAmpGainSat.gridx = 1;
					gbc_lblAmpGainSat.gridy = ++Gy;
					getContentPane().add(lblAmpGainSat, gbc_lblAmpGainSat);

					textAmpGainSat = new JTextField();
					GridBagConstraints gbc_textAmpGainSat = new GridBagConstraints();
					gbc_textAmpGainSat.anchor = GridBagConstraints.NORTHWEST;
					gbc_textAmpGainSat.insets = new Insets(0, 0, 5, 0);
					gbc_textAmpGainSat.gridx = 2;
					gbc_textAmpGainSat.gridy = Gy;
					getContentPane().add(textAmpGainSat, gbc_textAmpGainSat);
					textAmpGainSat.setColumns(10);

					textAmpGain.setText(Double.toString(amp.getGain()));
					textAmpGainSat.setText(Double.toString(amp.getGainSaturation()));
				} else if (c instanceof Filter) {
					Filter f = (Filter) c;
					JLabel lblFiltCentW = new JLabel("Central Wavelenght");
					GridBagConstraints gbc_lblFiltCentW = new GridBagConstraints();
					gbc_lblFiltCentW.anchor = GridBagConstraints.EAST;
					gbc_lblFiltCentW.insets = new Insets(0, 0, 5, 5);
					gbc_lblFiltCentW.gridx = 1;
					gbc_lblFiltCentW.gridy = ++Gy;
					getContentPane().add(lblFiltCentW, gbc_lblFiltCentW);

					textFiltCentW = new JTextField();
					GridBagConstraints gbc_textFiltCentW = new GridBagConstraints();
					gbc_textFiltCentW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFiltCentW.insets = new Insets(0, 0, 5, 0);
					gbc_textFiltCentW.gridx = 2;
					gbc_textFiltCentW.gridy = Gy;
					getContentPane().add(textFiltCentW, gbc_textFiltCentW);
					textFiltCentW.setColumns(10);

					JLabel lblFiltWBand = new JLabel("Wavelenght band");
					GridBagConstraints gbc_lblFiltWBand = new GridBagConstraints();
					gbc_lblFiltWBand.anchor = GridBagConstraints.EAST;
					gbc_lblFiltWBand.insets = new Insets(0, 0, 5, 5);
					gbc_lblFiltWBand.gridx = 1;
					gbc_lblFiltWBand.gridy = ++Gy;
					getContentPane().add(lblFiltWBand, gbc_lblFiltWBand);

					textFiltWBand = new JTextField();
					GridBagConstraints gbc_textFiltWBand = new GridBagConstraints();
					gbc_textFiltWBand.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFiltWBand.insets = new Insets(0, 0, 5, 0);
					gbc_textFiltWBand.gridx = 2;
					gbc_textFiltWBand.gridy = Gy;
					getContentPane().add(textFiltWBand, gbc_textFiltWBand);
					textFiltWBand.setColumns(10);

					JLabel lblFiltMinW = new JLabel("Min detectable Wav.");
					GridBagConstraints gbc_lblFiltMinW = new GridBagConstraints();
					gbc_lblFiltMinW.anchor = GridBagConstraints.EAST;
					gbc_lblFiltMinW.insets = new Insets(0, 0, 5, 5);
					gbc_lblFiltMinW.gridx = 1;
					gbc_lblFiltMinW.gridy = ++Gy;
					getContentPane().add(lblFiltMinW, gbc_lblFiltMinW);

					textFiltMinW = new JTextField();
					GridBagConstraints gbc_textFiltMinW = new GridBagConstraints();
					gbc_textFiltMinW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFiltMinW.insets = new Insets(0, 0, 5, 0);
					gbc_textFiltMinW.gridx = 2;
					gbc_textFiltMinW.gridy = Gy;
					getContentPane().add(textFiltMinW, gbc_textFiltMinW);
					textFiltMinW.setColumns(10);

					JLabel lblFiltMaxW = new JLabel("Min detectable Wav.");
					GridBagConstraints gbc_lblFiltMaxW = new GridBagConstraints();
					gbc_lblFiltMaxW.anchor = GridBagConstraints.EAST;
					gbc_lblFiltMaxW.insets = new Insets(0, 0, 5, 5);
					gbc_lblFiltMaxW.gridx = 1;
					gbc_lblFiltMaxW.gridy = ++Gy;
					getContentPane().add(lblFiltMaxW, gbc_lblFiltMaxW);

					textFiltMaxW = new JTextField();
					GridBagConstraints gbc_textFiltMaxW = new GridBagConstraints();
					gbc_textFiltMaxW.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFiltMaxW.insets = new Insets(0, 0, 5, 0);
					gbc_textFiltMaxW.gridx = 2;
					gbc_textFiltMaxW.gridy = Gy;
					getContentPane().add(textFiltMaxW, gbc_textFiltMaxW);
					textFiltMaxW.setColumns(10);

					textFiltCentW.setText(Double.toString(f.getCentralWavelength()));
					textFiltWBand.setText(Double.toString(f.getWavelengthBandWidth()));
					textFiltMinW.setText(Double.toString(f.getMinBand()));
					textFiltMaxW.setText(Double.toString(f.getMaxBand()));
				} else if (c instanceof Coupler) {
					Coupler coup = (Coupler) c;
					JLabel lblCoupLoss = new JLabel("Coupling loss");
					GridBagConstraints gbc_lblCoupLoss = new GridBagConstraints();
					textCoupLoss = new JTextField();
					GridBagConstraints gbc_textCoupLoss = new GridBagConstraints();
					createField(lblCoupLoss, gbc_lblCoupLoss, 1, ++Gy, textCoupLoss, gbc_textCoupLoss);

					textCoupLoss.setText(Double.toString(coup.getCouplingLoss()));
				} else if (c instanceof Multiplexer) {
					Multiplexer mux = (Multiplexer) c;
					JLabel lblMuxNumOfInputs = new JLabel("Number of inputs");
					GridBagConstraints gbc_lblMuxNumOfInputs = new GridBagConstraints();
					textMuxNumOfInputs = new JTextField();
					GridBagConstraints gbc_textMuxNumOfInputs = new GridBagConstraints();
					createField(lblMuxNumOfInputs, gbc_lblMuxNumOfInputs, 1, Gy, textMuxNumOfInputs,
							gbc_lblMuxNumOfInputs);

					JLabel lblMuxLoss = new JLabel("Multiplexing loss");
					GridBagConstraints gbc_lblMuxLoss = new GridBagConstraints();
					textMuxLoss = new JTextField();
					GridBagConstraints gbc_textMuxLoss = new GridBagConstraints();
					createField(lblMuxLoss, gbc_lblMuxLoss, 1, ++Gy, textMuxLoss, gbc_textMuxLoss);

					JLabel lblMuxInConnector = new JLabel("Select input port");
					GridBagConstraints gbc_lblMuxInConnector = new GridBagConstraints();
					textMuxInConnector = new JTextField();
					GridBagConstraints gbc_textMuxInConnector = new GridBagConstraints();
					createField(lblMuxInConnector, gbc_lblMuxInConnector, 1, ++Gy, textMuxInConnector,
							gbc_textMuxInConnector);

					JLabel lblMuxMinW = new JLabel("Min detectable wav.");
					GridBagConstraints gbc_lblMuxMinW = new GridBagConstraints();
					textMuxMinW = new JTextField();
					GridBagConstraints gbc_textMuxMinW = new GridBagConstraints();
					createField(lblMuxMinW, gbc_lblMuxMinW, 1, ++Gy, textMuxMinW, gbc_textMuxMinW);

					JLabel lblMuxMaxW = new JLabel("Max detectable wav.");
					GridBagConstraints gbc_lblMuxMaxW = new GridBagConstraints();
					textMuxMaxW = new JTextField();
					GridBagConstraints gbc_textMuxMaxW = new GridBagConstraints();
					createField(lblMuxMaxW, gbc_lblMuxMaxW, 1, ++Gy, textMuxMaxW, gbc_textMuxMaxW);

					textMuxNumOfInputs.setText(Integer.toString(mux.getNumOfInputs()));
					textMuxLoss.setText(Double.toString(mux.getMultiplexingLoss()));
					textMuxInConnector.setText(Integer.toString(mux.getSelectedPort()));
					textMuxMinW.setText(Integer.toString(mux.getMinChanBand(mux.getSelectedPort())));
					textMuxMaxW.setText(Integer.toString(mux.getMaxChanBand(mux.getSelectedPort())));
				} else if (c instanceof Demultiplexer) {
					Demultiplexer dmux = (Demultiplexer) c;
					JLabel lblDmuxNumOfInputs = new JLabel("Number of outputs");
					GridBagConstraints gbc_lblDmuxNumOfInputs = new GridBagConstraints();
					textDmuxNumOfInputs = new JTextField();
					GridBagConstraints gbc_textDmuxNumOfInputs = new GridBagConstraints();
					createField(lblDmuxNumOfInputs, gbc_lblDmuxNumOfInputs, 1, ++Gy, textDmuxNumOfInputs,
							gbc_textDmuxNumOfInputs);

					JLabel lblDmuxLoss = new JLabel("Demultiplexing loss");
					GridBagConstraints gbc_lblDmuxLoss = new GridBagConstraints();
					textDmuxLoss = new JTextField();
					GridBagConstraints gbc_textDmuxLoss = new GridBagConstraints();
					createField(lblDmuxLoss, gbc_lblDmuxLoss, 1, ++Gy, textDmuxLoss, gbc_textDmuxLoss);

					JLabel lblDmuxInConnector = new JLabel("Select output port");
					GridBagConstraints gbc_lblDmuxInConnector = new GridBagConstraints();
					textDmuxInConnector = new JTextField();
					GridBagConstraints gbc_textDmuxInConnector = new GridBagConstraints();
					createField(lblDmuxInConnector, gbc_lblDmuxInConnector, 1, ++Gy, textDmuxInConnector,
							gbc_textDmuxInConnector);

					JLabel lblDmuxMinW = new JLabel("Min detectable wav.");
					GridBagConstraints gbc_lblDmuxMinW = new GridBagConstraints();
					textDmuxMinW = new JTextField();
					GridBagConstraints gbc_textDmuxMinW = new GridBagConstraints();
					createField(lblDmuxMinW, gbc_lblDmuxMinW, 1, ++Gy, textDmuxMinW, gbc_textDmuxMinW);

					JLabel lblDmuxMaxW = new JLabel("Max detectable wav.");
					GridBagConstraints gbc_lblDmuxMaxW = new GridBagConstraints();
					textDmuxMaxW = new JTextField();
					GridBagConstraints gbc_textDmuxMaxW = new GridBagConstraints();
					createField(lblDmuxMaxW, gbc_lblDmuxMaxW, 1, ++Gy, textDmuxMaxW, gbc_textDmuxMaxW);

					textDmuxNumOfInputs.setText(Integer.toString(dmux.getNumOfOutputs()));
					textDmuxLoss.setText(Double.toString(dmux.getDemultiplexingLoss()));
					textDmuxInConnector.setText(Integer.toString(dmux.getSelectedPort()));
					textDmuxMinW.setText(Integer.toString(dmux.getMinChanBand(dmux.getSelectedPort())));
					textDmuxMaxW.setText(Integer.toString(dmux.getMaxChanBand(dmux.getSelectedPort())));
				} else if (c instanceof Decoupler) {
					Decoupler d = (Decoupler) c;
					JLabel lblDcoupLoss = new JLabel("Decoupling loss");
					GridBagConstraints gbc_lblDcoupLoss = new GridBagConstraints();
					textDcoupLoss = new JTextField();
					GridBagConstraints gbc_textDcoupLoss = new GridBagConstraints();
					createField(lblDcoupLoss, gbc_lblDcoupLoss, 1, ++Gy, textDcoupLoss, gbc_textDcoupLoss);

					textDcoupLoss.setText(Double.toString(d.getCouplingLoss()));
				} else if (c instanceof WavelengthConverter) {
					WavelengthConverter wc = (WavelengthConverter) c;
					JLabel lblWCMinW = new JLabel("Min detectable Wav.");
					GridBagConstraints gbc_lblWCMinW = new GridBagConstraints();
					textWCMinW = new JTextField();
					GridBagConstraints gbc_textWCMinW = new GridBagConstraints();
					createField(lblWCMinW, gbc_lblWCMinW, 1, ++Gy, textWCMinW, gbc_textWCMinW);

					JLabel lblWCMaxW = new JLabel("Max detectable Wav.");
					GridBagConstraints gbc_lblWCMaxW = new GridBagConstraints();
					textWCMaxW = new JTextField();
					GridBagConstraints gbc_textWCMaxW = new GridBagConstraints();
					createField(lblWCMaxW, gbc_lblWCMaxW, 1, ++Gy, textWCMaxW, gbc_textWCMaxW);

					JLabel lblWCMinOutW = new JLabel("Min out wavelength");
					GridBagConstraints gbc_lblWCMinOutW = new GridBagConstraints();
					textWCMinOutW = new JTextField();
					GridBagConstraints gbc_textWCMinOutW = new GridBagConstraints();
					createField(lblWCMinOutW, gbc_lblWCMinOutW, 1, ++Gy, textWCMinOutW, gbc_textWCMinOutW);

					JLabel lblWCMaxOutW = new JLabel("Max out wavelength");
					GridBagConstraints gbc_lblWCMaxOutW = new GridBagConstraints();
					textWCMaxOutW = new JTextField();
					GridBagConstraints gbc_textWCMaxOutW = new GridBagConstraints();
					createField(lblWCMaxOutW, gbc_lblWCMaxOutW, 1, ++Gy, textWCMaxOutW, gbc_textWCMaxOutW);

					textWCMinW.setText(Integer.toString(wc.getMinBandwidth()));
					textWCMaxW.setText(Integer.toString(wc.getMaxBandwidth()));
					textWCMinOutW.setText(Integer.toString(wc.getOutMinBandwidth()));
					textWCMaxOutW.setText(Integer.toString(wc.getOutMaxBandwidth()));
				} else if (c instanceof CrossConnect) {
					CrossConnect cc = (CrossConnect) c;

					JLabel lblInPorts = new JLabel("Number of input ports:");
					GridBagConstraints gbc_lblInPorts = new GridBagConstraints();
					textInPorts = new JTextField();
					GridBagConstraints gbc_textInPorts = new GridBagConstraints();
					createField(lblInPorts, gbc_lblInPorts, 1, ++Gy, textInPorts, gbc_textInPorts);

					JLabel lblOutPorts = new JLabel("Number of output ports:");
					GridBagConstraints gbc_lblOutPorts = new GridBagConstraints();
					textOutPorts = new JTextField();
					GridBagConstraints gbc_textOutPorts = new GridBagConstraints();
					createField(lblOutPorts, gbc_lblOutPorts, 1, ++Gy, textOutPorts, gbc_textOutPorts);

					textInPorts.setText(Integer.toString(cc.getNumInputs()));
					textOutPorts.setText(Integer.toString(cc.getNumOutputs()));

					final JPopupMenu popup = new JPopupMenu();
					for (int i = 0; i < cc.getNumOutputs(); i++) {
						final int a = i;
						popup.add(new JMenuItem(new AbstractAction("Out port " + Integer.toString(i + 1)) {
							@Override
							public void actionPerformed(ActionEvent e) {
								cc.setSelectedOutPort(a);
								cc.configSwitchingMatrix();
								String msg = "In port " + (cc.getSelectedInPort() + 1) + " set to out port "
										+ (cc.getSelectedOutPort() + 1) + ".";
								JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), msg);
							}
						}));
					}

					for (int i = 0; i < cc.getNumInputs(); i++) {
						final int a = i;
						final JButton inPort1Button = new JButton("In port " + Integer.toString(i + 1) + " to:");
						inPort1Button.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								cc.setSelectedInPort(a);
								popup.show(e.getComponent(), e.getX(), e.getY());
							}
						});

						Gy++;
						GridBagConstraints gbc_inPort1Button = new GridBagConstraints();
						gbc_inPort1Button.insets = new Insets(5, 0, 0, 5);
						gbc_inPort1Button.gridx = 1;
						gbc_inPort1Button.gridy = ++Gy;
						getContentPane().add(inPort1Button, gbc_inPort1Button);
					}
				}

				
				else if (c instanceof AddDropMux) {
					AddDropMux adm = (AddDropMux) c;
					
					final JPopupMenu popup = new JPopupMenu();
					JTextArea textArea1 = new JTextArea(15, 25);
					JScrollPane scrollPane = new JScrollPane(textArea1); 
					textArea1.setEditable(false);
					popup.add(scrollPane);
					
					final JButton AddWave = new JButton("Add Wav.");
					AddWave.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			            	String txt = textADMadd.getText();
			            	if (!txt.isEmpty())
			            		adm.setAddWavelengths(Integer.parseInt(txt));
			            }
			        });
			        
			        GridBagConstraints gbc_AddWave = new GridBagConstraints();
			        gbc_AddWave.insets = new Insets(5, 0, 0, 5);
			        gbc_AddWave.gridx = 1;
			        gbc_AddWave.gridy = 6;
					getContentPane().add(AddWave, gbc_AddWave);
					
					final JButton remWave = new JButton("Remove Wav.");
					remWave.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			            	String txt = textADMrem.getText();
			            	if (!txt.isEmpty())
			            		adm.setDropWavelengths(Integer.parseInt(txt));
			            }
			        });
			        
			        GridBagConstraints gbc_remWave = new GridBagConstraints();
			        gbc_remWave.insets = new Insets(5, 0, 0, 5);
			        gbc_remWave.gridx = 1;
			        gbc_remWave.gridy = 7;
					getContentPane().add(remWave, gbc_remWave);
					
					final JButton showAD = new JButton("Show add/drop");
					showAD.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			            	popup.show(e.getComponent(), e.getX(), e.getY());
			            	textArea1.setText("Wavelengths to add: \n");
			            	for (int i = 0; i<adm.getAddWavelengthListSize(); i++) {
			            		textArea1.append(" "+adm.getAddWavelengths(i)+" ");
			            	}
			            	textArea1.append("\n");
			            	textArea1.append("Wavelengths to remove: \n");
			            	for (int i = 0; i<adm.getDropWavelengthListSize(); i++) {
			            		textArea1.append(" "+adm.getDropWavelengths(i)+" ");
			            	}
			            	
			            }
			        });
			        
			        GridBagConstraints gbc_showAD = new GridBagConstraints();
			        gbc_showAD.insets = new Insets(5, 0, 0, 5);
			        gbc_showAD.gridx = 1;
			        gbc_showAD.gridy = 8;
					getContentPane().add(showAD, gbc_showAD);
					
					textADMadd = new JTextField();
					GridBagConstraints gbc_textADMadd = new GridBagConstraints();
					gbc_textADMadd.anchor = GridBagConstraints.NORTHWEST;
					gbc_textADMadd.insets = new Insets(7, 0, 5, 0);
					gbc_textADMadd.gridx = 2;
					gbc_textADMadd.gridy = 6;
					getContentPane().add(textADMadd, gbc_textADMadd);
					textADMadd.setColumns(10);
					
					textADMrem = new JTextField();
					GridBagConstraints gbc_textADMrem = new GridBagConstraints();
					gbc_textADMrem.anchor = GridBagConstraints.NORTHWEST;
					gbc_textADMrem.insets = new Insets(9, 0, 5, 0);
					gbc_textADMrem.gridx = 2;
					gbc_textADMrem.gridy = 7;
					getContentPane().add(textADMrem, gbc_textADMrem);
					textADMrem.setColumns(10);
					
				}
				

			}
		}
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.anchor = GridBagConstraints.SOUTH;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = ++Gy;
		getContentPane().add(btnSave, gbc_btnSave);

		pack();
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