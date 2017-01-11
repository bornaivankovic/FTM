package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import components.Fiber;
import components.OpticalComponent;
import components.Transmitter;

public class ArrayListSerialization {

	public void serializeList(ArrayList<OpticalComponent> ocList, ArrayList<Fiber> fibList,
			ArrayList<Transmitter> tList, File f) {
		Map<String, ArrayList> map = new HashMap<String, ArrayList>();
		try {
			map.put("components", ocList);
			map.put("fibers", fibList);
			map.put("transmitters", tList);

			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(map);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
