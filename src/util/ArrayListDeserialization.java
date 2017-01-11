package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;

public class ArrayListDeserialization {

	public ArrayListDeserialization() {

	}

	public Map deserializeList(File f) throws ClassNotFoundException, IOException {
		Map<String, ArrayList> map;
		FileInputStream fis;
		ObjectInputStream ois;
		fis = new FileInputStream(f);
		ois = new ObjectInputStream(fis);
		map = (Map<String, ArrayList>) ois.readObject();
		ois.close();
		fis.close();
		return map;
	}
}
