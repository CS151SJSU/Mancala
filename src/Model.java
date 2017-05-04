import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	private static final int TOTAL_HOLES = 14;
	private static final int INITIAL_STONES_PER_HOLE = 4;
	private static final int FIRST_PLAYER_MANCALA = 7;
	private static final int SECOND_PLAYER_MANCALA = 0;
	private static final int GAME_NOT_OVER = 100;
	private ArrayList<Hole> data;
	// private Hole currentHole; //at what hole should you start the current
	// turn
	private boolean isFirstPlayerTurn; // Indicates whehter it is the turn of
										// player 1 or player 2
	private ArrayList<ChangeListener> listeners;

	public Model() {
		data = new ArrayList<Hole>();
		for (int i = 0; i < TOTAL_HOLES; i++) {
			int type, belongsTo, initialStonesCount;
			// Set whether the hole is normal or mancala
			if (i == 0 || i == TOTAL_HOLES / 2) {
				type = Hole.MANCALA_HOLE;
				initialStonesCount = 0;
			} else {
				type = Hole.NORMAL_HOLE;
				initialStonesCount = INITIAL_STONES_PER_HOLE;

			}

			// Set whether the hole belongs to player 1 or player 2
			if (i >= 1 && i <= 7)
				belongsTo = 1;
			else
				belongsTo = 2;
			data.add(new Hole(i, belongsTo, type, initialStonesCount));
		}
		listeners = new ArrayList<ChangeListener>();
		isFirstPlayerTurn = true;
	}

	// Get the entire list of Holes
	public ArrayList<Hole> getData() {
		return (ArrayList<Hole>) data.clone();
	}

	// Getter and setter for the current hole
	/*
	 * public int getCurrentHole() { return currentHole; }
	 */
	/*
	 * public void setCurrentHole(int currentHole) { this.currentHole =
	 * currentHole; }
	 */

	// Getter and setter for for boolean variable indicating the whether it's
	// the firs player's turn
	public boolean getIsFirstPlayerTurn() {
		return isFirstPlayerTurn;
	}

	/*
	 * public void setIsFirstPlayerTurn(boolean isFirstPlayerTurn) {
	 * this.isFirstPlayerTurn = isFirstPlayerTurn; }
	 */

	// Returns null in case of any exception; in case of correct index returns
	// the object
	public Hole getHole(int id) {
		try {
			return (Hole) data.get(id).clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		} catch (IndexOutOfBoundsException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	public void attach(ChangeListener c) {
		listeners.add(c);
	}

	// Mutator of data model that gets called after the player plays a turn i.e
	// Controller updates data
	public void updateBoard(int pitNumber) {
		
		System.out.println("At start of move stones");
		System.out.println("Pit No -->" + pitNumber);
		Hole lastHole = moveStones(pitNumber);
		System.out.println("Before game over");
		// check for game over
		gameOver();

		// Check and take care of the case where the stone getIsFirstPlayerTurngetIsFirstPlayerTurnends up in an empty hole on the side of the player playing the turn
		checkEmpty(lastHole);
		
		// if there is no additional turn then toggle or change the turn
		if (!isAdditionalTurn(lastHole))
			isFirstPlayerTurn = !isFirstPlayerTurn;

		// Inform listeners of data change
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
		
		for(int i = 0; i < data.size(); i++)
		{
			System.out.println(i + " " + data.get(i).getStonesCount());
		}

	}

	// Check for case when the stone ends up in an empty hole of the player
	// playing turn and handle it
	public void checkEmpty(Hole lastHole) {

		// Check 1. last hole was empty 2. It was a normal hole 3. The hole
		// belonged to the player whose turn it was
		if (lastHole.isEmpty() && lastHole.getType() == Hole.NORMAL_HOLE && belongsToCurrentPlayer(lastHole)) {
			// grab stones from the opposite hole && put it in the player's
			// Mancala
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

	}

	// Check for case when the turn ends up in the Mancala of the same player
	public boolean isAdditionalTurn(Hole lastHole) {
		// Give an additinal turn if the turn ends up at the Mancala of the
		// player playing
		if (lastHole.getType() == Hole.NORMAL_HOLE)
			return false;
		else
			return belongsToCurrentPlayer(lastHole);

	}

	// Checks whether the Hole belongs to the player who just played the turn
	public boolean belongsToCurrentPlayer(Hole lastHole) {
		return ((lastHole.getBelongsto() == 1 && isFirstPlayerTurn)
				|| (lastHole.getBelongsto() == 2 && !isFirstPlayerTurn));
	}

	// Move stones in anticlockwise manner and add them to subsequent pits
	public Hole moveStones(int pitNumber) {
		// Get the hole where the player played the turn
		Hole currentHole = (Hole) data.get(pitNumber);
		// Initialize the last hole to the current hole
		Hole lastHole = currentHole;
		// Get stones to be distributed
		int stonesDist = currentHole.getStonesCount();
		// Change the pits stones to zero
		currentHole.setStonesCount(0);
		
		/*
		 * I Changed this because the code I commented out below was walking out of bounds
		 * This may not be the most efficient solution, but it behaves as expected
		 */
		
		int index = pitNumber + 1;
		int stonesRemaining = stonesDist;
		while(stonesRemaining > 0)
		{
			if(index == 14)
			{
				index = 0;
			}
			Hole nextHole = (Hole) data.get(index);
			nextHole.setStonesCount(nextHole.getStonesCount() + 1);
			stonesRemaining--;
			if(stonesRemaining == 0)
			{
				lastHole = data.get(index);
			}
			index++;
		}
		
		// if there are more than 14 stones in the current Hole do a full round
		// of incrementing stones in all holes
	
		
		//int fullRound = stonesDist / TOTAL_HOLES;
		//int remStones = stonesDist % TOTAL_HOLES;
		//if (fullRound > 0)
		//	for (Hole hole : data)
		//		hole.setStonesCount(hole.getStonesCount() + 1);

		//if (remStones > 0) {
		//	for (int i = 1; i <= remStones; i++) {
		//		System.out.println("NEXT:" + (pitNumber + i));
		//		Hole nextHole = (Hole) data.get(pitNumber + i);		OUT OF BOUNDS HERE...IF pitNumber + i + remStones > 13
		//		nextHole.setStonesCount(nextHole.getStonesCount() + 1);
		//		// Get the last hole where the turn ended
		//		if (i == remStones)
		//			lastHole = data.get(pitNumber + i);
		//	}
		//}
		return lastHole;

	}

	// Checks whether the game is over and returns the id of the player who won.
	// In case game is not over returns GAME_NOT_OVER
	public int gameOver() {
		return GAME_NOT_OVER;

	}

}