import java.awt.FlowLayout;

import javax.swing.JFrame;
/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a MancalaTester class for testing everything.
 * MVC are included.
 */
public class MancalaTester {
	private static int numberOfPhones = 0;
	public static void main(String[] args){
		// Create a StyleStrategy array which lists 
		// Default Style and BlackWhiteStyle boards.
		StyleStrategy[] p = {new DefaultStyle(), new BlackWhiteStyle()};
		
		// Creates a frame for add everything in. 
		JFrame frame = new JFrame("Mancala Game");
		
		// Set the frame layout to the flow layout.
		//frame.setLayout(new FlowLayout());
		
		//Create a MainMenu for the game.
		MainMenu styleChoice = new MainMenu(frame, p);
		
		// Display the diaglog that include buttons.
		styleChoice.showDialog();
		
		// Attach everything in the Panel.
		Model model = new Model();
		StyleStrategy s = styleChoice.getStyle();
		PitPanel panel = new PitPanel(model, s);
		model.attach(panel);
		
		// Set the size of frame.
		frame.setSize(1250, 500);
		
		// Set the close-All button.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Add panel to frame.
		frame.add(panel);
		
		// Pack them up and display.
		//frame.pack();
		frame.setVisible(true);
		
		StoneMenu sm = new StoneMenu(frame);
		sm.setAlwaysOnTop(true);
		sm.showDialog();
		
		int k =sm.getNumStones();
		model.initStones(k);
		
	}// End of the main method.
}// End of the MancalaTester class