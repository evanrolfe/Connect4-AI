import presets.*;

public class PlayConnect4 {

	public static void main(String[] args) throws InterruptedException{
		Connect4Player human = new HumanPlayer();
		Connect4Player random = new RandomPlayer();
		Connect4Player clever = new CleverPlayer();		
		
		Connect4 game = new Connect4(human, clever);
		game.play();
	}
	
}

class Connect4 {
	private Connect4GameState game;
	private Connect4Player player1, player2;
	
	public Connect4(Connect4Player player1, Connect4Player player2){
		this.game = new GameState();
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void play() throws InterruptedException{
		this.game.startGame();
		int i=0;
		
		do{	
			System.out.println(this.game);			
			Connect4Player currentPlayer = (i%2 == 0) ? this.player1 : this.player2;
			currentPlayer.makeMove(this.game);
			i++;
			//Thread.sleep(1000);
		}while(!this.game.gameOver());
		
		String msg;
		
		switch(this.game.getWinner()){
			case Connect4GameState.EMPTY: msg="Draw."; break;
			case Connect4GameState.RED: msg="Winner: Red!"; break;
			case Connect4GameState.YELLOW: msg="Winner: Yellow!"; break;
			default: msg="No Winner."; break;
		}
		
		System.out.println(this.game+"\nGame Over: "+msg);
	}
	
	public int getOutcome(){
		this.game.startGame();
		int i=0;
		
		do{	
			Connect4Player currentPlayer = (i%2 == 0) ? this.player1 : this.player2;
			currentPlayer.makeMove(this.game);
			i++;
		}while(!this.game.gameOver());
		
		return this.game.getWinner();
	}	
}