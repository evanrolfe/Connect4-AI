package presets;

/**
 * An abstract class representing a ranking in the league table
 * of the Connect4 tournament
 *
 */
public abstract class Connect4Ranking {

	/**
	 * @return the name of the player to whom this ranking applies
	 */
	public abstract String getName();
	
	
	/**
	 * @return the number of points accrued by the player
	 */
	public abstract int getPoints();	
}