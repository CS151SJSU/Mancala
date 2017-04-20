import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class PitPanel extends JPanel implements ChangeListener
{
	private PitView[] myPits;
	private Model model;
	private ArrayList<Hole> data;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 200;
	private static final int NUM_PITS = 14;
	private final static int DEFAULT_STONE_X = 0;
	private final static int DEFAULT_STONE_Y = 0;
	
	public PitPanel()
	{
		model = new Model();
		data = new ArrayList<Hole>();
		myPits = new PitView[NUM_PITS];
		
		for(int i = 0; i < NUM_PITS; i++)
		{
			myPits[i] = new PitView(model, i, 4, 0, 0); //4 stones, draw at 0,0 for now, need to change location
		}
	}
	
	/**
	 * Update this pit with the most recent changes in model.
	 * @param e an event containing the model object.
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		data = model.getData()
		int stones = 0;
		for(int i = 0; i < NUM_PITS; i++)
		{
		   stones = data.get(i).getNumStones;
		   myPits[i].setNumStones(stones);
		}
		this.repaint();
	}

	/**
	 * Gets copy of data.
	 * @return the data
	 */
	public Model getData(){
		return model;
	}
	/**
	 * Paint the Pits on the Panel.
	 * @param pit
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//Missing something on this line?
		
		for(int i = 0; i < NUM_PITS; i++){
			g2.draw(p);
		}
	}
	
	/**
	 * Creates an ellipse to use as a stone.
	 */
	public void drawStones(PitView p, Graphics g, int x, int y)
	{
		//Graphics2D g2 = (Graphics2D) g;
		//g2.draw(p);
	}
}
