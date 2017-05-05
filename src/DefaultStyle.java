import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D.Double;

public class DefaultStyle implements StyleStrategy
{
	private final int numStones;
	
	public DefaultStyle(int numStones)
	{
		this.numStones = numStones;
	}
	
	@Override
	public void paintBoard(Graphics2D g2, Double board, PitView[] pits) 
	{
		g2.draw(board);
		g2.draw(pits[0]);
		g2.draw(pits[7]);
		for(int i = 1; i < 7; i++)
		{
			g2.draw(pits[i]);
			paintPitStones(g2, pits[i]);
		}
		for(int i = 8; i < 14; i++)
		{
			g2.draw(pits[i]);
			paintPitStones(g2, pits[i]);
		}
		paintMancalaStones(g2, pits[0]);
		paintMancalaStones(g2, pits[7]);
	}

	@Override
	public void paintPitStones(Graphics2D g2, PitView p) 
	{
		double centerX = p.getXPos() + PIT_WIDTH / 2;
		double centerY = p.getYPos() + PIT_WIDTH / 2;
		centerX -= PIT_WIDTH / 12;
		centerY -= PIT_WIDTH / 12;
		
		int offset = PIT_WIDTH / 6;
		double angle = 0;
		
		int j = 0;
		if(p.getNumStones() > 0)
		{
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			j++;
		}
		while(j < p.getNumStones() && j < 7)
		{
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 3;
			j++;
		}
		
		offset += PIT_WIDTH / 6;
		while(j < p.getNumStones() && j < 19)
		{
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 6;
			j++;
		}
	}

	@Override
	public void paintMancalaStones(Graphics2D g2, PitView p) 
	{
		double centerX = p.getXPos() + PIT_WIDTH / 2;
		double centerY = DEFAULT_HEIGHT / 3;
		centerX -= PIT_WIDTH / 12;
		centerY -= PIT_WIDTH / 3;
		int offset = PIT_WIDTH / 6;
		double angle = 0;
		
		int j = 0;
		if(p.getNumStones() > 0)
		{
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			j++;
		}
		while(j < p.getNumStones() && j < 7)
		{
			System.out.println(j + "" + p.getNumStones());
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 3;
			j++;
		}
		offset += PIT_WIDTH / 6;
		while(j < p.getNumStones() && j < 19)
		{
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 6;
			j++;
		}
		offset -= PIT_WIDTH / 6;
		centerY = 2 * DEFAULT_HEIGHT / 3;
		centerY += PIT_WIDTH / 6;
		if(p.getNumStones() > 19)
		{
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			j++;
		}
		while(j < p.getNumStones() && j < 26)
		{
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 3;
			j++;
		}
		offset += PIT_WIDTH / 6;
		while(j < p.getNumStones() && j < 38)
		{	
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 6;
			j++;	
		}
		centerX = p.getXPos() + PIT_WIDTH / 2 - PIT_WIDTH / 12;
		centerY = DEFAULT_HEIGHT / 2 - PIT_WIDTH / 6;
		if(p.getNumStones() > 38)
		{
			Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			j++;
		}
		offset -= PIT_WIDTH / 6;
		while(j < p.getNumStones() && j < 45)
		{	
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
	
			g2.draw(stone);
			angle += PI / 3;
			j++;	
		}
		offset += PIT_WIDTH / 8;
		angle = PI / 2;
		while(j < p.getNumStones())
		{
			double x = centerX + Math.cos(angle) * offset;
			double y = centerY + Math.sin(angle) * offset;
			Ellipse2D.Double stone = new Ellipse2D.Double(x, y, PIT_WIDTH / 6, PIT_WIDTH / 6);
			g2.draw(stone);
			angle += 2 * PI / 3;
			j++;
		}
	}

}
