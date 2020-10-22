import java.util.*;
import java.util.ArrayList;


public class Algorithm {
	boolean max;
	Player p;
	public Algorithm(boolean minMax, char color){
		max = minMax;
		p = new Player(color);
	}
	
	
	public ArrayList<Board> findSuccessors(Board b){
		ArrayList<Board> successors = new ArrayList<Board>();
		int space = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				space++;
				if(b.gameBoard[i][j] == p.color) {
					if(j < 6 && b.gameBoard[i][j+1] == p.enemy && b.gameBoard[i][j+2] == ' ') {
						Board c = b.makeMove(space, space + 2);
						c.value = c.evaluate();
						successors.add(c);
					}
					if(j > 1 && b.gameBoard[i][j-1] == p.enemy && b.gameBoard[i][j-2] == ' ') {
						Board c = b.makeMove(space, space - 2);
						c.value = c.evaluate();
						successors.add(c);
					}
					if(i < 6 && b.gameBoard[i+1][j] == p.enemy && b.gameBoard[i+2][j] == ' ') {
						Board c = b.makeMove(space, space + 16);
						c.value = c.evaluate();
						successors.add(c);
					}
					if(i > 1 && b.gameBoard[i-1][j] == p.enemy && b.gameBoard[i-2][j] == ' ') {
						Board c = b.makeMove(space, space - 16);
						c.value = c.evaluate();
						successors.add(c);
					}
				}
			}
		}
		return successors;
	}
}
