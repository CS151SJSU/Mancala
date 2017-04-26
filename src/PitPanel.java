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
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 400;
	private static final int PIT_WIDTH = DEFAULT_WIDTH / 8;
	private static final int PIT_HEIGHT = DEFAULT_HEIGHT / 2;
	private static final int MANCALA_HEIGHT = DEFAULT_HEIGHT;
	private static final int DEFAULT_PITS_NUMBER = 14;
	private static final double PI = 3.14159265359;
	private Rectangle2D.Double board;
	
	public PitPanel(Model model)
	{
		this.setSize(500, 200);
		this.setLayout(new FlowLayout());
		data = new ArrayList<Hole>();
		myPits = new PitView[DEFAULT_PITS_NUMBER];
		board = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
			// Loop through 14 pits and put 4 initial stones in each pit.
			// Except 2 mancalas, which are in location #0 and #7.
			myPits[0] = new PitView(model, 0, 0, 0, 0, true);// The coordinate need to be changed
			myPits[7] = new PitView(model, 7, 0, DEFAULT_WIDTH - PIT_WIDTH, 0, true);
			for(int i = 1; i < 7; i++)
			{
				myPits[i] = new PitView(model, i, 4, PIT_WIDTH * i, 0);
			}
			for(int j = 8; j < 14; j++)
			{
				myPits[j] = new PitView(model, j, 4, PIT_WIDTH * j - DEFAULT_WIDTH + PIT_WIDTH, DEFAULT_HEIGHT / 2);
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
			double centerX = myPits[i].getXPos() + PIT_WIDTH / 2;
			double centerY = myPits[i].getYPos() + PIT_WIDTH / 2;
			centerX -= PIT_WIDTH / 12;
			centerY -= PIT_WIDTH / 12;
			int offset = PIT_WIDTH / 6;
			double angle = 0;
			int j = 0;
			if(myPits[i].getNumStones() > 0)
			{
				Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
				g2.draw(stone);
				j++;
			}
			while(j < myPits[i].getNumStones() && j < 7)
			{
				double x = centerX + Math.cos(angle) * offset;
				double y = centerY + Math.sin(angle) * offset;
				Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
		
				g2.draw(stone);
				angle += PI / 3;
				j++;
			}
			
			offset += PIT_WIDTH / 6;
			while(j < myPits[i].getNumStones() && j < 19)
			{
				double x = centerX + Math.cos(angle) * offset;
				double y = centerY + Math.sin(angle) * offset;
				Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
		
				g2.draw(stone);
				angle += PI / 6;
				j++;
			}
			
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