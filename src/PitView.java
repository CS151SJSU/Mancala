import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class PitView extends Ellipse2D.Double implements MouseListener
{
	private Model model;
	private int pitNumber;
	private int numStones;
	private int xPos;
	private int yPos;
	
	private static boolean player1Turn;
	
	private static final int BOARD_WIDTH = 1000;
	private static final int BOARD_HEIGHT = 400;
	private static final int PIT_RADIUS = BOARD_WIDTH / 20;
	private static final int PIT_WIDTH = BOARD_WIDTH / 8;
	private static final int PIT_HEIGHT = BOARD_HEIGHT / 2;
	private static final int MANCALA_HEIGHT = BOARD_HEIGHT;
	
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos)
	{
		super(xPos, yPos, PIT_WIDTH , PIT_WIDTH);
		this.model = model;
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
		player1Turn = true;
		
		
	}
	
	//Make this constructor take an extra parameter to flag it for constructing a Mancala pit
	//I CANNOT wait to check the variable, as the super() statement must be the first statement
	//in the constructor, I must already know this is for Mancala pit, and make it bigger with super call
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos, boolean mancalaFlag)
	{
		//Make the height twice as large as normal pit for now
		super(xPos, yPos, PIT_WIDTH, MANCALA_HEIGHT);
		this.model = model;
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
		player1Turn = true;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) 
	{
		System.out.println("NIPPLES");
		if(player1Turn && pitNumber > 6)	//If Player 1 turn, and click Player 2 pit
		{
			return;					//Do Nothing
		}
		else if(!player1Turn && pitNumber < 7)	//If Player 2 selects player 1 pit
		{
			return;				//Do Nothing
		}
		//Made sure Player clicked own pit, now ensure did not click empty pit
		else if(numStones == 0)		//If Player selects own empty pit
		{
			return;					//Do Nothing
		}
		else			//Player selected a valid pit
		{
			//NEED RAHUL TO CHANGE SIGNATURE OF UPDATEBOARD TO RETURN BOOLEAN
			//boolean goAgain = model.updateBoard(pitNumber);	//do the move and see if landed in on Mancala
			//If we didn't get an extra turn, switch turn by negating the value
			//if(!goAgain)
			{
				player1Turn = !player1Turn;
			}
			return;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		//DO NOTHING
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		//DO NOTHING
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		System.out.println("NIPPLES");
		if(player1Turn && pitNumber > 6)	//If Player 1 turn, and click Player 2 pit
		{
			return;					//Do Nothing
		}
		else if(!player1Turn && pitNumber < 7)	//If Player 2 selects player 1 pit
		{
			return;				//Do Nothing
		}
		//Made sure Player clicked own pit, now ensure did not click empty pit
		else if(numStones == 0)		//If Player selects own empty pit
		{
			return;					//Do Nothing
		}
		else			//Player selected a valid pit
		{
			//NEED RAHUL TO CHANGE SIGNATURE OF UPDATEBOARD TO RETURN BOOLEAN
			//boolean goAgain = model.updateBoard(pitNumber);	//do the move and see if landed in on Mancala
			//If we didn't get an extra turn, switch turn by negating the value
			//if(!goAgain)
			{
				player1Turn = !player1Turn;
			}
			return;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		//DO NOTHING
	}
	
	public int getPitNumber()
	{
		return pitNumber;
	}
	
	public int getNumStones()
	{
		return numStones;
	}
	
	public void setNumStones(int numStones)
	{
		this.numStones = numStones;
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	
}