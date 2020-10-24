//hello
public class Board {
    int value;
    int enemyValue;
    char gameBoard[][];
    String move;

    //board constructor
    public Board(){
        value = 1;
        enemyValue = 1;
        gameBoard = new char[8][8];
    }

    public void cloneBoard(Board b){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this.gameBoard[i][j] = b.gameBoard[i][j];
            }
        }
    }
    
    //evaluation function
    public int evaluate() {
        return 0;
    }

    //board move function
    public Board makeMove(int start, int end) {
        Board c = new Board();
        c.cloneBoard(this);
        int startX = (start-1) / 8;
        int startY = (start-1) % 8;
        int endX = (end-1) / 8;
        int endY = (end-1) / 8;
        int deltX = endX - startX;
        int deltY = endY - startY;
        char mover;
        char removed;
        if(c.gameBoard[startY][startX] == 'W'){
            mover = 'W';
            removed = 'B';
        }
        else{
            mover = 'B';
            removed = 'W';
        }
        if(deltX > 0){
            
        }
        else{
            
        }
        c.move = ("Move piece at space " + start + " to space " + end + ".");
        return c;
    }

    //fill in the board 
    public void initializeBoard() {
        int oddOrEven = 0;
        for(int i = 0; i<gameBoard.length;i++){
            for(int j = 0; j<gameBoard[i].length;j++){
                if(oddOrEven % 2 == 0) {
                    gameBoard[i][j] = 'B';
                }else {
                    gameBoard[i][j] = 'W';
                }
                oddOrEven++;
            }
            oddOrEven++;
        }
    }

    //print function for the game board 
    public void printBoard() {
        for(int i = 0; i<gameBoard.length;i++){
            for(int j = 0; j<gameBoard[i].length;j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}
