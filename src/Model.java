import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a Model class for the logic of the game.
 */
public class Model {
	public static final int TOTAL_HOLES = 14;
	public static final int INITIAL_STONES_PER_HOLE = 4;
	public static final int FIRST_PLAYER_MANCALA = 7;
	public static final int SECOND_PLAYER_MANCALA = 0;
	//private static final int GAME_NOT_OVER = 100;
	private boolean gameOver;
	private boolean extraChance;
	private boolean initialized;
	
	// Call private data structure holding data.
	private ArrayList<Hole> data;
	private int[] lastStoneCount; // The last hole count.
	
	// Indicates whehter it is the turn of player 1 or player 2.
	private boolean isFirstPlayerTurn; 
	
	// Call private data structure holding Views.
	private ArrayList<ChangeListener> listeners;

	/**
	 * Constructor of Model is used for placing the total of hole 
	 * and specifying the player's turn.
	 */
	public Model() {
		data = new ArrayList<Hole>();
		for (int i = 0; i < TOTAL_HOLES; i++){
			int type, belongsTo, initialStonesCount;
			// Set whether the hole is normal or Mancala.
			if (i == 0 || i == TOTAL_HOLES / 2){
				type = Hole.MANCALA_HOLE;
				initialStonesCount = 0;
			}else{
				type = Hole.NORMAL_HOLE;
				initialStonesCount = INITIAL_STONES_PER_HOLE;
			}

			// Set whether the hole belongs to player 1 or player 2.
			if (i >= 1 && i <= 7)
				belongsTo = 1; // Player 1.
			else
				belongsTo = 2; // Player 2.
			data.add(new Hole(i, belongsTo, type, initialStonesCount));
		}
		
		lastStoneCount = new int[data.size()];
		listeners = new ArrayList<ChangeListener>();
		isFirstPlayerTurn = true;
		gameOver=false;
		extraChance=false;
		initialized = false;
	}// End of the constructor.
	
	/**
	 * Is the game over?.
	 * @return game over.
	 */
	public boolean isGameOver() {
		return gameOver;
	}// End of the method.

	/**
	 * Get the entire list of Holes
	 * @return The clone from Hole.
	 */
	public ArrayList<Hole> getData() {
		return (ArrayList<Hole>) data.clone();
	}// End of the method.

	/**
	 * Getter if it's the first player's turn
	 * @return
	 */
	public boolean getIsFirstPlayerTurn() {
		return isFirstPlayerTurn;
	}// End of the method.

	/**
	 * Returns null in case of any exception; 
	 * in case of correct index returns the object
	 * @param id
	 * @return
	 */
	public Hole getHole(int id){
		try{
			return (Hole) data.get(id).clone();
		}catch (CloneNotSupportedException e1){
			e1.printStackTrace();
		}catch (IndexOutOfBoundsException e2){
			e2.printStackTrace();
		}
		
		return null;
	}// End of the method

