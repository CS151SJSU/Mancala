import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainMenu extends JDialog
{
	private Container pane;
	private StyleStrategy[] styles;
	private StyleStrategy style;
	private final int WIDTH = 400;
	private final int HEIGHT = 200;
	
	
	public MainMenu(Frame owner, StyleStrategy[] styles)
	{
		super(owner, true);
		this.styles = styles;
		style = styles[0];
		pane = getContentPane();
		setSize(WIDTH, HEIGHT);
		JPanel strategyPanel = new JPanel();
		JLabel instructions = new JLabel("Choose a board style");
		
		JRadioButton[] styleButtons = new JRadioButton[styles.length];
		ButtonGroup radioMutex = new ButtonGroup();
		
		JRadioButton defaultButton = new JRadioButton("Default", true);
		JRadioButton contrastButton = new JRadioButton("High Contrast", false);
		
		defaultButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						style = styles[0];
					}
					
				});
		contrastButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					style = styles[1];
					
				}
			
			});
		strategyPanel.add(defaultButton);
		radioMutex.add(defaultButton);
		strategyPanel.add(contrastButton);
		radioMutex.add(contrastButton);
		
		JButton startButton = new JButton("Start Game");
		startButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						pane.setVisible(false);
						dispose();
					}
					
				});
		pane.setLayout(new BorderLayout());
		pane.add(instructions, BorderLayout.NORTH);
		pane.add(strategyPanel, BorderLayout.CENTER);
		pane.add(startButton, BorderLayout.SOUTH);
		setResizable(false);
	}
	
	public StyleStrategy getStyle()
	{
		return style;
	}
	
	public String showDialog()
	{
		setVisible(true);
		return "Startup Dialog";
	}
}
