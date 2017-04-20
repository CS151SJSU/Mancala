import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class PitPanel extends JPanel implements ChangeListener{
	private Pits myPits;
	private Model model;
	private ArrayList<Hole> data;
	private ArrayList<Pits> pitList;
	private static final int DEFAULT_WIDTH = 60;
	private static final int DEFAULT_HEIGHT = 60;
	private final static int DEFAULT_STONE_X = 0;
	private final static int DEFAULT_STONE_Y = 0;
	
	public PitPanel(){
		model = new Model();
		myPits = new Pits();
		data = new ArrayList<Hole>();
		pitList = new ArrayList<Pits>();
		
		for(int i = 0; i < pitList.size(); i++){
			
		}
	}
	
	/**
	 * Update this pit with the most recent changes in model.
	 * @param e an event containing the model object.
	 */
	@Override
	public void stateChanged(ChangeEvent e){
		data = (ArrayList<Hole>) e.getSource();
		for(Hole d: data){
			d.getStonesCount();
		}
		this.repaint();
	}

	/**
	 * Gets copy of data.
	 * @return the data
	 */
	public Model getData(){
		return model;
	}
	/**
	 * Paint all the pits on the panel.
	 * @param pit
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		//Missing something on this line? Is that a JPanel?
		JPanel panel = new JPanel();
		for(Pits p: pitList){
			g2.draw(p);
		}
	}
	
	/**
	 * Creates an ellipse to use as a stone.
	 */
	public void drawStones(Pits p, Graphics g, int x, int y){
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(p);
	}
}
