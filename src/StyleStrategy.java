import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface StyleStrategy 
{
	public static final double PI = 3.14159265359;
	
	public static final int DEFAULT_WIDTH = 1000;
	public static final int DEFAULT_HEIGHT = 400;
	public static final int PIT_WIDTH = DEFAULT_WIDTH / 8;
	public static final int PIT_HEIGHT = DEFAULT_HEIGHT / 2;
	public static final int MANCALA_HEIGHT = DEFAULT_HEIGHT;
	public static final int DEFAULT_PITS_NUMBER = 14;
	
	
	public void paintBoard(Graphics2D g2, Rectangle2D.Double board, PitView[] pits);
	
	public void paintPitStones(Graphics2D g2, PitView p);
	
	public void paintMancalaStones(Graphics2D g2, PitView p);
}
