import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.*;

public class PitPanel extends JPanel implements ChangeListener{
	private Pits myPits;
	private Model data;
	
	public PitPanel(){
		data = new Model();
	}
	
	/**
	 * Somthing changed
	 */
	@Override
	public void stateChanged(ChangeEvent e){
		data = (Datamodel) e.getSource();
		for(){
			
			
		}
		repaint();
	}

	/**
	 * Gets copy of data.
	 * @return the Data model
	 */
	public DataModel getData(){
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
	public void drawStones(Pit p, Graphic g, int x, int y){
		Graphics2D g2 = (Graphics2D) g;
		
	}
}