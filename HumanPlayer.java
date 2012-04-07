import presets.*;

public class HumanPlayer extends Connect4Player{
	private EasyReader keyboard;
	
	public HumanPlayer(){
		this.keyboard = new EasyReader();		
	}
	
	public void makeMove(Connect4GameState gameState){
		try{
			String c = this.keyboard.readString("Please select a column from 0-6");
			
			int col = Integer.parseInt(c);

			gameState.move(col);
		}catch(ColumnFullException e){
			System.out.println("That column is full!");
			this.makeMove(gameState);
		}catch(IllegalColumnException e){
			System.out.println("That is not a valid column number (not in range)!");
			this.makeMove(gameState);			
		}catch(NumberFormatException e){
			System.out.println("That is not a valid column number (not an integer)!");
			this.makeMove(gameState);			
		}
	}
}