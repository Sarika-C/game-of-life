/*
 * This class is the engine for a simulation of John Conway's Game of Life
 * It stores the game board as a Board object and the generation number
 * the game is on as an integer
 * A GameOfLife object can be constructed by either entering the Board's 
 * dimensions as integers, entering the Board's side length as an integer 
 * if it as a square game board, or entering the Board's initial setup as a
 * two-dimensional Cell array
 * When the Board's dimensions or side length are entered, all the Cells are
 * initially set to dead
 * In all three cases, the generation number is initially set to 0
 * This class contains methods to: check if a Cell at a given location is alive; 
 * calculate how many living neighbors a Cell has; check if a Cell will be alive
 * in the next generation; set the state of a Cell; kill or revive a Cell; swap
 * a Cell's state from alive to dead or vice versa; calculate the total number 
 * of living Cells and dead Cells; clear the board by killing all the Cells; advance 
 * the game to the next generation; and find the Board's dimensions and the game's 
 * current generation number
 */
public class GameOfLife {
	
	// instance data
	private Board board;
	private int generationNumber;

	/*
	 * Constructs a GameOfLife object given integer Board dimensions and sets the
	 * GamePieces at all locations to dead Cells
	 */
	public GameOfLife(int width, int height) {
		this.board = new Board(width, height);
		
		for(int i = 0; i <= width; i++) {
			for(int j = 0; j <= height; j++) {
				board.setPiece(i, j, new Cell(0));
			}
		}
		
		this.generationNumber = 0;
	}
	
	/*
	 * Constructs a GameOfLife object given an integer side length for a square Board
	 * and sets the GamePieces at all locations to dead Cells
	 */
	public GameOfLife(int width) {
		this(width, width);
	}
	
	/*
	 * Constructs a GameOfLife object given a two-dimensional array of Cells
	 */
	public GameOfLife(Cell[][] initialState) {
		this.board = new Board(initialState);
		this.generationNumber = 0;
	}
	
	// Checks to see if the Cell at integer coordinates (x, y) is alive
	public boolean isAlive(int x, int y) {
		return (board.getPiece(x, y) != null && board.getPiece(x, y).getType() == 1);
	}
	
	// Calculates how many of a Cell’s neighbors are alive
	public int countLivingNeighbors(int x, int y) {
		int liveCellCount = 0;
		
		for(int i = x-1; i <= x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				if(!(i == x && j == y) && this.board.inBounds(i, j) && isAlive(i, j)) {
					liveCellCount++;
				}
			}
		}
		
		return liveCellCount;
	}
	
	/*
	 * Checks to see if the Cell at the given integer coordinates (x,y) has the 
	 * correct number of neighbors to be alive in the next generation according to 
	 * the rules of John Conway's Game of Life
	 */
	public boolean willBeAlive(int x, int y) {
		return (countLivingNeighbors(x, y) == 3 || isAlive(x, y) && countLivingNeighbors(x, y) == 2);
	}
	
	/*
	 * Updates an individual Cell's state for the following generation
	 */
	public void setCell(int x, int y, int state) {
		board.getPiece(x, y).setType(state);
	}

	/*
	 * Sets a Cell's state to "alive" at a certain location on the Board given integer coordinates (x, y)
	 */
	public void revive(int x, int y) {
		setCell(x, y, 1);
	}
	
	/*
	 * Sets a Cell's state to "dead" at a certain location on the Board given integer coordinates (x, y)
	 */
	public void kill(int x, int y) {
		setCell(x, y, 0);
	}
	
	/*
	 * Swaps a Cell's state ("dead" to "alive" or "alive" to "dead") at a certain location on the
	 * Board given integer coordinates (x, y)
	 */
	
		public void swapStatus(int x, int y) {
			if(isAlive(x, y)) {
				board.getPiece(x, y).setType(0);
			}
			else {
				board.getPiece(x, y).setType(1);
			}
		}
	
	/*
	 * Counts how many total Cells are alive
	 */
	public int aliveCount() {
		int aliveCellCount = 0;
		
		for(int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight(); y++) {
				if(this.isAlive(x, y)) {
					aliveCellCount++;
				}
			}
		}

		 return aliveCellCount;
	}
	
	/*
	 * Counts how many total Cells are dead
	 */
	public int deadCount() {
		int deadCellCount = (this.getWidth() * this.getHeight()) - this.aliveCount();		
		return deadCellCount;
	}
	
	/*
	 * Clears the Board by setting all the Cells to "dead"
	 */
	public void clear() {
		for(int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight(); y++) {
				this.setCell(x, y, 0);
			}
		}
	}
	
	/*
	 * Advances from one generation to the next
	 */
	public void nextGen() {
		Board newBoard = new Board(this.getWidth(), this.getHeight());
		
		for(int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight(); y++) {
				if(this.willBeAlive(x, y)) {
					newBoard.setPiece(x, y, new Cell(1));
				}
				else {
					newBoard.setPiece(x, y, new Cell(0));
				}
			}
		}

		this.board = newBoard;
		this.generationNumber++;
	}
	
	/*
	 * Returns the generation number the game is currently on as an integer
	 */
	public int getGenNumber() {
		return this.generationNumber;
	}
	
	/*
	 * Returns the width of the Board as an integer
	 */
	public int getWidth() {
		return board.getWidth();
	}
	
	/*
	 * Returns the height of the Board as an integer
	 */
	public int getHeight() {
		return board.getHeight();
	}
	
	public static void main(String[] args) {
		Cell[][] test = {{new Cell(0), new Cell(1), new Cell(0), new Cell(1), new Cell(0), new Cell(1)},
				{new Cell(1), new Cell(0), new Cell(1), new Cell(0), new Cell(1), new Cell(0)}};
		
		GameOfLife g = new GameOfLife(test);

		for(int i = 0; i < g.getWidth(); i++) {
			for(int j = 0; j < g.getHeight(); j++) {
				System.out.println("Number of living neighbors: " + g.countLivingNeighbors(i, j));
				System.out.println("Will be alive in the next generation: " + g.willBeAlive(i, j));
			}
		}
	}

}