package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.OpticalComponent;
import components.TransferableComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JComponent implements DragGestureListener{
	public Toolbar toolbar=new Toolbar();
	public Toolbox toolbox=new Toolbox();
	public Console console=new Console();
	public Canvas canvas=new Canvas();
	 
	
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
    	frame.getContentPane().setLayout(new BorderLayout());
    	GUI gui=new GUI();
    	frame.getContentPane().add(gui.toolbar,BorderLayout.NORTH);
    	frame.getContentPane().add(gui.toolbox,BorderLayout.WEST);
    	frame.getContentPane().add(new JScrollPane(gui.console,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
    	frame.getContentPane().add(gui.canvas,BorderLayout.CENTER);
    	gui.canvas.console=gui.console;
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
