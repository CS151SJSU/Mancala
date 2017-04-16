public class Hole implements Cloneable {

	public static final int MANCALA_HOLE = 1;
	public static final int NORMAL_HOLE = 0;
	private int id; // i do the hole from 0 to 13
	private int stonesCount; // No. of stones currently in the hole
	private boolean isEmpty; // Is the hole empty
	private int belongsto; // id of the player that the hole belongs to (0 or 1)
	private int type; // Normal or Mancala
	
	public Hole (int type)
	{
		if(type != MANCALA_HOLE && type != NORMAL_HOLE) 
			throw new IllegalArgumentException();
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStonesCount() {
		return stonesCount;
	}
	
	public void setStonesCount(int stonesCount) {
		this.stonesCount = stonesCount;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public int getBelongsto() {
		return belongsto;
	}
	
	public void setBelongsto(int belongsto) {
		this.belongsto = belongsto;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	} 
	
}