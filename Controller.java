import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.InputMismatchException;

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
                }
                else {
                    turn = false;
                }
                break;
            }
        }
        if(!turn) {
            int firstMove;
            while(true) {
                try {
                    System.out.println("Enter opponent removed piece: ");
                    firstMove = keyboard.nextInt();
                    if(firstMove == 1 || firstMove == 28 || firstMove == 37 || firstMove == 64) {
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
                    break;
                case 28:
                    b.gameBoard[3][3] = ' ';
                    break;
                case 37:
                    b.gameBoard[4][4] = ' ';
                    break;
                default:
                    b.gameBoard[7][7] = ' ';
                    b.gameBoard[7][6] = ' ';//No real choice needed, both options are symmetrical
                    break;
            }
        }
        else {
            //Stuff
            int moveCheck;
            int secondMove;
            while(true) {
                try {
                    System.out.println("Enter opponent removed piece: ");
                    secondMove = keyboard.nextInt();
                }
                catch(InputMismatchException e) {
                    System.out.println("Invalid Number");
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
                System.out.println();
                turn = !turn;
            }
        }
    }

    
}
