public class Hole implements Cloneable {

	public static final int MANCALA_HOLE = 1;
	public static final int NORMAL_HOLE = 0;
	private int id; // i do the hole from 0 to 13 in an anticlockwise direction starting from the left Mancala
	private int stonesCount; // No. of stones currently in the hole
	//private boolean isEmpty; // Is the hole empty
	private int belongsto; // id of the player that the hole belongs to (1 or 2)
	private int type; // Normal or Mancala
	
	public Hole (int id, int belongsto, int type, int stonesCount){
		if(type != MANCALA_HOLE && type != NORMAL_HOLE) 
			throw new IllegalArgumentException();
		this.id=id;
		this.belongsto=belongsto;
		this.type = type;
		this.stonesCount=stonesCount;
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
	
	public void addStones(int stones)
	{
		this.stonesCount +=  stones;
	}
	
	public void setStonesCount(int stonesCount) {
		this.stonesCount = stonesCount;
	}
	
	public boolean isEmpty() {
		return (stonesCount==0);
	}
	
	/*public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}*/
	
	public int getBelongsto() {
		return belongsto;
	}
	
	/*public void setBelongsto(int belongsto) {
		this.belongsto = belongsto;
	}*/
	
	public int getType() {
		return type;
	}
	
	/*public void setType(int type) {
		this.type = type;
	}*/
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	} 
	
}