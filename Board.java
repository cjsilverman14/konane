//hello
public class Board {
	int value;
	int enemyValue;
	char gameBoard[][];
	
	public Board(){
		value = 1;
		enemyValue = 1;
		gameBoard = new char[8][8];
	}
	
	public int evaluate() {
		return 0;
	}
	
	public Board makeMove(int start, int end) {
		Board c = new Board();
		return c;
	}
	
	//print function for the game board 
	public void printBoard() {
		for(int i = 0; i<gameBoard.length;i++)
			for(int j = 0; j<gameBoard[i].length;j++)
				System.out.println(gameBoard[i][j] + " ");
	}
}
