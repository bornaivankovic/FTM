package util;

import java.util.ArrayList;
import java.util.List;

import components.Fiber;
import components.OpticalComponent;
import components.Transmitter;

import java.io.*;

public class ArrayListSerialization {
	
	private static String modelFolderPath = System.getProperty("user.dir") + File.separator + "Saved models" + File.separator;
	
	public void serializeList (ArrayList ocList, String type) {
		try {
			File directory = new File(modelFolderPath); 	//create directory for old modules if it doesn't exist
			directory.mkdir();
			
			FileOutputStream fos= new FileOutputStream(modelFolderPath+type);
		    ObjectOutputStream oos= new ObjectOutputStream(fos);
		    
		    oos.writeObject(ocList);
	        oos.close();
	        fos.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
