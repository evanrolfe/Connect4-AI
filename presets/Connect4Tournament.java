package presets;

import java.util.List;
import java.util.Map;
import presets.*;

/**
 * An abstract class representing Connect4 tournament, to be extended by your own implementation
 *
 */
		
public abstract class Connect4Tournament {

	/**
	 * The method responsible for running the tournament and collating the results
	 * @param competitors an object mapping competitor names (as strings) to Connect4Player objects
	 */
	public abstract void runTournament(Map<String, Connect4Player> competitors);
	
	/**
	 * Returns a list of Connect4Ranking objects.  Each object corresponds to a player and the number
	 * of points they have accrued in the tournament.  The list is in points-order (i.e. the winner(s) of 
	 * the tournament are at the head of the list) 
	 * @return a correctly ordered list of Connect4Ranking objects 
	 */
	public abstract List<Connect4Ranking> getRankings();
	
	/**
	 * Prints a league table to the console of each competitor name and the points they have accrued. 
	 * The printout is ranked with players appearing in points-order. 
	 */
	public abstract void printTable();
}