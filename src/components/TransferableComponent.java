package components;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TransferableComponent implements Transferable {
	
	public static DataFlavor componentFlavor = new DataFlavor(OpticalComponent.class, "Optical component object");
	public static DataFlavor[] supportedFlavors = {componentFlavor, DataFlavor.stringFlavor};

	OpticalComponent component;
	
	public TransferableComponent(OpticalComponent component) {
		this.component=component;
	}
	
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		 if (flavor.equals(componentFlavor))
	         return component;
	     else if (flavor.equals(DataFlavor.stringFlavor)) 
	         return component.toString();
	     else 
	         throw new UnsupportedFlavorException(flavor);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(componentFlavor) || flavor.equals(DataFlavor.stringFlavor)) 
			return true;
	    return false;
	}

}
