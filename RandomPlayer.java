import presets.ColumnFullException;
import presets.Connect4GameState;
import presets.Connect4Player;
import java.util.Random;

public class RandomPlayer extends Connect4Player{
	public RandomPlayer(){
		
	}
	
	public void makeMove(Connect4GameState gameState){
		int random = this.selectColumn(gameState);
		
		try{
			gameState.move(random);
		}catch(ColumnFullException e){
			System.out.println(e);
		}		
	}
	
	private int selectColumn(Connect4GameState gameState){
		Random generator = new Random();
		int random = generator.nextInt(Connect4GameState.NUM_COLS-1);
		
		if(gameState.isColumnFull(random)){
			return this.selectColumn(gameState);
		}else{
			return random;
		}
	}
}
