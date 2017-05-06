/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a Hole class implementing Clonable.
 */
public class Hole implements Cloneable {
	public static final int MANCALA_HOLE = 1;
	public static final int NORMAL_HOLE = 0; 
	private int id;
	private int stonesCount; // Number of stones currently in the hole
	private boolean isEmpty; // Is the hole empty?
	private int belongsto; // The id of the player that the hole belongs to (1 or 2)
	private int type; // Normal or Mancala?
	
	/**
	 * Create the Hole constructor.
	 * @param id to use the id.
	 * @param belongsto to use the belongsto.
	 * @param type to use the type.
	 * @param stonesCount to use the stonesCount.
	 */
	public Hole (int id, int belongsto, int type, int stonesCount){
		if(type != MANCALA_HOLE && type != NORMAL_HOLE) 
			throw new IllegalArgumentException();
		this.id=id;
		this.belongsto=belongsto;
		this.type = type;
		this.stonesCount=stonesCount;
	}// End of the constructor.
	
	/**
	 * Gets the id of each pit.
	 * @return the id of each pit.
	 */
	public int getId(){
		return id;
	}// End of the method.
	
	/**
	 * Sets the id of each pit.
	 * @param id to set the id of each pit.
	 */
	public void setId(int id){
		this.id = id;
	}// End of the method.
	
	/**
	 * Get the stones count.
	 * @return the stones count.
	 */
	public int getStonesCount(){
		return stonesCount;
	}// End of the method.
	
	/**
	 * Adds stones to each hole.
	 * @param stones to add stones in each hole
	 */
	public void addStones(int stones){
		this.stonesCount +=  stones;
	}// End of the method.
	
	/**
	 * Set the stone count.
	 * @param stonesCount to set the stone count.
	 */
	public void setStonesCount(int stonesCount) {
		this.stonesCount = stonesCount;
	}// End of the method.
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return (stonesCount==0);
	}// End of the method.
	
	/**
	 * 
	 * @param isEmpty
	 */
	public void setEmpty(boolean isEmpty){
		this.isEmpty = isEmpty;
	}// End of the method.
	
	/**
	 * 
	 * @return
	 */
	public int getBelongsto(){
		return belongsto;
	}// End of the method.
	
	/**
	 * 
	 * @param belongsto
	 */
	public void setBelongsto(int belongsto){
		this.belongsto = belongsto;
	}// End of the method.
	
	/**
	 * 
	 * @return
	 */
	public int getType(){
		return type;
	}// End of the method.
	
	/**
	 * 
	 * @param type
	 */
	public void setType(int type){
		this.type = type;
	}// End of the method.
	
	/**
	 * Create a clone method. 
	 * @throws CloneNotSupportedException.
	 */
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	} // End of the method
}// End of the class.