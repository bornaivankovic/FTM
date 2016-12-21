package components;

import java.awt.Point;
import java.util.ArrayList;

public class AddDropMux extends OpticalComponent {
	// lista valnih duljina koje simulator treba dodati
	private ArrayList<Integer> addWavelengths = new ArrayList<Integer>();
	// lista valnih duljina koje simulator treba droppati
	private ArrayList<Integer> dropWavelengths = new ArrayList<Integer>();

	public AddDropMux(OpticalComponent c) {
		super(c);
		// setImgPath("D:\\Code\\Java\\FTM\\icons\\adm.png");

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

	/*
	 * isti scenarij kao i za dodane valne duljine
	 */
	public void setDropWavelengths(int wl) {
		dropWavelengths.add(wl);

	}

	public int getDropWavelengths(int index) {
		return dropWavelengths.get(index);
	}
}
