//hello
public class Board {
	int value;
	int enemyValue;
	char gameBoard[][];
	
	//board constructor
	public Board(){
		value = 1;
		enemyValue = 1;
		gameBoard = new char[8][8];
		
		//intializeBoard();
		intializeBoard();
	}
	
	//evaluation function
	public int evaluate() {
		return 0;
	}
	
	//board move function
	public Board makeMove(int start, int end) {
		Board c = new Board();
		return c;
	}
	
	//fill in the board 
	public void intializeBoard() {
		int oddOrEven = 0;
		for(int i = 0; i<gameBoard.length;i++)
			for(int j = 0; j<gameBoard[i].length;j++)
				if(oddOrEven % 2 == 0) {
					gameBoard[i][j] = 'B';
				}else {
					gameBoard[i][j] = 'W';
				}
	}
	
	//print function for the game board 
	public void printBoard() {
		for(int i = 0; i<gameBoard.length;i++)
			for(int j = 0; j<gameBoard[i].length;j++)
				System.out.println(gameBoard[i][j]);
	}
}
