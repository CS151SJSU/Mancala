import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


import javax.swing.*;
import javax.swing.event.*;

public class PitPanel extends JPanel implements ChangeListener{
	private Pits myPits;
	private Model data;
	
	public PitPanel(){
		data = new Model();
		myPits = new Pits();
	}
	
	/**
	 * Update this pit with the most recent changes in model.
	 * @param e an event containing the model object.
	 */
	@Override
	public void stateChanged(ChangeEvent e){
		data = (Model) e.getSource();
		repaint();
	}

	/**
	 * Gets copy of data.
	 * @return the data
	 */
	public Model getData(){
		return data;
	}
	
	/**
	 * Add pits onto this panel.
	 */
	public void initializePits(){
		
	}

	/**
	 * Creates an ellipse to use as a stone.
	 */
	public void drawStones(Pits p, Graphics g, int x, int y){
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(p);
	}

}
