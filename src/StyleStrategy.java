import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a StyleStrategy interface.
 */
public interface StyleStrategy {
	public static final double PI = 3.14159265359;
	
	public static final int DEFAULT_WIDTH = 1000;
	public static final int DEFAULT_HEIGHT = 400;
	public static final int PIT_WIDTH = DEFAULT_WIDTH / 8;
	public static final int PIT_HEIGHT = DEFAULT_HEIGHT / 2;
	public static final int MANCALA_HEIGHT = DEFAULT_HEIGHT;
	public static final int DEFAULT_PITS_NUMBER = 14;
	
	/**
	 * Paint the Board.
	 * @param g2 to draw the board.
	 * @param board to draw the rectangle board.
	 * @param pits to draw all pits on the board.
	 */
	public void paintBoard(Graphics2D g2, Rectangle2D.Double board, PitView[] pits);
	
	/**
	 * Paint the Pit Stones.
	 * @param g2 to draw pit stones.
	 * @param p to get pit view.
	 */
	public void paintPitStones(Graphics2D g2, PitView p);
	
	/**
	 * Paint the Mancala Stones.
	 * @param g2 to draw Mancala stones.
	 * @param p to get PitView.
	 */
	public void paintMancalaStones(Graphics2D g2, PitView p);
}// End of the interface.
