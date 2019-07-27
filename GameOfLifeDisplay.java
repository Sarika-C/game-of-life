import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.Timer;

/*
 * Displays generations of John Conway's Game of Life.
 * Allows a user of the program to step through one generation
 * at a time or to run the generations based on a timer.
 */
public class GameOfLifeDisplay extends JFrame {

	private JPanel contentPane;
	private JLabel txtGeneration = new JLabel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeDisplay frame = new GameOfLifeDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Adds a button panel to the frame and
	 * initializes the usage of each button.
	 */
	public GameOfLifeDisplay() {
		Cell[][] glider = {{new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(1), new Cell(1), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)}};

		GameOfLife g = new GameOfLife(glider); // call an appropriate constructor
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
		 * creates a Timer and defines what will occur when
		 * it is run when the user clicks the "start" button
		 */
		Timer timer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// what should happen each time the Timer is fired off
				g.nextGen();
				txtGeneration.setText("Generation " + g.getGenNumber());
				repaint();
			}
			
		});
		
		/*
		 * creates the button panel
		 */
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		/*
		 * creates a button that allows the game to run on 
		 * a timer. The label toggles between "Start" and "Stop"
		 */
		JButton startStopButton = new JButton("Start");
		buttonPanel.add(startStopButton);
		startStopButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(startStopButton.getText().equals("Start")){
					startStopButton.setText("Stop");
					// start the generations
					timer.start();
				}
				else{
					startStopButton.setText("Start");
					// stop the generations
					timer.stop();
				}
				
			}
			
		});
		
		/*
		 * adds a button which allows the user to step through
		 * the game one generation at a time
		 */
		JButton nextGenButton = new JButton("Next Generation");
		buttonPanel.add(nextGenButton);
		nextGenButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// generate and display the next generation
				g.nextGen();
				txtGeneration.setText("Generation " + g.getGenNumber());
				repaint();
			}
			
		});

		/*
		 * displays the generation number
		 */
		txtGeneration.setText("Generation 0");
		buttonPanel.add(txtGeneration);
		
		JPanel panel = new JPanel();
		buttonPanel.add(panel);

		/*
		 * adds the panel which displays the Game of Life
		 * board. See the BoardPanel class for details.
		 */
		JPanel boardPanel = new BoardPanel(g);
		contentPane.add(boardPanel, BorderLayout.CENTER);
	}

}
