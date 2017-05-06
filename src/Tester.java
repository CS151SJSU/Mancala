import javax.swing.JFrame;

public class Tester 
{
	public static void main(String[] args) 
	{
		Model model = new Model();
		StyleStrategy s = new BlackWhiteStyle(4);
		PitPanel panel = new PitPanel(model, s);
		model.attach(panel);
		JFrame frame = new JFrame();
		frame.setSize(1250, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//frame.pack();
		frame.setVisible(true);
	}
}