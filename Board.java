import java.util.Arrays;

/*
 * This class stores integers width and height as the Board object's dimensions and
 * a two-dimensional array of GamePiece objects, called board, as the Board's setup
 * A Board object can be constructed either by entering the dimensions as integers,
 * by entering the side length as an integer if the Board is a square, or by
 * entering the Board's initial setup as a two-dimensional GamePiece array
 * The toString method returns a String that lists the Board's dimensions in a sentence
 * This class contains methods to: clear a Board of all GamePieces; place a GamePiece on or
 * remove it from a given location on the Board; get a GamePiece from a given Board location;
 * check if the Board contains a GamePiece at a given location; check if a given location
 * is within the scope of the Board; and find the dimensions of the Board
 */
public class Board {
	
	// instance data
	private int width;
	private int height;
	private GamePiece[][] board;
	
	/*
	 * Constructs a rectangular Board given integer dimensions
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new GamePiece[height][width];
	}
	
	/* 
	 * Constructs a square Board given an integer side length
	 */
	public Board(int sideLength) {
		this(sideLength, sideLength);
		
	}
	
	/*
	 *  Constructs a Board given a two-dimensional array of GamePiece objects
	 */
	public Board(GamePiece[][] board) {
		this.board = board;
		this.width = board[0].length;
		this.height = board.length;
	}
	
	/*
	 *  Returns a String with the Board's dimensions
	 */
	public String toString() {
		return "This board has width " + this.getWidth() + " and height " + this.getHeight();
 	}

	/*
	 * Clears the Board of all GamePieces by setting all the locations to null
	 */
	public void clear() {
		for(int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight(); y++) {
				board[y][x] = null;
			}
		}	
	}
	
	/*
	 * Places the given GamePiece object, "piece," on the Board location given by (x, y)
	 * and returns the GamePiece that was originally at (x, y)
	 */
	public GamePiece setPiece(int x, int y, GamePiece piece) {
		GamePiece initialPiece = null;
		
		if(inBounds(x, y)) {
			initialPiece = board[y][x];
			board[y][x] = piece;
		}
		
		return initialPiece;
	}
	
	/*
	 *  Removes the GamePiece at (x, y) from that Board location and returns that GamePiece
	 */
	public GamePiece removePiece(int x, int y) {
		GamePiece initialPiece = null;
		
		if(inBounds(x, y)) {
			initialPiece = board[y][x];
			board[y][x] = null;
		}
		
		return initialPiece;
	}
	
	/*
	 *  Returns the GamePiece at location (x, y) on the Board
	 */
	public GamePiece getPiece(int x, int y) {
		if(inBounds(x, y)) {
			return board[y][x];
		}
		else {
			return null;
		}
	}
	
	/*
	 *  Checks if there is a GamePiece object at location (x, y) on the Board
	 */
	public boolean hasPiece(int x, int y) {
		if(inBounds(x, y)) {
		return (board[y][x] != null);
		}

		return false;
	}
	
	/*
	 *  Checks if a location (x, y) is inside the Board
	 */
	public boolean inBounds(int x, int y) {
		return (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight());
	}

	/*
	 * Returns the width of the Board as an integer (getter)
	 */
	public int getWidth() {
		return width;
	}

	/*
	 *  Returns the height of the board as an integer (getter)
	 */
	public int getHeight() {
		return height;
	}
	
	public static void main(String[] args) {
		Board test1 = new Board(4, 5);
		System.out.println(test1);
		System.out.println("The previous piece at (0, 0): " + test1.setPiece(0, 0, new Cell(1)));
		System.out.println("The piece at (0, 0): " + test1.getPiece(0, 0));
		System.out.println("The piece at (1, 1): " + test1.getPiece(1, 1));
		System.out.println("The piece at (5, 7): " + test1.getPiece(5, 7));
		System.out.println("The previous piece at (3, 4): " + test1.setPiece(3, 4, new Cell(0)));
		System.out.println("The piece at (3, 4): " + test1.getPiece(3, 4));
		System.out.println("There is a piece at (0, 0): " + test1.hasPiece(0, 0));
		System.out.println("There is a piece at (1, 1): " + test1.hasPiece(1, 1));
		System.out.println("There is a piece at (5, 7): " + test1.hasPiece(5, 7));
		System.out.println("There is a piece at (3, 4): " + test1.hasPiece(3, 4));
		test1.setPiece(5, 7, new Cell(1));
		System.out.println("The piece at (5, 7): " + test1.getPiece(5, 7));
		System.out.println("The previous piece at (0, 0): " + test1.removePiece(0, 0));
		System.out.println("The piece at (0, 0): " + test1.getPiece(0, 0));
		System.out.println("The previous piece at (1, 1): " + test1.removePiece(1, 1));
		System.out.println("The piece at (1, 1): " + test1.getPiece(1, 1));
		System.out.println("The previous piece at (5, 7): " + test1.removePiece(5, 7));
		System.out.println("The piece at (5, 7): " + test1.getPiece(5, 7));
		System.out.println("The previous piece at (3, 4): " + test1.removePiece(3, 4));
		System.out.println("The piece at (3, 4): " + test1.getPiece(3, 4));
		test1.setPiece(0, 0, new Cell(0));
		System.out.println("The piece at (0, 0): " + test1.getPiece(0, 0));
		test1.clear();
		System.out.println("The piece at (0, 0): " + test1.getPiece(0, 0));
		
		Board test2 = new Board(4);
		System.out.println(test2);
		
		Cell[][] test = {{new Cell(0), new Cell(1), new Cell(0), new Cell(1), new Cell(0), new Cell(1)},
								{new Cell(1), new Cell(0), new Cell(1), new Cell(0), new Cell(1), new Cell(0)}};
		
		Board test3 = new Board(test);
		System.out.println(test3);
		for(Cell[] row: test) {
			System.out.println(Arrays.toString(row));
		}

	}
}
