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
				}
				catch(InputMismatchException e) {
					
				}
			}
		}
		else {
			
		}
		while(b.value > 0 && b.enemyValue > 0) {
			if(turn) {
				int start;
				int end;
				System.out.println("Enter piece start space: ");
				while(true) {
					try {
						System.out.println("Enter piece start space: ");
						start = keyboard.nextInt();
						if(start < 65 && start > 0) {
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
				while(true) {
					try {
						System.out.println("Enter piece start space: ");
						end = keyboard.nextInt();
						if(end < 65 && end > 0) {
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
				bNext = b.makeMove(start,end);
				turn = !turn;
			}
			else {
				bNext = g.determineMove(b,color, depth);
				turn = !turn;
			}
		}
	}
	
	
	
}
