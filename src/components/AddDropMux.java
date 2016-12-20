package components;

import java.awt.Point;
import java.util.ArrayList;

class AddDropMux extends OpticalComponent{
	
	private ArrayList<Integer> addWavelengths = new ArrayList<Integer>(); // lista valnih duljina koje simulator treba dodati
	private ArrayList<Integer> dropWavelengths = new ArrayList<Integer>(); // lista valnih duljina koje simulator treba droppati
	public AddDropMux(OpticalComponent c) {
		super(c);
	
	}
	
	public AddDropMux(String str, Point p, int height, int width) {
		super(str, p, height, width);
		
	}
	/*
	 dodavanje valnih duljina koje treba simulator dodati signalu
	 */
	public void setAddWavelengths (int wl) {
		this.addWavelengths.add(wl);
		
	}
	/*
	 getter za valne duljine koje simulator treba dodati, u petlji vati indexe preko ovog gettera 
	 */
	
	public int getAddWavelengths (int index) {
		return this.addWavelengths.get(index);
	}
	
	/*
	 isti scenarij kao i za dodane valne duljine
	 */
	public void setDropWavelengths (int wl) {
		this.dropWavelengths.add(wl);
		
	}

	public int getDropWavelengths(int index) {
		return this.dropWavelengths.get(index);
	}
}
