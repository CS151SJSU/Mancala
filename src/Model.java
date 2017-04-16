import java.util.ArrayList;

import javax.swing.event.ChangeListener;

public class Model {
	
	private ArrayList<Hole> data;
	private int currentHole; //at what hole should you start the current turn
	private boolean isFirstPlayerTurn; // Indicates whehter it is the turn of player 1 or player 2
	private ArrayList<ChangeListener> listeners;
	private PitPanel pits;
	
	public Model(){
		data = new ArrayList<Hole>();
		pits = new PitPanel();
		listeners = new ArrayList<ChangeListener>();
	}
	
	// Get the entire list of Holes
	public ArrayList<Hole> getData() {
		return (ArrayList<Hole>) data.clone();
	}
	
	// Getter and setter for the current hole
	public int getCurrentHole() {
		return currentHole;
	}
	public void setCurrentHole(int currentHole) {
		this.currentHole = currentHole;
	}
	
	// Getter and setter for for boolean variable indicating the whether it's the firs player's turn
	public boolean getIsFirstPlayerTurn() {
		return isFirstPlayerTurn;
	}
	
	public void setIsFirstPlayerTurn(boolean isFirstPlayerTurn) {
		this.isFirstPlayerTurn = isFirstPlayerTurn;
	}

	// Returns null in case of any exception; in case of correct index returns the object
	public Hole getHole(int id){
		try {
			return (Hole) data.get(id).clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		catch (IndexOutOfBoundsException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	public void attach(ChangeListener c) {
		listeners.add(c);
	}
	
	// Mutator of data model that gets called after the player plays a turn i.e Controller updates data
	public void updateBoard() {
		
	}
	
}
