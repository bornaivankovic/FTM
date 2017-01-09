package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import components.Fiber;
import components.OpticalComponent;
import components.Transmitter;

import java.io.*;

public class ArrayListDeserialization {
	
	private static String modelFolderPath = System.getProperty("user.dir") + File.separator + "Saved models" + File.separator;
	
	public ArrayListDeserialization () {
		
	}
	
	public Map deserializeList (String name) throws ClassNotFoundException, IOException {
		Map<String, ArrayList> map;
		FileInputStream fis;
		ObjectInputStream ois;
        fis = new FileInputStream(modelFolderPath+name);
        ois = new ObjectInputStream(fis);
		map = (Map<String, ArrayList>) ois.readObject();
        ois.close();
        fis.close();
        return map;
	}
}
