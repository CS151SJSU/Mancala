import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a PitPanel class extending to JPanel class 
 * and implementing to ChangeListener interface.
 */
public class PitPanel extends JPanel implements ChangeListener{
	private PitView[] myPits;
	private Model model;
	private StyleStrategy style;
	private Point mousePosition;
	private Rectangle2D.Double board;
	private JTextField turnIndicator; // This is View
	private JTextField errorToast; // This is View
	private static int lastTurn;
	private int undoCount;
	
	private static final double PI = 3.14159265359;

	// These are all of the calculations.
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 400;
	private static final int PIT_WIDTH = DEFAULT_WIDTH / 8;
	private static final int PIT_HEIGHT = DEFAULT_HEIGHT / 2;
	private static final int MANCALA_HEIGHT = DEFAULT_HEIGHT;
	private static final int DEFAULT_PITS_NUMBER = 14;
	
	// These are for undo button.
	private static final int NORMAL_TURN=0;
	private static final int UNDO_TURN=1;

	/**
	 * Create a PitPanel constructor using 
	 * the MouseListener for clicking on the Mancala board.
	 * @param model to use the model from the Model class.
	 * @param style to use the style from the StyleStrategy interface.
	 */
	public PitPanel(final Model model, StyleStrategy style){
		this.style = style;
		this.undoCount=0;
		
		//Add the MouseListener
		this.addMouseListener(new 
				MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent event){
					errorToast.setVisible(false);
					mousePosition = event.getPoint();	//get Mouse Click Position
					for(int i = 0; i < myPits.length; i++){
						// Check if clicked on a pit.
						if(myPits[i].contains(mousePosition)){	
							if(myPits[i].getPitNumber() == 0 
									|| myPits[i].getPitNumber() == 7){
								errorToast.setText("You may not select a Mancala Pit");
								errorToast.setVisible(true);
								
								return;	// Don't allow click on Mancala Pit
							}else if(myPits[i].getPitNumber() 
									> 6 && model.getIsFirstPlayerTurn()){
								errorToast.setText("You may not select opponent's pit");
								errorToast.setVisible(true);
								
								return;	// Don't allow player 1 to select player 2 pit
							}else if(model.getIsFirstPlayerTurn() 
									== false && myPits[i].getPitNumber() < 7){
								errorToast.setText("You may not select opponent's pit");
								errorToast.setVisible(true);
								
								return;	// Don't allow player 2 to click player 1 pit
							}else if(myPits[i].getNumStones() == 0){
								errorToast.setText("You may not select an empty pit");
								errorToast.setVisible(true);
								
								return;	// Can't move if no stones in pit
							}else{
								System.out.println("Calling update board -->"+undoCount);
							
								//Update the move.
								model.updateBoard(myPits[i].getPitNumber()); 
								// If players want to undo, undo the last turn.
								if( (lastTurn==UNDO_TURN) && undoCount>3)
									undoCount=0;
								lastTurn=NORMAL_TURN;
							}
						}
					}
				}// End of the Pressed method.
		});// End of the anonymous class.
		
		this.model = model;
		
		// Create a undo button.
		JButton undoButton = new JButton("Undo Move");
		
		// Add ActionListener into JButton.
		undoButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("In undo action performed-->" + undoCount);
				
				// If players already press on the undo button,
				// then system will show error method. 
				if(lastTurn == UNDO_TURN)
					JOptionPane.showMessageDialog(null,"You wouldn't be allowed to make consecutive undo moves. "
							+ "Note: Please take a normal turn!! ");
				else{
					undoCount++;
					if(undoCount > 3)
						JOptionPane.showMessageDialog(null,"You wouldn't be allowed to make more than 3 undo moves. "
								+ "Pleas take a normal turn (click on a pit)");
					else
						model.undo();		
				}
				// Undo the last turn.
				lastTurn = UNDO_TURN;
			}// End of the actionPerformed method.
		}); // End of the anonymous class.
		
		// Adds the button into JPanel.
		this.add(undoButton);
		
		// Set the size of JPanel.
		this.setSize(1000, 400);
		
		// Create a turnIndicator JTextField.
		turnIndicator = new JTextField();
		
		// Add a JTextField into JPanel.
		this.add(turnIndicator);
		
		// Set the text on JPanel.
		turnIndicator.setText("  Player 1's Turn");
		turnIndicator.setEditable(false);
		
		// Create an errorToast JTextField.
		this.errorToast = new JTextField();
		errorToast.setEditable(false);
		errorToast.setText("Yabba dabba doo");
		
		// Add an errorToast JTextField into JPanel.
		this.add(errorToast);
		this.setLayout(null);
		
		// Sets all bounds for turnIndicator and errorToast.
		turnIndicator.setBounds((int) (DEFAULT_WIDTH / 2 - PIT_WIDTH * 0.4), DEFAULT_HEIGHT - 
				(int)(PIT_WIDTH * 0.4), (int) (PIT_WIDTH * 0.8), (int) (PIT_WIDTH * 0.4));
		errorToast.setBounds(PIT_WIDTH + PIT_WIDTH / 2, DEFAULT_HEIGHT /2 - 
				(int) (PIT_WIDTH * 0.5), (int) (PIT_WIDTH * 1.5), (int) (PIT_WIDTH * 0.4));
		errorToast.setVisible(false);
		
		// Set all bounds for the undo button.
		undoButton.setBounds((int) (DEFAULT_WIDTH / 2 - PIT_WIDTH * 0.4), DEFAULT_HEIGHT /2 - 
				(int) (PIT_WIDTH * 0.5), (int) (PIT_WIDTH * 0.8), (int) (PIT_WIDTH * 0.4));
		
		// Create a PitView reference. 
		myPits = new PitView[DEFAULT_PITS_NUMBER];
		
		// Create a board.
		board = new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// Loop through 14 pits and put 4 initial stones in each pit.
		// Except 2 mancalas, which are in location #0 and #7.
		myPits[0] = new PitView(model, 0, 0, 0, 0, true);// The coordinate need to be changed
		myPits[7] = new PitView(model, 7, 0, DEFAULT_WIDTH - PIT_WIDTH, 0, true);
			
		// Create all pits including Mancalas.
		for(int i = 1; i < 7; i++)
			myPits[i] = new PitView(model, i, 0, PIT_WIDTH * i, DEFAULT_HEIGHT / 2);
		for(int j = 8; j < 14; j++)
			myPits[j] = new PitView(model, j, 0, DEFAULT_WIDTH - 2*PIT_WIDTH - (j-8)*PIT_WIDTH, 0);
	}// End of the method.
	
	/**
	 * Update this pit with the most recent changes in model.
	 * @param e an event containing the model object.
	 */
	@Override
	public void stateChanged(ChangeEvent e){
		//System.out.println("In state changed");
		ArrayList<Hole> data = model.getData();
		//System.out.println("Data size -->"+ data.size());
		int stones = 0;
		
		for(int i = 0; i < DEFAULT_PITS_NUMBER; i++){
		   stones = data.get(i).getStonesCount();
		   myPits[i].setNumStones(stones);
		}
		repaint(); // Repaint all pits, including Mancalas.
		
		// If the game is over, then system checks
		// whether player 1 wins or player 2.
		// There may be some special cases that both players win.
		if(model.isGameOver()){
			String winner;
			if(data.get(Model.FIRST_PLAYER_MANCALA).getStonesCount() 
					> data.get(Model.SECOND_PLAYER_MANCALA).getStonesCount())
				winner = "Player1";
			else if(data.get(Model.FIRST_PLAYER_MANCALA).getStonesCount() 
					< data.get(Model.SECOND_PLAYER_MANCALA).getStonesCount())
				winner = "Player2";
			else
				winner = "Game Drawn";
			
			// Displays the message window for determining the winner of the game.
			JOptionPane.showMessageDialog(null, "Game Over \nWinner : "+winner+""
					+ "\nPlayer 1: "+data.get(Model.FIRST_PLAYER_MANCALA).getStonesCount() 
					+ "\nPlayer 2: "+data.get(Model.SECOND_PLAYER_MANCALA).getStonesCount() );
		}
		
		// Indicating whether player 1's turn or player 2's. 
		if(model.getIsFirstPlayerTurn())
			turnIndicator.setText("  Player 1's Turn");
		else
			turnIndicator.setText("  Player 2's Turn");	
	}// End of the method.

	/**
	 * Paint the Pits on the Panel.
	 * @param pit
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		style.paintBoard(g2, board, myPits);
	}// End of the method.
}// End of the class.