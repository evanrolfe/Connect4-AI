package presets;

public class ColumnFullException extends Exception {

	/**
	 * Constructs a new ColumnFullException
	 * @param col the number of the column (in the range 0-6) that is full
	 */
	
	public ColumnFullException(int col) {
		super("Column "+col+" is full");
	}
	
}