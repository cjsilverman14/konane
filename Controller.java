import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Controller {
    static Scanner keyboard = new Scanner(System.in);
    public static double branchCount;
    public static double branches;
    public static int staticEval;
    public static int cutOffs;
    public static void main(String[] args) {
        Board b = new Board(); //The board variable that will be used for the game
        b.initializeBoard();
        b.printBoard();
        Board bNext;
        Game g = new Game();
        boolean turn;//Whether it's the AI's turn or not
        int depth;//The depth of the search
        char color;//The AI's color
        char oColor;//The Opponent's color
        branchCount = 0;
        branches = 0;
        staticEval = 0;
        while(true) {//Set depth
            try {
                System.out.println("Enter a search depth (2-5): ");
                depth = keyboard.nextInt();
                if(depth > 1 && depth < 6) {//Get the search depth. 
                    depth--;//Our depth is set up more like levels, so a depth of 6 needs to be translated to a depth value of 4, 5 to 3, and so on
                    break;
                }
                else {
                    System.out.println("Invalid depth");
                }
            }
            catch(InputMismatchException e) {//Make sure it's an int
                System.out.println("Invalid Format");
            }
        }
        while(true) {//Set color
            System.out.println("What color am I? (B/W): ");
            color = keyboard.next().charAt(0);
            if(color == 'B' || color == 'W') {
                if(color == 'B') {//Black goes first
                    turn = true;
                    oColor = 'W';
                }
                else {//White goes second
                    turn = false;
                    oColor = 'B';
                }
                break;
            }
        }
        if(!turn) {//If the user goes first
            int firstMove;
            while(true) {
                try {//Grab the column of the removed piece. The row and the colomn are the same
                    System.out.println("Enter opponent removed piece row/col (1,4,5,8): ");
                    firstMove = keyboard.nextInt();
                    if(firstMove == 1 || firstMove == 4 || firstMove == 5 || firstMove == 8) {
                        break;
                    }
                    else {
                        System.out.println("Invalid First B Move");
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Invalid Number");
                }
            }
            switch(firstMove){//We were unable to figure out the best way to implement our algorithm into single remove moves. This just uses a fixed set of moves
                case 1:
                b.gameBoard[0][0] = ' ';
                b.gameBoard[0][1] = ' '; //No real choice needed, both options are symmetrical
                System.out.println("I remove the piece from row 1, column 2");
                break;
                case 4:
                b.gameBoard[3][3] = ' ';
                b.gameBoard[3][4] = ' ';
                System.out.println("I remove the piece from row 4, column 5");
                break;
                case 5:
                b.gameBoard[4][4] = ' ';
                b.gameBoard[4][3] = ' ';
                System.out.println("I remove the piece from row 5, column 4");
                break;
                default:
                b.gameBoard[7][7] = ' ';
                b.gameBoard[7][6] = ' ';//No real choice needed, both options are symmetrical
                System.out.println("I remove the piece from row 8, column 7");
                break;
            }
            b.printBoard();
        }
        else {
            //We were unable to use our algorithm to determine next move, but picking from the middle should always result
            //in more valid moves. The specific middle piece doesn't matter as the board is symmetrical
            b.gameBoard[3][3] = ' ';
            System.out.println("I remove the piece from row 4, column 4");
            b.printBoard();
            int moveCheck;
            char secondMove;
            while(true) {//Get the direction of the opponent's removed piece
                try {
                    System.out.println("Enter opponent removed piece direction (U/L/D/R): ");
                    secondMove = keyboard.next().charAt(0);
                    if(secondMove != 'U'|| secondMove != 'D' || secondMove != 'L' || secondMove != 'D'){
                        break;
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Invalid Direction");
                }
            }
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(b.gameBoard[i][j] == ' '){//Remove it from the board
                        switch(secondMove){
                            case 'U':
                            b.gameBoard[i-1][j] = ' ';
                            break;
                            case 'D':
                            b.gameBoard[i+1][j] = ' ';
                            break;
                            case 'L':
                            b.gameBoard[i][j-1] = ' ';
                            break;
                            case 'R':
                            b.gameBoard[i][j+1] = ' ';
                            break;
                            default:
                            break;
                        }
                        i = 8;
                        j = 8;
                    }
                }
            }
        }
        while(b.value > 0 && b.enemyValue > 0) {//Until the game is over
            b.printBoard();
            if(!turn) {//The user's turn
                int startCol = 1;
                int startRow = 1;
                int endCol = 1;
                int endRow = 1;
                int start;
                int end;
                while(true) {//Get their movement
                    try {
                        System.out.println("Enter piece start column: ");
                        startCol = keyboard.nextInt();
                        if(startCol < 9 && startCol > 0) {
                        }
                        else {
                            System.out.println("Not a valid number");
                            continue;
                        }
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Not a number");
                    }
                    try {
                        System.out.println("Enter piece start row: ");
                        startRow = keyboard.nextInt();
                        if(startRow < 9 && startRow > 0) {
                            break;
                        }
                        else {
                            System.out.println("Not a valid number");
                        }
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Not a number");
                    }
                }
                start = ((startRow-1)*8) + ((startCol)); 
                //makeMove takes arguments in the form of uniquely numbered squares. This converts it to that form
                while(true) {
                    try {
                        System.out.println("Enter piece end column: ");
                        endCol = keyboard.nextInt();
                        if(endCol % 2 != startCol % 2){
                            System.out.println("Invalid Column");
                            continue;
                        }
                        if(endCol < 9 && endCol > 0) {
                        }
                        else {
                            System.out.println("Not a valid number");
                            continue;
                        }
                        if(b.gameBoard[startRow-1][startCol-1] != oColor){
                            System.out.println("Not your piece");
                            continue;
                        }
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Not a number");
                    }
                    try {
                        System.out.println("Enter piece end row: ");
                        endRow = keyboard.nextInt();
                        if(endRow % 2 != startRow % 2 || (startCol != endCol && startRow != endRow)){
                            System.out.println("Invalid Move");
                        }
                        if(endRow < 9 && endRow > 0) {
                            break;
                        }
                        else {
                            System.out.println("Not a valid number");
                        }
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Not a number");
                    }
                }
                end = ((endRow-1)*8) + ((endCol));
                //Conversion specified above
                bNext = b.makeMove(start,end, false);
                b = bNext;
                turn = !turn;
            }
            else {//The AI's turn
                
                bNext = g.determineMove(b,color, depth);//Determine the move to make
                b = bNext;
                b.value = b.evaluate(color, oColor);//Find value to ensure it knows when to end the game
                System.out.println();
                System.out.println(b.move);
                turn = !turn;
            }
        }
        b.printBoard();
        if(b.value == 0){
            System.out.println("You win!");
        }
        else{
            System.out.println("I win!");
        }
        System.out.println("Average Branching Factor: " + (branchCount / branches));
        System.out.println("Number of Static Evaluations: " + staticEval);
        System.out.println("Number of cutoffs: " + cutOffs);
    }

    
}
