package gui;

import java.awt.Color;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JPanel;

import components.OpticalComponent;
import components.TransferableComponent;

public class DropTargetListener extends DropTargetAdapter  {

	private DropTarget dropTarget;
    private Canvas canvas;

    public DropTargetListener(Canvas canvas) {
	    this.canvas = canvas;
	    dropTarget = new DropTarget(canvas, DnDConstants.ACTION_COPY, this, true, null);
    }
    
	@Override
	public void drop(DropTargetDropEvent event) {
		try {
	          Transferable tr = event.getTransferable();
	          OpticalComponent component = (OpticalComponent) tr.getTransferData(TransferableComponent.componentFlavor);

	            if (event.isDataFlavorSupported(TransferableComponent.componentFlavor)) {
	              event.acceptDrop(DnDConstants.ACTION_COPY);
	              this.canvas.addComponent(component,event.getLocation());
	              event.dropComplete(true);
	              this.canvas.repaint();
	              return;
	            }
	          event.rejectDrop();
	        } catch (Exception e) {
	          e.printStackTrace();
	          event.rejectDrop();
	        }
	}

}
