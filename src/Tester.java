import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Tester 
{
	public static void main(String[] args) 
	{

		StyleStrategy[] p = {new DefaultStyle(), new BlackWhiteStyle()};
		JFrame frame = new JFrame();
		//frame.setLayout(new FlowLayout());
		MainMenu styleChoice = new MainMenu(frame, p);
		styleChoice.showDialog();
		Model model = new Model();
		StyleStrategy s = styleChoice.getStyle();
		PitPanel panel = new PitPanel(model, s);
		model.attach(panel);
		
		
		
		frame.setSize(1250, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//frame.pack();
		frame.setVisible(true);
	}
}
