import presets.ColumnFullException;
import presets.Connect4GameState;
import presets.Connect4Player;
import presets.IllegalColumnException;

import java.util.*;

public class CleverPlayer extends Connect4Player{
	public CleverPlayer(){
		
	}
	
	public void makeMove(Connect4GameState gameState){
		try {		
			gameState.move(this.min(gameState, 2, 0));
		} catch (IllegalColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ColumnFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int evalGameState(Connect4GameState gameState){
		int score;
		
		//Determine score: 1 - win, 0 - draw, -1 loose
		if(gameState.getWinner() == gameState.RED){
			score = 1;
		}else if(gameState.getWinner() == gameState.EMPTY){
			score = 0;
		}else{
			score = -1;
		}
		
		return score;
	}
	
	public int min(Connect4GameState originalGameState, int maxDepth, int depth){
		ArrayList<Column> columns = new ArrayList<Column>();
		String player = (originalGameState.whoseTurn()==originalGameState.RED) ? "Red" : "Yellow";		
		
		String tabs = "";
		for(int i=0; i<depth; i++){ tabs+="\t"; }
		
		for(int k=0; k<originalGameState.NUM_COLS; k++){			
			int value;
			
			//Make sure the game has not already been won
			if(originalGameState.gameOver()){
				value = evalGameState(originalGameState);
				columns.add(new Column(k, value));
			}else{
				//2. Copy gamestate
				Connect4GameState gameCopy = originalGameState.copy();
				
				//3. Move on this column on game board
				try{
					gameCopy.move(k);
				}catch(ColumnFullException e){
					continue;
				}catch(IllegalColumnException e){					
					continue;
				}
				
				value = (depth == maxDepth) ? evalGameState(gameCopy) : this.min(gameCopy, maxDepth, depth+1);
											
				columns.add(new Column(k, value));
			}
			
//System.out.println(tabs+player+" moves "+k+" - score: "+value);
		}

		Collections.sort(columns);
		
		if(originalGameState.whoseTurn()==originalGameState.YELLOW){ Collections.reverse(columns); }
			
		
		int output = 0;

		
		if(depth == maxDepth){
			//Compute sum of scores
			for(Column col : columns){
				output += col.score;
			}				
		}else{
			output = columns.get(0).number;		
		}
		
//System.out.println(tabs+player+" - outputting: "+output);
		return output;
	}
	
	public static void main(String[] args) throws IllegalColumnException, ColumnFullException{
		Connect4GameState game = new GameState();
		CleverPlayer clever = new CleverPlayer();
		game.startGame();
		
		game.move(0);
		game.move(1);
		game.move(1);
		game.move(2);
		game.move(2);
		game.move(0);
		game.move(2);
		game.move(3);
		game.move(3);
		game.move(3);
		
		//game.move(clever.miniMax(game));
		clever.makeMove(game);
		//clever.min(game, 2, 0);
		System.out.println(game);
	}	
}

class Column implements Comparable<Column>{
	public int number, score;
	
	public Column(int number, int score){
		this.number = number;
		this.score = score;
	}
	
	public int compareTo(Column c) {
		if(this.score == c.score){
			return 0;
		}else if(this.score > c.score){
			return -1;
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return this.number+" with score: "+this.score;
	}
}