	/**
	 * 
	 * @param c
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}// End of the method.

	/**
	 * Mutator of data model that gets called after the 
	 * player plays a turn i.e Controller updates data
	 * @param pitNumber
	 */
	public void updateBoard(int pitNumber) {
		//System.out.println("At start of move stones");
		//System.out.println("Pit No -->" + pitNumber);
		
		// Store the some count for undo
		initialized = true;
		
		for(int i =0; i < TOTAL_HOLES; i++ )
			lastStoneCount[i] = data.get(i).getStonesCount(); 
		Hole lastHole = moveStones(pitNumber);
		//System.out.println("Before game over");

		/*
		 * Check the case where the stone getIsFirstPlayerTurngetIsFirstPlayerTurnends up 
		 * in an empty hole on the side of the player playing the turn.
		 */
		checkEmpty(lastHole);
		
		gameOver();// Check for game over
		//System.out.println("After game over");
		
		/**
		 * If there is no additional turn, 
		 * then toggle or change the turn
		 */
		if (!isAdditionalTurn(lastHole)){
			isFirstPlayerTurn = !isFirstPlayerTurn;
			extraChance = false;
		}else
			extraChance = true;

		// Inform listeners of data change
		for (ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}// End of the method.

	/**
	 * Check for case when the stone ends up in an empty hole of the player playing turn
	 * @param lastHole
	 */
	public void checkEmpty(Hole lastHole) {

		// Check 1. last hole was empty before moving stones i.e it has 1 stone now 
		//2. It was a normal hole 
		//3. The hole belonged to the player whose turn it was
		if (lastHole.getStonesCount()==1 && lastHole.getType() == Hole.NORMAL_HOLE && belongsToCurrentPlayer(lastHole)) {
			// Grab stones from the opposite hole && put it in the player's Mancala
			Hole mancala, oppositeHole;
			if (isFirstPlayerTurn)
				mancala = data.get(FIRST_PLAYER_MANCALA);
			else
				mancala = data.get(SECOND_PLAYER_MANCALA);
			oppositeHole = data.get(TOTAL_HOLES - lastHole.getId());
			mancala.addStones(lastHole.getStonesCount() + oppositeHole.getStonesCount());
			// Once added set the count of both the holes to zero
			lastHole.setStonesCount(0);
			oppositeHole.setStonesCount(0);
		}
	}// End of the method.

	/**
	 * Check for case when the turn ends up in the Mancala of the same player
	 * @param lastHole
	 * @return
	 */
	public boolean isAdditionalTurn(Hole lastHole) {
		// Give an additinal turn if the turn ends up at the Mancala of the player playing
		if (lastHole.getType() == Hole.NORMAL_HOLE)
			return false;
		else
			return belongsToCurrentPlayer(lastHole);
	}// End of the method.

	/**
	 * Checks whether the Hole belongs to the player who just played the turn.
	 * @param lastHole lastHole to check.
	 * @return Whether the Hole belongs to who.
	 */
	public boolean belongsToCurrentPlayer(Hole lastHole) {
		return ((lastHole.getBelongsto() == 1 && isFirstPlayerTurn)
				|| (lastHole.getBelongsto() == 2 && !isFirstPlayerTurn));
	}// End of the method.

	/**
	 * Move stones in anti-clockwise manner and add them to subsequent pits
	 * @param pitNumber
	 * @return The last hole.
	 */
	public Hole moveStones(int pitNumber){
		// Get the hole where the player played the turn
		Hole currentHole = (Hole) data.get(pitNumber);
		
		// Initialize the last hole to the current hole
		Hole lastHole = currentHole;
		
		// Get stones to be distributed
		int stonesDist = currentHole.getStonesCount();
		
		// Change the pits stones to zero
		currentHole.setStonesCount(0);
		int index = pitNumber + 1;
		int stonesRemaining = stonesDist;
		
		while(stonesRemaining > 0){
			if(index == 14)
				index = 0;
			if(!(isFirstPlayerTurn && (index == SECOND_PLAYER_MANCALA)) 
					&& !(!isFirstPlayerTurn && (index == FIRST_PLAYER_MANCALA))){
				Hole nextHole = (Hole) data.get(index);
				nextHole.setStonesCount(nextHole.getStonesCount() + 1);
				stonesRemaining--;
				if(stonesRemaining == 0)
					lastHole = data.get(index);
			}
			
			index++;
		}
		
		return lastHole;
	}// End of the method.

	/**
	 * Checks whether the game is over and accordingly set the gameOver var.
	 */
	public void gameOver(){
		//System.out.println("In game over");
		boolean p1Empty = true, p2Empty = true;
		
		for(int i = 1; i < 7; i ++){
			if(!data.get(i).isEmpty()){
				//System.out.println("Player 1 Non empty -->"+i);
				p1Empty = false;
				break;
			}	
		}
		
		if(p1Empty){
			Hole pl2Mancala = data.get(0);
			for(int i=8; i < TOTAL_HOLES; i ++){
				pl2Mancala.setStonesCount(pl2Mancala.getStonesCount()+data.get(i).getStonesCount());
				data.get(i).setStonesCount(0);
			}
			gameOver=true;
		}
			
		for(int i=(TOTAL_HOLES/2)+1; i < TOTAL_HOLES; i ++){
			if(data.get(i).getStonesCount() > 0){
				//System.out.println("Player 2 Non empty -->"+i);
				p2Empty = false;
				break;
			}		
		}
		
		if(p2Empty){
			Hole pl1Mancala = data.get(7);
			for(int i=1; i < (TOTAL_HOLES/2); i ++){
				pl1Mancala.setStonesCount(pl1Mancala.getStonesCount()+data.get(i).getStonesCount());
				data.get(i).setStonesCount(0);
			}
			gameOver=true;
		}
		
		//System.out.println("Game Over ---->"+gameOver);
	}// End of the method.
	
	/**
	 * Undo the last move that players make.
	 */
	public void undo(){
		if(!initialized){
			return;
		}
		
		for(int i = 0; i < data.size(); i++)
			data.get(i).setStonesCount(lastStoneCount[i]);
		
		if(!extraChance)
			isFirstPlayerTurn = !isFirstPlayerTurn;
		// Inform listeners of data change
				for (ChangeListener l : listeners) {
					l.stateChanged(new ChangeEvent(this));
				}		
	}// End of the method.
	
	/**
	 * Initialize the number of stones.
	 * @param numStones numStones to initialize.
	 */
	public void initStones(int numStones){
		for(int i = 1; i < 7; i++){
			data.get(i).setStonesCount(numStones);
		}
		
		for(int i = 8; i < 14; i++){
			data.get(i).setStonesCount(numStones);
		}
		
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}// End of the method.
	
	/**
	 * Gets initialized stones.
	 * @return The initialized ones.
	 */
	public boolean getInitialized(){
		return initialized;
	}// End of the method.
}// End of the class.