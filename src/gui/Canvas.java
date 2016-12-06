package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import components.OpticalComponent;

public class Canvas extends JPanel{
	
	private static ArrayList<OpticalComponent> komponente;

	private Point mousePt = new Point(800 / 2, 600 / 2);
	private Rectangle mouseRect = new Rectangle();
	private boolean selecting = false;
	Console console=null;

	public Canvas(){
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePt = e.getPoint();
	            if (e.isShiftDown()) {
//	                Node.selectToggle(nodes, mousePt);
	            } else if (e.isPopupTrigger()) {
	                selectOne(mousePt);
	                showPopup(e);
	            } else if (selectOne(mousePt)) {
	                selecting = false;
	            } else {
	                selectNone();
	                selecting = true;
	            }
	            e.getComponent().repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				selecting = false;
	            mouseRect.setBounds(0, 0, 0, 0);
	            if (e.isPopupTrigger()) {
	                showPopup(e);
	            }
	            e.getComponent().repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			Point delta = new Point();
			@Override
			public void mouseDragged(MouseEvent e) {
				if (selecting) {
	                mouseRect.setBounds(
	                    Math.min(mousePt.x, e.getX()),
	                    Math.min(mousePt.y, e.getY()),
	                    Math.abs(mousePt.x - e.getX()),
	                    Math.abs(mousePt.y - e.getY()));
	                selectRect(mouseRect);
	            } else {
	                delta.setLocation(
	                    e.getX() - mousePt.x,
	                    e.getY() - mousePt.y);
	                updatePosition(delta);
	                mousePt = e.getPoint();
	            }
	            e.getComponent().repaint();
			}
		});
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"), "delete");
		this.getActionMap().put("delete",new DeleteAction("delete"));
		
		setBackground(Color.WHITE);
		komponente=new ArrayList<OpticalComponent>();
	}
	
	public void addComponent(OpticalComponent component,Point p){
		OpticalComponent c=new OpticalComponent(component);
		c.setP(p);
		komponente.add(c);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (OpticalComponent opticalComponent : komponente) {
			opticalComponent.draw(g);
		}
		if (selecting) {
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                mouseRect.width, mouseRect.height);
        }
	}
	
	private void updatePosition(Point delta){
		for (OpticalComponent c : komponente) {
			if(c.isSelected()){
				c.setP(new Point(c.getP().x+delta.x,c.getP().y+delta.y));
			}
		}
	}
	
	private void selectRect(Rectangle r){
		for (OpticalComponent c : komponente) {
			c.setSelected(r.contains(c.getP()));
		}
	}
	
	private boolean selectOne(Point p){
		for (OpticalComponent c : komponente) {
            if (c.contains(p)) {
                if (!c.isSelected()) {
                    selectNone();
                    c.setSelected(true);
                }
                console.println(c.getLabel()+"|"+c.getInsertionLoss()+"|"+c.getReturnLoss());
                return true;
            }
        }
        return false;
	}
	private void selectNone(){
		for (OpticalComponent c : komponente) {
			c.setSelected(false);
		}
	}
	
	private void showPopup(MouseEvent e){
		Popup popup=new Popup(e,komponente);
	}

	public void deleteSelected() {
		ArrayList<OpticalComponent> zaIzbrisati= new ArrayList<>();
		for (OpticalComponent c : komponente) {
			if(c.isSelected()){
				zaIzbrisati.add(c);
			}
		}
		komponente.removeAll(zaIzbrisati);
	}
	
	private class DeleteAction extends AbstractAction{
		
		public DeleteAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteSelected();
			repaint();
		}
		
	}
}
