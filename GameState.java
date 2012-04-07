import java.util.ArrayList;

import presets.*;

public class GameState extends Connect4GameState {
	
	public int[][] grid;
	public int currentTurn;
	public ArrayList<int[]> tmpList = new ArrayList<int[]>();
	public ArrayList<int[][]> lines = new ArrayList<int[][]>();

	/**
	 * Starts the game. Initialises every grid position in the board to EMPTY
	 * and sets the current turn to RED
	 */
	public void startGame(){
		int i,j;
		this.grid = new int[NUM_ROWS][NUM_COLS];
		this.currentTurn = RED;
		
		for(i=0; i<NUM_ROWS; i++){
			for(j=0; j<NUM_COLS; j++){
				this.grid[i][j] = EMPTY;
			}
		}
		
		this.generateLines();
	}
	
	/**
	 * Drops a counter into a slot on the board.  The colour of the counter depends 
	 * on whose turn it is
	 * @param col the column in which to drop the counter, in the range 0-6
	 * @throws ColumnFullException if the column denoted by col is full (i.e. the move cannot be played)
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 */
	public void move(int col) throws ColumnFullException, IllegalColumnException{		
		if(this.isColumnFull(col)){
			throw new ColumnFullException(col);
		}else{
			//Iterate through each row of the column= int col until it is empty
			//then place counter in the first empty position
			for(int i=0; i<NUM_ROWS; i++){
				if(this.getCounterAt(col, i) == EMPTY){
					this.grid[i][col] = this.whoseTurn();
					break;
				}
			}
			
			//Update the current turn to the other player
			this.currentTurn = (this.currentTurn == RED) ? YELLOW : RED;			
		}
	}
	
	/**
	 * Returns whose turn is it
	 * @return the constant RED if it is red's turn, else YELLOW 
	 */
	public int whoseTurn(){
		return currentTurn;
	}
	
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
	public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException{
		if(col >= NUM_COLS || col < 0){
			throw new IllegalColumnException(col);
		}else if(row >= NUM_ROWS || row < 0){
			throw new IllegalRowException(row);
		}else{
			return this.grid[row][col];
		}
	}
	
