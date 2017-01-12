package components;

import java.awt.Point;
import java.util.ArrayList;

public class AddDropMux extends OpticalComponent {

	private int numOfInputs = 2; // DEFAULT 2 ulaza 1 izlaz

	// lista valnih duljina koje simulator treba dodati
	private ArrayList<Integer> addWavelengths = new ArrayList<Integer>();
	// lista valnih duljina koje simulator treba droppati
	private ArrayList<Integer> dropWavelengths = new ArrayList<Integer>();
	private double inSigPowerLoss = 0.2; // Nekakva DEFAULT vrijednost gubitka
											// snage za svaki ulazni signal
	private ArrayList<Signal> inSignals = new ArrayList<Signal>();
	private ArrayList<Fiber> inputFibers = new ArrayList<Fiber>();
	private int handleMethodCallTimes = 0;

	public double getInSigPowerLoss() {
		return inSigPowerLoss;
	}

	public void setInSigPowerLoss(double inSigPowerLoss) {
		this.inSigPowerLoss = inSigPowerLoss;
	}
	
	public void addInputFiber(Fiber f) {
		this.inputFibers.add(f);
		this.numOfInputs = inputFibers.size();
	}

	public int getNumOfInputs() {
		return numOfInputs;
	}

	public void setNumOfInputs(int numOfInputs) {
		this.numOfInputs = numOfInputs;
	}

	public AddDropMux(OpticalComponent c) {
		super(c);
		this.setInsertionLoss(0.3);
		this.setReturnLoss(0.3);
		setImgPath("adm.png");

	}

	public AddDropMux(String str, Point p, int height, int width) {
		super(str, p, height, width);

	}

	/*
	 * dodavanje valnih duljina koje treba simulator dodati signalu
	 */
	public void setAddWavelengths(int wl) {
		addWavelengths.add(wl);

	}
	/*
	 * getter za valne duljine koje simulator treba dodati, u petlji vati indexe
	 * preko ovog gettera
	 */

	public int getAddWavelengths(int index) {
		return addWavelengths.get(index);
	}
	
	public int getAddWavelengthListSize () {
		return addWavelengths.size();
	}
	
	public int getDropWavelengthListSize () {
		return dropWavelengths.size();
	}

	/*
	 * isti scenarij kao i za dodane valne duljine
	 */
	public void setDropWavelengths(int wl) {
		dropWavelengths.add(wl);

	}

	public int getDropWavelengths(int index) {
		return dropWavelengths.get(index);
	}

	public void handleSignal(Signal s) {
		handleMethodCallTimes++;
		attenuateSignal(s);
		inSignals.add(s);
		if (handleMethodCallTimes == numOfInputs) {
			double outputPower;
			double totalInputPower = 0;
			for (Signal sig : inSignals) {
				totalInputPower += sig.getPower();
			}
			outputPower = totalInputPower / numOfInputs;
			sendSignal(outputPower);
		}
	}

	private void sendSignal(double power) {
		Signal outputSignal = new Signal(power);
		for (Signal s : inSignals) {
			int size = s.getWavelengthListSize();
			for (int i = 0; i < size; i++)
				outputSignal.addWavelength(s.getWavelength(i));
		}
		addWavelengthsToSignal(outputSignal);
		dropWavelengthsFromSignal(outputSignal);
		getOutConnector().handleSignal(outputSignal);
	}

	private void dropWavelengthsFromSignal(Signal s) {
		for (int i = 0; i < dropWavelengths.size(); i++) {
			int wave = dropWavelengths.get(i);
			for (int j = 0; j < s.getWavelengthListSize(); j++) {
				if (s.getWavelength(j) == wave) {
					s.dropWavelength(wave);
					break;
				}
			}
		}
	}

	private void addWavelengthsToSignal(Signal outputSignal) {
		for (int i = 0; i < addWavelengths.size(); i++) {
			outputSignal.addWavelength(addWavelengths.get(i));
		}

	}

	private void attenuateSignal(Signal s) {
		double outPower = s.getPower();
		outPower = outPower - this.getInsertionLoss() - this.getReturnLoss();
		s.setPower(outPower - inSigPowerLoss);
	}
}
