import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * StoneMenu class extends JDialog to allow user to set the number of starting stones to 3 or 4 
 * @author Group2
 */
public class StoneMenu extends JDialog
{
	/** Hold the JDialog content pane */
	private Container pane;
	
	/** The number of starting stones per pit */
	private int numStones = 3;
	
	/** Menu width in pixels*/
	private final int WIDTH = 400;
	
	/** Menu height in pixels */
	private final int HEIGHT = 200;
	
	/**
	 * Normal Constructor
	 * @param owner		the JFrame associated with this dialog
	 */
	public StoneMenu(Frame owner)
	{
		super(owner, true);			//initialize super class
		pane = getContentPane();	//get the content pane
		setSize(WIDTH, HEIGHT);		//set the size of the menu
		
		/** JPanel to add menu buttons to */
		JPanel stonePanel = new JPanel();
		
		/** JLabel instructing user what to do */
		JLabel instructions = new JLabel("Choose the number of starting stones");
		
		/** A ButtonGroup which allows only 1 of the group to be selected at a time */
		ButtonGroup stoneMutex = new ButtonGroup();
		
		/** Radio style button to select 3 stones */
		JRadioButton threeButton = new JRadioButton("Three", true);
		
		/** Radio style button to select 4 stones */
		JRadioButton fourButton = new JRadioButton("Four", false);
		/** Add actionListener to set number of stones to 3 when three button pressed */
		threeButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						numStones = 3;
					}
					
				});
		/** Add actionListener to set number of stones to 4 when three button pressed */
		fourButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) 
					{
						numStones = 4;
					}
			
				});
		/* Add the buttons to the panel */
		stonePanel.add(threeButton);
		stonePanel.add(fourButton);
		
		/* Add the buttons to the button group */
		stoneMutex.add(threeButton);
		stoneMutex.add(fourButton);
		
		/** JButton to start game when selected */
		JButton startButton = new JButton("Start Game");
		
		/** JButton actionListener to dispose of this menu when user presses button */
		startButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						pane.setVisible(false);
						dispose();
					}
					
				});
		/* format the content pane and add the panel with radio buttons, the start button, and instructions */
		pane.setLayout(new BorderLayout());
		pane.add(instructions, BorderLayout.NORTH);
		pane.add(stonePanel, BorderLayout.CENTER);
		pane.add(startButton, BorderLayout.SOUTH);
		setResizable(false);
		
	}
	
	/**
	 * Get the number of stones the user selected
	 * @return	int number of stones selected by user
	 */
	public int getNumStones()
	{
		return numStones;
	}
	
	/** Make the menu visible to interact with */
	public void showDialog()
	{
		setVisible(true);
	}
}