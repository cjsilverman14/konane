import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Controller {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Board b = new Board();
        b.initializeBoard();
        b.printBoard();
        Board bNext = new Board();
        Game g = new Game();
        boolean turn;
        int depth;
        char color;
        char oColor;
        while(true) {
            try {
                System.out.println("Enter a search depth (1-6): ");
                depth = keyboard.nextInt();
                if(depth > 0 && depth < 7) {
                    break;
                }
                else {
                    System.out.println("Invalid depth");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid Format");
            }
        }
        while(true) {
            System.out.println("What color am I? (B/W): ");
            color = keyboard.next().charAt(0);
            if(color == 'B' || color == 'W') {
                if(color == 'B') {
                    turn = true;
                    oColor = 'W';
                }
                else {
                    turn = false;
                    oColor = 'B';
                }
                break;
            }
        }
        if(!turn) {
            int firstMove;
            while(true) {
                try {
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
            switch(firstMove){
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
        }
        else {
            //Stuff
            b.gameBoard[3][4] = ' ';
            System.out.println("I remove the piece from row 4, column 5");
            int moveCheck;
            char secondMove;
            while(true) {
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
                    if(b.gameBoard[i][j] == ' '){
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
        while(b.value > 0 && b.enemyValue > 0) {
            b.printBoard();
            if(!turn) {
                int startCol = 1;
                int startRow = 1;
                int endCol = 1;
                int endRow = 1;
                int start;
                int end;
                while(true) {
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
                while(true) {
                    try {
                        System.out.println("Enter piece end column: ");
                        endCol = keyboard.nextInt();
                        if(endCol < 9 && endCol > 0) {
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
                        System.out.println("Enter piece end row: ");
                        endRow = keyboard.nextInt();
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
                bNext = b.makeMove(start,end);
                b = bNext;
                turn = !turn;
            }
            else {
                bNext = g.determineMove(b,color, depth);
                b = bNext;
                b.value = b.evaluate(color, oColor);
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
    }

    
}
