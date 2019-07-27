import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*
 * A class that extends the JPanel class, adding the functionality
 * of painting the current generation of a Game of Life.
 */
public class BoardPanel extends JPanel{
	private GameOfLife game;
	
	public BoardPanel(GameOfLife g){
		game = g;
	}

	
	/**
	 * Paints the current state of the Game of Life board onto
	 * this panel. This method is invoked for you each time you
	 * call repaint() on either this object or on the JFrame upon
	 * which this panel is placed.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.LIGHT_GRAY);

		// draws the current state of the board
		int cellWidth = getWidth() / game.getWidth();
		int cellHeight = getHeight() / game.getHeight();
		
		int finalBoardWidth = cellWidth * game.getWidth();
		int finalBoardHeight = cellHeight * game.getHeight();

		for(int x = 0; x < finalBoardWidth; x += cellWidth) {
			for(int y = 0; y < finalBoardHeight; y += cellHeight ) {
				g2.drawRect(x, y, cellWidth, cellHeight);
			}
		}
		
		// fills every rectangle occupied by a living Cell
		for(int x = 0; x < finalBoardWidth; x += cellWidth) {
			for(int y = 0; y < finalBoardHeight; y += cellHeight) {
					if(game.isAlive(x / cellWidth, y / cellHeight)) {
						g2.setColor(Color.BLACK);
						g2.fillRect(x, y, cellWidth, cellHeight);
						g2.setColor(Color.LIGHT_GRAY);
					}
			}
		}
	}
}
