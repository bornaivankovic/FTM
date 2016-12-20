package components;

import java.awt.Point;

public class CrossConnect extends OpticalComponent {
	
	private int numInputs;
	private int numOutputs;
	private int[][] switchingMatrix;

	public CrossConnect(String str, Point p, int height, int width, int inputs, int outputs) {
		super(str, p, height, width);
		this.numInputs = inputs;
		this.numOutputs = outputs;
		this.switchingMatrix = new int[numInputs][numOutputs];
		setDefaultSwitchingMatrix();
		
	}
	
	public CrossConnect(OpticalComponent c) {
		super(c);
	}
	
	private void setDefaultSwitchingMatrix () {
		// matrica prospajanja, ako je 1 znaci da prospaja, ako je 0 onda propušta
		// ulazi i
		// izlazi j
		// npr ako (i,j) = 1 tada se ulaz i prospaja na izlaz j, a ako (i,j) = 0 tada se ne prospaja
		for (int i=0; i<numInputs; i++) {
			for (int j=0; j<numOutputs; j++) {
				if (i==j)
					switchingMatrix[i][j]=1;
				else
					switchingMatrix[i][j]=0;
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
	
	public int getCrossStatus (int input, int output) {
		return switchingMatrix[input][output];
	}
	
	public void setCrossStatus(int status, int input, int output) {
		this.switchingMatrix[input][output] = status;
	}
	
}
