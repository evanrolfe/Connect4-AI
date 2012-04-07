package presets;

/**
 * 
 * A class that represents the current state of a Connect4 game,
 * including the configuration of the board and whose turn it is.
 * The class is abstract and is to be extended by your own 
 * implementation of all the methods.
 */
public abstract class Connect4GameState {

	/** The number of columns the board has */
	public static int NUM_COLS = 7;
	
	/** The number of rows the board has */
	public static int NUM_ROWS = 6;
	
	/** The number of counters of a given colour should be in a row to win the game */
	public static int NUM_IN_A_ROW_TO_WIN = 4;
	
	/** Denotes a grid position in the board that is unfilled */ 
	public static final int EMPTY = -1;
	
	/** Denotes a red counter / the red player */
	public static final int RED = 0;
	
	/** Denotes a yellow counter / the yellow player */
	public static final int YELLOW = 1;
		
	/**
	 * Starts the game. Initialises every grid position in the board to EMPTY
	 * and sets the current turn to RED
	 */
	public abstract void startGame();
	
	/**
	 * Drops a counter into a slot on the board.  The colour of the counter depends 
	 * on whose turn it is
	 * @param col the column in which to drop the counter, in the range 0-6
	 * @throws ColumnFullException if the column denoted by col is full (i.e. the move cannot be played)
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 */
	public abstract void move(int col) throws ColumnFullException, IllegalColumnException;
	
	/**
	 * Returns whose turn is it
	 * @return the constant RED if it is red's turn, else YELLOW 
	 */
	public abstract int whoseTurn();
	
	/**
	 * Returns a constant denoting the status of the slot at the position denoted by the 
	 * col and row parameters
	 * @param col the column of the position being queried (in the range 0-6)
	 * @param row the row of the position being queried (in the range 0-5)
	 * @return the EMPTY constant if the slot is empty, the RED constant if the slot is filled by a red counter,
	 * the YELLOW constant if is yellow
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 * @throws IllegalRowException if col is not in the range 0-5 (i.e. an invalid row)
	 */
	public abstract int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException;
	
	/**
	 * Returns whether the board is full and the game has ended in a tie
	 * @return true if the board is full, else false
	 */
	public abstract boolean isBoardFull();
	
	/**
	 * Returns whether the column denoted by the col parameter is full or not
	 * @param col the column being queried (in the range 0-6)
	 * @return true if the column denoted by col is full of counters, else false
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 */
	public abstract boolean isColumnFull(int col) throws IllegalColumnException;
	
	/**
	 * Indicates whether the game has been won, and by whom
	 * @return the constant EMPTY if there is no winner yet, else the constant RED if the red player
	 * has four in a row, or the YELLOW constant if it is yellow that has won
	 */
	public abstract int getWinner();
	
	/**
	 * Indicates whether the current game has finished
	 * @return true if there is a winner or the board is full
	 */
	public abstract boolean gameOver();
	
	/**
	 * Copies the current Connect4GameState instance into another object
	 * @return the new Connect4GameState instance
	 */
	public abstract Connect4GameState copy();
}