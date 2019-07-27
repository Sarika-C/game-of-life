/*
 * This class extends the GamePiece class
 * It stores the status of a Cell as an integer constant, either 0 or 1, where
 * 0 represents a dead state while 1 represents an alive state
 * A Cell object can be constructed by entering its status as an integer
 * This class contains methods to find and set the Cell's status
 */
public class Cell extends GamePiece{
	
	// instance data
	public static final int DEAD = 0;
	public static final int ALIVE = 1;

	/*
	 * Constructs a Cell given a status (either dead or alive) as an integer (either 0 or 1)
	 * by delegating to the super constructor from the GamePiece class
	 */
	public Cell(int status) {
		super(status);
	}
	
	/*
	 * Returns the status of this Cell as an integer by delegating to 
	 * the getType() method from the GamePiece class (getter)
	 */
	public int getStatus() {
		return this.getType();
	}
	
	/*
	 * Changes the status of this Cell to the given integer parameter value by delegating
	 * to the setType() method from the GamePiece class (setter)
	 */
	public void setStatus(int status) {
		this.setType(status);
	}
	
	public static void main(String[] args) {
		Cell aliveCell = new Cell(1);
		System.out.println(aliveCell);
		System.out.println(aliveCell.getStatus());
		aliveCell.setStatus(0);
		System.out.println(aliveCell.getStatus());
		
		Cell deadCell = new Cell(0);
		System.out.println(deadCell);
		System.out.println(deadCell.getStatus());
		deadCell.setStatus(1);
		System.out.println(deadCell.getStatus());
	}

}
