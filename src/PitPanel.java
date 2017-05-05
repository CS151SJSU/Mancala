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
	private StyleStrategy style;
	private Point mousePosition;
	private Rectangle2D.Double board;
	
	private static final double PI = 3.14159265359;

	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 400;
	private static final int PIT_WIDTH = DEFAULT_WIDTH / 8;
	private static final int PIT_HEIGHT = DEFAULT_HEIGHT / 2;
	private static final int MANCALA_HEIGHT = DEFAULT_HEIGHT;
	private static final int DEFAULT_PITS_NUMBER = 14;

	
	
	public PitPanel(final Model model, StyleStrategy style)
	{
		this.style = style;
		this.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent event)
				{
					System.out.println("In mouse pressed");
					System.out.println("Turn -->" + model.getIsFirstPlayerTurn());
					
					mousePosition = event.getPoint();	//get Mouse Click Position
					for(int i = 0; i < myPits.length; i++)
					{
						if(myPits[i].contains(mousePosition)) //Check if clicked on a pit
						{
							System.out.println("Pit clicked -->" + myPits[i].getPitNumber());
							
							if(myPits[i].getPitNumber() == 0 || myPits[i].getPitNumber() == 7)
							{
								return;	//don't allow click on Mancala Pit
							}
							else if(myPits[i].getPitNumber() > 6 && model.getIsFirstPlayerTurn())
							{
								return;	//don't allow player 1 to select player 2 pit
							}
							else if(model.getIsFirstPlayerTurn() == false && myPits[i].getPitNumber() < 7)
							{
								return;	//don't allow player 2 to click player 1 pit
							}
							else if(myPits[i].getNumStones() == 0)
							{
								return;	//cant move if no stones in pit
							}
							else
							{
								System.out.print("Before update board");
								model.updateBoard(myPits[i].getPitNumber()); // do move
								System.out.print("After update board");
							}
						}
					}
				}
			  });
		this.model = model;
		this.setSize(1000, 400);
		this.setLayout(new FlowLayout());
//		data = new ArrayList<Hole>();
		myPits = new PitView[DEFAULT_PITS_NUMBER];
		board = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
			// Loop through 14 pits and put 4 initial stones in each pit.
			// Except 2 mancalas, which are in location #0 and #7.
			myPits[0] = new PitView(model, 0, 0, 0, 0, true);// The coordinate need to be changed
			myPits[7] = new PitView(model, 7, 0, DEFAULT_WIDTH - PIT_WIDTH, 0, true);
			for(int i = 1; i < 7; i++)
			{
				myPits[i] = new PitView(model, i, 4, PIT_WIDTH * i, DEFAULT_HEIGHT / 2);
			}
			for(int j = 8; j < 14; j++)
			{
				myPits[j] = new PitView(model, j, 4, DEFAULT_WIDTH - 2*PIT_WIDTH - (j-8)*PIT_WIDTH, 0);
			}

	}
	
	
	/**
	 * Update this pit with the most recent changes in model.
	 * @param e an event containing the model object.
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		System.out.println("In state changed");
		ArrayList<Hole> data = model.getData();
		System.out.println("Data size -->"+ data.size());
		int stones = 0;
		for(int i = 0; i < DEFAULT_PITS_NUMBER; i++)
		{
		   stones = data.get(i).getStonesCount();
		   myPits[i].setNumStones(stones);
		}
		repaint();
	}

	/**
	 * Paint the Pits on the Panel.
	 * @param pit
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		style.paintBoard(g2, board, myPits);
	}
}