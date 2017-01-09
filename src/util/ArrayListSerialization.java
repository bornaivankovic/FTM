package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import components.Fiber;
import components.OpticalComponent;
import components.Transmitter;

import java.io.*;

public class ArrayListSerialization {
	
	private static String modelFolderPath = System.getProperty("user.dir") + File.separator + "Saved models" + File.separator;
	
	public void serializeList (ArrayList<OpticalComponent> ocList, ArrayList<Fiber> fibList, ArrayList<Transmitter> tList, String name) {
		Map<String, ArrayList> map = new HashMap<String, ArrayList>();
		try {
			map.put("components", ocList);
			map.put("fibers", fibList);
			map.put("transmitters", tList);
			File directory = new File(modelFolderPath); 	//create directory for old modules if it doesn't exist
			directory.mkdir();
			
			FileOutputStream fos= new FileOutputStream(modelFolderPath+name);
		    ObjectOutputStream oos= new ObjectOutputStream(fos);
		    
		    oos.writeObject(map);
	        oos.close();
	        fos.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
