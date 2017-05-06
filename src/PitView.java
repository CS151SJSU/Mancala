import java.awt.geom.Ellipse2D;
/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a PitView class, which extends to Ellipse2D class.
 */
public class PitView extends Ellipse2D.Double{
	private int pitNumber;
	private int numStones;
	private int xPos;
	private int yPos;
	
	//These are all of the calculations for the board, pits, and Mancala.
	private static final int BOARD_WIDTH = 1000;
	private static final int BOARD_HEIGHT = 400;
	private static final int PIT_RADIUS = BOARD_WIDTH / 20;
	private static final int PIT_WIDTH = BOARD_WIDTH / 8;
	private static final int PIT_HEIGHT = BOARD_HEIGHT / 2;
	private static final int MANCALA_HEIGHT = BOARD_HEIGHT;
	
	/**
	 * Creates a PitView constructor for specifying
	 * the Normal holes.
	 * @param model to use the model.
	 * @param pitNumber to use the pit number.
	 * @param numStones to use the number of stones.
	 * @param xPos to use the x coordinate.
	 * @param yPos to use the y coordinate.
	 */
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos){
		super(xPos, yPos, Math.min(PIT_WIDTH, PIT_HEIGHT), Math.min(PIT_WIDTH, PIT_HEIGHT));
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
	}// End of the constructor
	
	/**
	 * Creates a PitView constructor for specifying
	 * the Mancala holes.
	 * @param model to use the model.
	 * @param pitNumber to use the pit number.
	 * @param numStones to use the number of stones.
	 * @param xPos to use the x coordinate.
	 * @param yPos to use the y coordinate.
	 * @param mancalaFlag to notify the mancala's flag.
	 */
	public PitView(Model model, int pitNumber, int numStones, 
			int xPos, int yPos, boolean mancalaFlag){
		super(xPos, yPos, PIT_WIDTH, MANCALA_HEIGHT);
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
	}// End of the constructor.
	
	/**
	 * Gets the pit number.
	 * @return the pit number.
	 */
	public int getPitNumber(){
		return pitNumber;
	}// End of the method.
	
	/**
	 * Gets the number of stones.
	 * @return the number of stones.
	 */
	public int getNumStones(){
		return numStones;
	}// End of the method.
	
	/**
	 * Sets the number of Stones.
	 * @param numStones to set the number of stones.
	 */
	public void setNumStones(int numStones){
		this.numStones = numStones;
	}// End of the method.
	
	/**
	 * Gets the X coordinate.
	 * @return the X coordinate.
	 */
	public int getXPos(){
		return xPos;
	}// End of the method.
	
	/**
	 * Gets the Y coordinate.
	 * @return the Y coordinate.
	 */
	public int getYPos(){
		return yPos;
	}// End of the method.
}// End of the class.