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
    public int evaluate(char color, char enemy) {
        Controller.staticEval++;
        int moves = 0;
        enemyValue = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(this.gameBoard[i][j] == color){
                    if(j > 1){
                        if(this.gameBoard[i][j-1] == enemy && this.gameBoard[i][j-2] == ' '){
                            moves++;
                            if(j == 7){
                                moves++;
                            }
                        }
                    }
                    if(j < 6){
                        if(this.gameBoard[i][j+1] == enemy && this.gameBoard[i][j+2] == ' '){
                            moves++;
                            if(j == 0){
                                moves++;
                            }
                        }
                    }
                    if(i > 1){
                        if(this.gameBoard[i-1][j] == enemy && this.gameBoard[i-2][j] == ' '){
                            moves++;
                            if(i == 7){
                                moves++;
                            }
                        }
                    }
                    if(i < 6){
                        if(this.gameBoard[i+1][j] == enemy && this.gameBoard[i+2][j] == ' '){
                            moves++;
                            if(i == 0){
                                moves++;
                            }
                        }
                    }
                }
                if(this.gameBoard[i][j] == enemy){
                    if(j > 1){
                        if(this.gameBoard[i][j-1] == color && this.gameBoard[i][j-2] == ' '){
                            enemyValue++;
                        }
                    }
                    if(j < 6){
                        if(this.gameBoard[i][j+1] == color && this.gameBoard[i][j+2] == ' '){
                            enemyValue++;
                        }
                    }
                    if(i > 1){
                        if(this.gameBoard[i-1][j] == color && this.gameBoard[i-2][j] == ' '){
                            enemyValue++;
                        }
                    }
                    if(i < 6){
                        if(this.gameBoard[i+1][j] == color && this.gameBoard[i+2][j] == ' '){
                            enemyValue++;
                        }
                    }
                }
            }
        }
        return moves;
    }

    //board move function
    public Board makeMove(int start, int end) {
        Board c = new Board();
        c.cloneBoard(this);
        int startX = (start-1) % 8;
        int startY = (start-1) / 8;
        int endX = (end-1) % 8;
        int endY = (end-1) / 8;
        int deltX = Math.abs(endX - startX);
        int deltY = Math.abs(endY - startY);
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
        c.gameBoard[startY][startX] = ' ';
        if(deltX > 0){
            if(startX > endX){
                c.gameBoard[startY][startX-1] = ' ';
                if(deltX > 2){
                    c.gameBoard[startY][startX-3] = ' ';
                    if(deltX > 4){
                        c.gameBoard[startY][startX-5] = ' ';
                        c.gameBoard[startY][startX-6] = mover;
                    }
                    else{
                        c.gameBoard[startY][startX-4] = mover;
                    }
                }
                else{
                    c.gameBoard[startY][startX-2] = mover;
                }
            }
            else{
                c.gameBoard[startY][startX+1] = ' ';
                if(deltX > 2){
                    c.gameBoard[startY][startX+3] = ' ';
                    if(deltX > 4){
                        c.gameBoard[startY][startX+5] = ' ';
                        c.gameBoard[startY][startX+6] = mover;
                    }
                    else{
                        c.gameBoard[startY][startX+4] = mover;
                    }
                }
                else{
                    c.gameBoard[startY][startX+2] = mover;
                }
            }
        }
        else{
            if(startY > endY){
                c.gameBoard[startY-1][startX] = ' ';
                if(deltY > 2){
                    c.gameBoard[startY-3][startX] = ' ';
                    if(deltY > 4){
                        c.gameBoard[startY-5][startX] = ' ';
                        c.gameBoard[startY-6][startX] = mover;
                    }
                    else{
                        c.gameBoard[startY-4][startX] = mover;
                    }
                }
                else{
                    c.gameBoard[startY-2][startX] = mover;
                }
            }
            else{
                c.gameBoard[startY+1][startX] = ' ';
                if(deltY > 2){
                    c.gameBoard[startY+3][startX] = ' ';
                    if(deltY > 4){
                        c.gameBoard[startY+5][startX] = ' ';
                        c.gameBoard[startY+6][startX] = mover;
                    }
                    else{
                        c.gameBoard[startY+4][startX] = mover;
                    }
                }
                else{
                    c.gameBoard[startY+2][startX] = mover;
                }
            }
        }
        c.move = ("Move piece at column " + (startX+1) + ", row " + (startY+1) + " to column " + (endX+1) + ", row " + (endY+1) + ".");
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
        System.out.println(" 12345678");
        for(int i = 0; i<gameBoard.length;i++){
            System.out.print(i+1);
            for(int j = 0; j<gameBoard[i].length;j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}
