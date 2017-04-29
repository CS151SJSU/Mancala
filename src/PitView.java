import java.awt.geom.Ellipse2D;

public class PitView extends Ellipse2D.Double
{
	private int pitNumber;
	private int numStones;
	private int xPos;
	private int yPos;
	
	private static final int BOARD_WIDTH = 1000;
	private static final int BOARD_HEIGHT = 400;
	private static final int PIT_RADIUS = BOARD_WIDTH / 20;
	private static final int PIT_WIDTH = BOARD_WIDTH / 8;
	private static final int PIT_HEIGHT = BOARD_HEIGHT / 2;
	private static final int MANCALA_HEIGHT = BOARD_HEIGHT;
	
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos)
	{
		super(xPos, yPos, Math.min(PIT_WIDTH, PIT_HEIGHT), Math.min(PIT_WIDTH, PIT_HEIGHT));
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	//Make this constructor take an extra parameter to flag it for constructing a Mancala pit
	//I CANNOT wait to check the variable, as the super() statement must be the first statement
	//in the constructor, I must already know this is for Mancala pit, and make it bigger with super call
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos, boolean mancalaFlag)
	{
		//Make the height twice as large as normal pit for now
		super(xPos, yPos, PIT_WIDTH, MANCALA_HEIGHT);
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
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