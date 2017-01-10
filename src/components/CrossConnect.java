package components;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import gui.Console;

public class CrossConnect extends OpticalComponent implements Serializable{

	private int numInputs;
	private int numOutputs;
	private int[][] switchingMatrix;
	private ArrayList<Fiber> inPorts = new ArrayList<Fiber>();
	private ArrayList<Fiber> outPorts = new ArrayList<Fiber>();

	public CrossConnect(String str, Point p, int height, int width, int inputs, int outputs) {
		super(str, p, height, width);
		numInputs = inputs;
		numOutputs = outputs;
		switchingMatrix = new int[numInputs][numOutputs];
		setDefaultSwitchingMatrix();

	}

	public CrossConnect(OpticalComponent c) {
		super(c);
		numInputs = 4;
		numOutputs = 4;
		setImgPath("xc.png");
		this.switchingMatrix = new int[numInputs][numOutputs];
		setDefaultSwitchingMatrix();
	}

	private void setDefaultSwitchingMatrix() {
		// matrica prospajanja, ako je 1 znaci da prospaja, ako je 0 onda
		// propu�ta
		// ulazi i
		// izlazi j
		// npr ako (i,j) = 1 tada se ulaz i prospaja na izlaz j, a ako (i,j) = 0
		// tada se ne prospaja
		for (int i = 0; i < numInputs; i++) {
			for (int j = 0; j < numOutputs; j++) {
				if (i == j)
					switchingMatrix[i][j] = 1;
				else
					switchingMatrix[i][j] = 0;
			}
		}
	}

	public int getNumInputs() {
		return numInputs;
	}

	public void setNumInputs(int numInputs) {
		this.numInputs = numInputs;
	}

	public int getNumOutputs() {
		return numOutputs;
	}

	public void setNumOutputs(int numOutputs) {
		this.numOutputs = numOutputs;
	}

	public int getCrossStatus(int input, int output) {
		return switchingMatrix[input][output];
	}

	public void setCrossStatus(int status, int input, int output) {
		switchingMatrix[input][output] = status;
	}

	public void handleSignal(Signal s, Fiber fib) {
		attenuateSignal (s);
		int inPort = inPorts.indexOf(fib);
		for (int j = 0; j<numOutputs; j++) {
			if (switchingMatrix[inPort][j]==1) {
				this.setOutConnector(outPorts.get(j)); 
				break;
				}
			}
		sendSignal(s);
		}
			
	private void sendSignal(Signal s) {
		getOutConnector().handleSignal(s);
		
	}

	private void attenuateSignal(Signal s) {
		double pow = s.getPower();
		pow = pow - this.getReturnLoss() - this.getInsertionLoss();
		s.setPower(pow);
	}

	public void addInPortFiber (Fiber f) {
		inPorts.add(f);
	}
	
	public void addOutPortFiber (Fiber f) {
		outPorts.add(f);
	}

}
