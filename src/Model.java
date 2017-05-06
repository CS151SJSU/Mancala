import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	public static final int TOTAL_HOLES = 14;
	public static final int INITIAL_STONES_PER_HOLE = 4;
	public static final int FIRST_PLAYER_MANCALA = 7;
	public static final int SECOND_PLAYER_MANCALA = 0;
	//private static final int GAME_NOT_OVER = 100;
	private boolean gameOver;
	private boolean extraChance;
	
	

	private ArrayList<Hole> data;
	private int[] lastStoneCount;

	private boolean isFirstPlayerTurn; // Indicates whehter it is the turn of player 1 or player 2
	
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
		lastStoneCount = new int[data.size()];
		listeners = new ArrayList<ChangeListener>();
		isFirstPlayerTurn = true;
		gameOver=false;
		extraChance=false;
		
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	// Get the entire list of Holes
	public ArrayList<Hole> getData() {
		return (ArrayList<Hole>) data.clone();
	}

	// Getter if it's the first player's turn
	public boolean getIsFirstPlayerTurn() {
		return isFirstPlayerTurn;
	}

	// Returns null in case of any exception; in case of correct index returns the object
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

	// Mutator of data model that gets called after the player plays a turn i.e Controller updates data
	public void updateBoard(int pitNumber) {
		
		//System.out.println("At start of move stones");
		//System.out.println("Pit No -->" + pitNumber);
		// Store the sone count for undo
		for(int i =0; i < TOTAL_HOLES; i++ )
			lastStoneCount[i] = data.get(i).getStonesCount(); 
		Hole lastHole = moveStones(pitNumber);
		//System.out.println("Before game over");
		

		// Check the case where the stone getIsFirstPlayerTurngetIsFirstPlayerTurnends up in an empty hole on the side of the player playing the turn
		checkEmpty(lastHole);
		
		// check for game over
				gameOver();
		//		System.out.println("After game over");
		
		// if there is no additional turn then toggle or change the turn
		if (!isAdditionalTurn(lastHole))
		{
			isFirstPlayerTurn = !isFirstPlayerTurn;
			extraChance = false;
		}
		else
			extraChance = true;

		// Inform listeners of data change
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}

	}

	// Check for case when the stone ends up in an empty hole of the player playing turn
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

	}

	// Check for case when the turn ends up in the Mancala of the same player
	public boolean isAdditionalTurn(Hole lastHole) {
		// Give an additinal turn if the turn ends up at the Mancala of the player playing
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
		int index = pitNumber + 1;
		int stonesRemaining = stonesDist;
		while(stonesRemaining > 0)
		{
			if(index == 14)
				index = 0;
			if( !(isFirstPlayerTurn && (index == SECOND_PLAYER_MANCALA)) && !(!isFirstPlayerTurn && (index == FIRST_PLAYER_MANCALA)) )
			{
				Hole nextHole = (Hole) data.get(index);
				nextHole.setStonesCount(nextHole.getStonesCount() + 1);
				stonesRemaining--;
				if(stonesRemaining == 0)
					lastHole = data.get(index);
			}
			
			index++;
		}
		
		return lastHole;

	}

	// Checks whether the game is over and accordingly set the gameOver var
	public void gameOver() {
		//System.out.println("In game over");
		boolean p1Empty = true, p2Empty = true;
		
		for(int i=1; i < (TOTAL_HOLES/2); i ++)
		{
			if(!data.get(i).isEmpty())
			{
				//System.out.println("Player 1 Non empty -->"+i);
				p1Empty = false;
				break;
			}
				
				
		}
		if(p1Empty)
		{
			Hole pl2Mancala = data.get(TOTAL_HOLES/2);
			for(int i=(TOTAL_HOLES/2)+1; i < TOTAL_HOLES; i ++)
			{
				pl2Mancala.setStonesCount(pl2Mancala.getStonesCount()+data.get(i).getStonesCount());
				data.get(i).setStonesCount(0);
			}
			gameOver=true;
		}
			
		for(int i=(TOTAL_HOLES/2)+1; i < TOTAL_HOLES; i ++)
		{
			if(data.get(i).getStonesCount() > 0)
			{
				//System.out.println("Player 2 Non empty -->"+i);
				p2Empty = false;
				break;
			}	
				
		}
		if(p2Empty)
		{
			Hole pl1Mancala = data.get(0);
			for(int i=1; i < (TOTAL_HOLES/2); i ++)
			{
				pl1Mancala.setStonesCount(pl1Mancala.getStonesCount()+data.get(i).getStonesCount());
				data.get(i).setStonesCount(0);
			}
			gameOver=true;
		}
		
		//System.out.println("Game Over ---->"+gameOver);

	}
	
	//Handle undo
	public void undo()
	{
		for(int i = 0; i < data.size(); i++)
			data.get(i).setStonesCount(lastStoneCount[i]);
		
		if(!extraChance)
			isFirstPlayerTurn = !isFirstPlayerTurn;
		// Inform listeners of data change
				for (ChangeListener l : listeners) {
					l.stateChanged(new ChangeEvent(this));
				}		
	}

}