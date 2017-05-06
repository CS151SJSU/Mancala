import java.awt.geom.Ellipse2D;
/**
 * COPYRIGHT (C) 2017 Hiep Nguyen. All Rights Reserved. 
 * CS 151 Kim: Project
 * Group 8: Rahul,Hiep Nguyen
 * @version 1.01 2017/20/2
 */

/*
 * 
 */
public class PitView extends Ellipse2D.Double{
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
	
	/**
	 * 
	 * @param model
	 * @param pitNumber
	 * @param numStones
	 * @param xPos
	 * @param yPos
	 */
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos){
		super(xPos, yPos, Math.min(PIT_WIDTH, PIT_HEIGHT), Math.min(PIT_WIDTH, PIT_HEIGHT));
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
	}// End of the constructor.
	
	/**
	 * 
	 * @param model
	 * @param pitNumber
	 * @param numStones
	 * @param xPos
	 * @param yPos
	 * @param mancalaFlag
	 */
	public PitView(Model model, int pitNumber, int numStones, int xPos, int yPos, boolean mancalaFlag){
		super(xPos, yPos, PIT_WIDTH, MANCALA_HEIGHT);
		this.pitNumber = pitNumber;
		this.numStones = numStones;
		this.xPos = xPos;
		this.yPos = yPos;
	}// End of the constructor.
	
	/**
	 * 
	 * @return
	 */
	public int getPitNumber(){
		return pitNumber;
	}// End of the method.
	
	/**
	 * 
	 * @return
	 */
	public int getNumStones(){
		return numStones;
	}// End of the method.
	
	/**
	 * 
	 * @param numStones
	 */
	public void setNumStones(int numStones){
		this.numStones = numStones;
	}// End of the method.
	
	/**
	 * 
	 */
	public int getXPos(){
		return xPos;
	}// End of the method.
	
	/**
	 * 
	 * @return
	 */
	public int getYPos(){
		return yPos;
	}// End of the method.
}// End of the class.