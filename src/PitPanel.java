import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;
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
	private static final int DEFAULT_PITS_NUMBER = 14;
	private Rectangle2D.Double board;
	
	public PitPanel()
	{
		model = new Model();
		data = new ArrayList<Hole>();
		myPits = new PitView[DEFAULT_PITS_NUMBER];
		board = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		for(int i = 0; i < DEFAULT_PITS_NUMBER; i++)
		{
			if(i == 0 && i == 7)
				myPits[i] = new PitView(model, i, 0, 0, 0, true);// The coordinate need to be changed
			else
				myPits[i] = new PitView(model, i, 4, 0, 0, false);// The coordinate need to be changed
		}
	}
	
	
	/**
	 * Update this pit with the most recent changes in model.
	 * @param e an event containing the model object.
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		data = model.getData();
		int stones = 0;
		for(int i = 0; i < DEFAULT_PITS_NUMBER; i++)
		{
		   stones = data.get(i).getStonesCount();
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
		g2.draw(board);
		for(int i = 0; i < DEFAULT_PITS_NUMBER; i++)
		{
			g2.draw(myPits[i]);
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
