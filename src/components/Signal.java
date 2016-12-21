package components;

import java.util.ArrayList;

public class Signal {
	//wavelengthList polje valnih duljina, možemo još i mplementirat max ogranicenje broja kanala
	//wavelengthPower snaga signala određene valne duljine, svakom indexiranom signalu odgovara snaga 
	//na istom indeksu u ovom polju.
	private ArrayList<Integer> wavelengthArray = new ArrayList<Integer>();
	private double power;
	
	public Signal(/*int wavelength, double power2*/) {
		//this.wavelengthArray.add(wavelength);
		//this.power = power2;
	}
	
	public void addWavelength (int wavelength) {
		this.wavelengthArray.add(wavelength);
	}
	
	public void dropWavelength (int wavelength) {
		int index = wavelengthArray.indexOf(wavelength);
		wavelengthArray.remove(wavelength);
	}
	
	public void changeWavelength () {
		// moramo vidit kako to utječe na pojedinu komponentu
		// tipa da prolaskom kroz pretvornik sve valne duljine sputstimo/povećamo za neki iznos 
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}
	
	public int getWavelengthListSize () {
		return wavelengthArray.size();
	}
	
	public int getWavelength (int i) {
		return wavelengthArray.get(i);
	}
	
}