	/**
	 * Returns whether the board is full and the game has ended in a tie
	 * @return true if the board is full, else false
	 */
	public boolean isBoardFull(){
		for(int i=0; i<NUM_COLS; i++){
			if(!this.isColumnFull(i)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns whether the column denoted by the col parameter is full or not
	 * @param col the column being queried (in the range 0-6)
	 * @return true if the column denoted by col is full of counters, else false
	 * @throws IllegalColumnException if col is not in the range 0-6 (i.e. an invalid column)
	 */
	public boolean isColumnFull(int col) throws IllegalColumnException{
		if(col >= NUM_COLS || col < 0){
			throw new IllegalColumnException(col);
		}else{
			for(int i=0; i<NUM_ROWS; i++){
				if(this.getCounterAt(col, i) == EMPTY){
					return false;
				}
			}
			
			return true;
		}
	}
	
	
	/**
	 * Indicates whether the current game has finished
	 * @return true if there is a winner or the board is full
	 */
	public boolean gameOver(){
		return (this.isBoardFull() || !(this.getWinner() == EMPTY));		
	}
	
	/**
	 * Copies the current Connect4GameState instance into another object
	 * @return the new Connect4GameState instance
	 */
	public Connect4GameState copy(){
		Connect4GameState gs = new GameState();
		GameState copy = (GameState) gs;
		copy.startGame();
		
		//Deep copy of grid array
		int i,j;
		
		for(i=0; i<NUM_ROWS; i++){
			for(j=0; j<NUM_COLS; j++){
				copy.grid[i][j] = this.grid[i][j];
			}
		}
		
		copy.currentTurn = this.currentTurn;
		
		return copy;
	}
	
	public static void main(String[] args){
		GameState g = new GameState();
		g.startGame();
		
		
	}
	
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public String toString(){
		int i,j;
		String out = "";
		
		for(i=NUM_ROWS-1; i>=0; i--){
			for(j=0; j<NUM_COLS; j++){
				String counter = "";
				
				if(j==0)
					counter = "| ";
				
				if(this.grid[i][j] == RED){
					counter += "R";
				}else if(this.grid[i][j] == YELLOW){
					counter += "Y";
				}else{
					counter += " ";
				}
				
				out += counter+" | ";
			}
			
			out += "\n";
		}		

		out += " ";
		for(i=0; i<NUM_COLS; i++){
			out += i+"   ";
		}
		
		return out;
	}
	
	/**
	 * Indicates whether the game has been won, and by whom
	 * @return the constant EMPTY if there is no winner yet, else the constant RED if the red player
	 * has four in a row, or the YELLOW constant if it is yellow that has won
	 */
	public int getWinner(){		
		//Iterate through each line of length 4
		for(int i=0; i<this.lines.size(); i++){
			int c1 = this.getCounterAt(this.lines.get(i)[0][0], this.lines.get(i)[0][1]);
			int c2 = this.getCounterAt(this.lines.get(i)[1][0], this.lines.get(i)[1][1]);
			int c3 = this.getCounterAt(this.lines.get(i)[2][0], this.lines.get(i)[2][1]);
			int c4 = this.getCounterAt(this.lines.get(i)[3][0], this.lines.get(i)[3][1]);
			
			if(c1 == c2 && c2 == c3 && c3 == c4 && !(c1 == EMPTY))
				return c1;
		}

		return EMPTY;
		//Iterate through queue checking if they are 4 in a row
	}

	private void generateLines(){	
		for(int a=NUM_ROWS-1; a>=0; a--){
			for(int b=0; b<NUM_COLS; b++){		
				int[] p = {b,a};	
				
				//Iterate through each direction for a point P(0,0)
				for(int f=0; f<8; f++){
					//Load the current 4-path into game.tmpList
					this.getIntersection(p, f);
					
					//Load the tmpList into the queue if there are four counters
					//and check if they are all the same
					int[][] path = new int[4][2];
					
					if(this.tmpList.size() == 4){
						for(int g=0; g<4; g++){
							//Convert tmpList to an array int[4][2]
							path[g][0] = this.tmpList.get(g)[0];
							path[g][1] = this.tmpList.get(g)[1];					
						}
						
						//TODO: only include unique paths (must permute coordinates)
						this.lines.add(path);
					}
		
				}
			}
		}
		
		
	}
	
	private void getIntersection(int[] point, int dir, int i){
		//clear the tmpList at the start of recursion
		if(i == 0 && this.tmpList.size() > 0){
			this.tmpList.clear();
		}
		
		//if the point is on the grid
		if(point[0] < NUM_COLS && point[0] >= 0 && point[1] < NUM_ROWS && point[1] >= 0 && this.tmpList.size() < 4){
			this.tmpList.add(point);
			//System.out.println("("+this.tmpList.get(i)[0]+","+this.tmpList.get(i)[1]+"  "+i);
			int[] neighbor = this.getNeighborInDir(point, dir);			
			this.getIntersection(neighbor, dir, i+1);
		}else{
			return;
		}
	}
	
	private void getIntersection(int[] point, int dir){
		this.getIntersection(point, dir, 0);
	}
	
	private int[] getNeighborInDir(int[] p, int dir){
		//int[][] out = {{p[0],p[1]+1},{p[0]+1,p[1]+1},{p[0]+1,p[1]},{p[0]+1,p[1]-1},{p[0],p[1]-1},{p[0]-1,p[1]-1},{p[0]-1,p[1]},{p[0]-1,p[1]+1}};
		
		int[] out = new int[2];
		
		switch(dir){
			case 0: out[0]=p[0]; out[1]=p[1]+1; break;
			case 1: out[0]=p[0]+1; out[1]=p[1]+1; break;
			case 2: out[0]=p[0]+1; out[1]=p[1]; break;
			case 3: out[0]=p[0]+1; out[1]=p[1]-1; break;
			case 4: out[0]=p[0]; out[1]=p[1]-1; break;
			case 5: out[0]=p[0]-1; out[1]=p[1]-1; break;
			case 6: out[0]=p[0]-1; out[1]=p[1]; break;
			case 7: out[0]=p[0]-1; out[1]=p[1]+1; break;
			
		}
		
		return out;
	}
}