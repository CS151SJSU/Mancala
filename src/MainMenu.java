import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * COPYRIGHT (C) 2017 Team 8. All Rights Reserved. 
 * CS 151 Kim: Mancala Project
 * Team members: Rahul Dalal, Nathan Hencky, Hiep Nguyen.
 * Date: 05/06/2017
 */

/*
 * Create a MainMenu extending JDialog.
 */
public class MainMenu extends JDialog{
	private Container pane;
	private StyleStrategy[] styles;
	private StyleStrategy style;
	private final int WIDTH = 400;
	private final int HEIGHT = 200;
	private JPanel panel = new JPanel();
	
	/**
	 * Creates a MainMenu constructor.
	 * @param owner to use the frame.
	 * @param styles to use the style.
	 */
	public MainMenu(Frame owner, StyleStrategy[] styles){
		super(owner, true);
		this.styles = styles;
		style = styles[0];
		pane = getContentPane();
		
		setSize(WIDTH, HEIGHT);
		JPanel strategyPanel = new JPanel();
		JLabel instructions = new JLabel("Choose a board style", SwingConstants.CENTER);
		instructions.setFont(new Font("Serif", Font.PLAIN, 25));
		
		//Set the title for the main menu.
		this.setTitle("Main Menu");
		
		/**Create a style button for choosing style of the board.*/
		JRadioButton[] styleButtons = new JRadioButton[styles.length];
		ButtonGroup radioMutex = new ButtonGroup();
		
		JRadioButton defaultButton = new JRadioButton("Default", true);
		JRadioButton contrastButton = new JRadioButton("High Contrast", false);
		
		/**Add the ActionListener to default button.*/
		defaultButton.addActionListener(new 
				ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						style = styles[0];
					}	
				});// End of the anonymous class.
		
		/**Add the ActionListener to contrast button.*/
		contrastButton.addActionListener(new 
				ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					style = styles[1];
				}
			});// End of the anonymous class.
		
		strategyPanel.add(defaultButton);
		radioMutex.add(defaultButton);
		strategyPanel.add(contrastButton);
		radioMutex.add(contrastButton);
		
		JButton startButton = new JButton("Start Game");
		startButton.setPreferredSize(new Dimension(100,30));
		startButton.addActionListener(new 
				ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						pane.setVisible(false);
						dispose();
					}
				});// End of the anonymous class.
		
		JButton closeButton = new JButton("Quit Game");
		closeButton.setPreferredSize(new Dimension(100, 30));
		closeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		
		panel.setLayout(new FlowLayout());
		panel.add(startButton);
		panel.add(closeButton);
		pane.setLayout(new BorderLayout());
		pane.add(instructions, BorderLayout.NORTH);
		pane.add(strategyPanel, BorderLayout.CENTER);
		pane.add(panel, BorderLayout.SOUTH);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
            		@Override
           		public void windowClosing(WindowEvent e) {          
            			System.exit(0);;  //close 
                } 
       	});
		
	}// End of the constructor.
	
	/**
	 * Gets styles of the board.
	 * @return The style of the board.
	 */
	public StyleStrategy getStyle(){
		return style;
	}// End of the method.
	
	/**
	 * Show the dialog box.
	 * @return The message: "Startup Dialog."
	 */
	public String showDialog(){
		setVisible(true);
		return "Startup Dialog";
	}// End of the method.
}// End of the class.