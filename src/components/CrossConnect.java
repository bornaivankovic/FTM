package components;

import java.awt.Point;
import java.util.ArrayList;

public class CrossConnect extends OpticalComponent {

	private int numInputs;
	private int numOutputs;
	private int[][] switchingMatrix;
	private ArrayList<Integer> inPorts = new ArrayList<Integer>();
	private ArrayList<Integer> outPorts = new ArrayList<Integer>();

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

	public void handleSingle(Signal s) {
		
		
	}

}
