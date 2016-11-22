package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.OpticalComponent;
import components.TransferableComponent;

public class GUI extends JComponent implements DragGestureListener{
	private Toolbar toolbar=new Toolbar();
	private Toolbox toolbox=new Toolbox();
	private Console console=new Console();
	private Canvas canvas=new Canvas();
	private ArrayList<OpticalComponent> komponente=new ArrayList<OpticalComponent>();
	 
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            }
        });
	}
	
	public GUI(){
		this.setOpaque(true);
	}
	
	private static void createAndShowGUI(){
		JFrame frame=new JFrame("Simulator opticke mreze");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(new Dimension(800, 600));
    	frame.setLayout(new BorderLayout());
    	GUI gui=new GUI();
    	frame.add(gui.toolbar,BorderLayout.NORTH);
    	frame.add(gui.toolbox,BorderLayout.WEST);
    	frame.add(gui.console,BorderLayout.SOUTH);
    	frame.add(gui.canvas,BorderLayout.CENTER);
    	DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(gui.toolbox, DnDConstants.ACTION_COPY, gui);
    	new DropTargetListener(gui.canvas);
    	frame.pack();
    	frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
        JPanel panel = (JPanel) event.getComponent();

        OpticalComponent component = this.toolbox.getOpticalComponent(event.getDragOrigin());

        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }

        event.startDrag(cursor, new TransferableComponent(component));
	}
}
