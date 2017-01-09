package util;

import java.util.ArrayList;
import java.util.List;

import components.Fiber;
import components.OpticalComponent;
import components.Transmitter;

import java.io.*;

public class ArrayListDeserialization {
	
	private static String modelFolderPath = System.getProperty("user.dir") + File.separator + "Saved models" + File.separator;
	
	public ArrayList deserializeList (String type) {
		ArrayList arraylist;
		try
        {	
            FileInputStream fis = new FileInputStream(modelFolderPath+type);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arraylist = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             ioe.printStackTrace();
             return null;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return null;
          }
		return arraylist;
	}
}
