import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;
/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a Default Style implementing StyleStrategy interface.
 */
public class DefaultStyle implements StyleStrategy{
	private String[] colors = new String[] { "0xED1C24", "0xF26522", "0xF7941D", 
			"0xFFF200", "0x8DC73F", "0x39B54A", "0x00A651", "0x00A99D", "0x00AEEF", 
			"0x0072BC", "0x0054A6", "0x2E3192", "0x662D91", "0x92278F", "0xEC008C", "0xED145B" };

	/**
	 * @param
	 */
	public DefaultStyle(){
		
	}// End of the constructor.
	
	/**
	 * Paint the Board.
	 * @param g2 to draw the board.
	 * @param board to draw the rectangle board.
	 * @param pits to draw all pits on the board.
	 */
	@Override
	public void paintBoard(Graphics2D g2, Double board, PitView[] pits) {
		g2.draw(board);
		g2.draw(pits[0]);
		g2.draw(pits[7]);
		
		for(int i = 1; i < 7; i++){
			g2.draw(pits[i]);
			paintPitStones(g2, pits[i]);
		}
		
		for(int i = 8; i < 14; i++){
			g2.draw(pits[i]);
			paintPitStones(g2, pits[i]);
		}
		paintMancalaStones(g2, pits[0]);
		paintMancalaStones(g2, pits[7]);
	}

	/**
	 * Paint the Pit Stones.
	 * @param g2 to draw pit stones.
	 * @param p to get pit view.
	 */
	@Override
	public void paintPitStones(Graphics2D g2, PitView p) {
		double centerX = p.getXPos() + PIT_WIDTH / 2;
		double centerY = p.getYPos() + PIT_WIDTH / 2;
		centerX -= PIT_WIDTH / 12;
		centerY -= PIT_WIDTH / 12;
		
		int offset = PIT_WIDTH / 6;
		double angle = 0;
		
		int j = 0;
		if(p.getNumStones() > 0){
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			g2.fill(stone);
			j++;
		}
		
		while(j < p.getNumStones() && j < 7){
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 3;
			j++;
		}
		
		offset += PIT_WIDTH / 6;
		while(j < p.getNumStones() && j < 19){
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 6;
			j++;
		}
	}// End of the method.

	/**
	 * Paint the Mancala Stones.
	 * @param g2 to draw Mancala stones.
	 * @param p to get PitView.
	 */
	@Override
	public void paintMancalaStones(Graphics2D g2, PitView p) {
		double centerX = p.getXPos() + PIT_WIDTH / 2;
		double centerY = DEFAULT_HEIGHT / 3;
		centerX -= PIT_WIDTH / 12;
		centerY -= PIT_WIDTH / 3;
		int offset = PIT_WIDTH / 6;
		double angle = 0;
		
		int j = 0;
		if(p.getNumStones() > 0){
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			g2.fill(stone);
			j++;
		}
		
		while(j < p.getNumStones() && j < 7){
			System.out.println(j + "" + p.getNumStones());
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 3;
			j++;
		}
		offset += PIT_WIDTH / 6;
		
		while(j < p.getNumStones() && j < 19){
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 6;
			j++;
		}
		offset -= PIT_WIDTH / 6;
		centerY = 2 * DEFAULT_HEIGHT / 3;
		centerY += PIT_WIDTH / 6;
		
		if(p.getNumStones() > 19){
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			g2.fill(stone);
			j++;
		}
		
		while(j < p.getNumStones() && j < 26){
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 3;
			j++;
		}
		offset += PIT_WIDTH / 6;
		
		while(j < p.getNumStones() && j < 38){	
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 6;
			j++;	
		}
		centerX = p.getXPos() + PIT_WIDTH / 2 - PIT_WIDTH / 12;
		centerY = DEFAULT_HEIGHT / 2 - PIT_WIDTH / 6;
		
		if(p.getNumStones() > 38){
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			j++;
		}
		offset -= PIT_WIDTH / 6;
		
		while(j < p.getNumStones() && j < 45){	
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			g2.fill(stone);
			angle += PI / 3;
			j++;	
		}
		offset += PIT_WIDTH / 8;
		angle = PI / 2;
		
		while(j < p.getNumStones()){
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			g2.fill(stone);
			angle += 2 * PI / 3;
			j++;
		}
	}// End of the method.
}// End of the class.