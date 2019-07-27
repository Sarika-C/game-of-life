/*
 * This class stores the type of a GamePiece object as an integer
 * A GamePiece object can be constructed by entering its type as an integer
 * The toString method returns a String that lists the GamePiece's type in a 
 * sentence
 * This class contains methods to find and set the GamePiece's type
 */
public abstract class GamePiece {
	// instance data
	private int type;
	
	/*
	 * Constructs a GamePiece object given a type as an integer
	 */
	public GamePiece(int type) {
		this.type = type;
	}
	
	/*
	 * Returns a String representation of this GamePiece object
	 */
	public String toString() {
		return "This GamePiece is of type " + this.getType();
	}

	/*
	 * Returns the type of this GamePiece object as an integer (getter)
	 */
	public int getType() {
		return type;
	}

	/*
	 * Changes the type of this GamePiece object to the given integer parameter value (setter)
	 */
	public void setType(int type) {
		this.type = type;
	}

}
