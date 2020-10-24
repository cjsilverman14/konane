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
                        if(j < 4 && b.gameBoard[i][j+3] == p.enemy && b.gameBoard[i][j+4] == ' '){
                            Board c2 = b.makeMove(space, space + 4);
                            c2.value = c2.evaluate();
                            successors.add(c2);
                            if(j < 2 && b.gameBoard[i][j+5] == p.enemy && b.gameBoard[i][j+6] == ' '){
                                Board c3 = b.makeMove(space, space + 6);
                                c3.value = c3.evaluate();
                                successors.add(c3);
                            }
                        }
                    }
                    if(j > 1 && b.gameBoard[i][j-1] == p.enemy && b.gameBoard[i][j-2] == ' ') {
                        Board c = b.makeMove(space, space - 2);
                        c.value = c.evaluate();
                        successors.add(c);
                        if(j > 3 && b.gameBoard[i][j-3] == p.enemy && b.gameBoard[i][j-4] == ' '){
                            Board c2 = b.makeMove(space, space - 4);
                            c2.value = c2.evaluate();
                            successors.add(c2);
                            if(j > 5 && b.gameBoard[i][j-5] == p.enemy && b.gameBoard[i][j-6] == ' '){
                                Board c3 = b.makeMove(space, space - 6);
                                c3.value = c3.evaluate();
                                successors.add(c3);
                            }
                        }
                    }
                    if(i < 6 && b.gameBoard[i+1][j] == p.enemy && b.gameBoard[i+2][j] == ' ') {
                        Board c = b.makeMove(space, space + 16);
                        c.value = c.evaluate();
                        successors.add(c);
                        if(i < 4 && b.gameBoard[i+3][j] == p.enemy && b.gameBoard[i+4][j] == ' '){
                            Board c2 = b.makeMove(space, space + 32);
                            c2.value = c2.evaluate();
                            successors.add(c2);
                            if(i < 2 && b.gameBoard[i+5][j] == p.enemy && b.gameBoard[i+6][j] == ' '){
                                Board c3 = b.makeMove(space, space + 48);
                                c3.value = c3.evaluate();
                                successors.add(c3);
                            }
                        }
                    }
                    if(i > 1 && b.gameBoard[i-1][j] == p.enemy && b.gameBoard[i-2][j] == ' ') {
                        Board c = b.makeMove(space, space - 16);
                        c.value = c.evaluate();
                        successors.add(c);
                        if(i > 3 && b.gameBoard[i-3][j] == p.enemy && b.gameBoard[i-4][j] == ' '){
                            Board c2 = b.makeMove(space, space - 32);
                            c2.value = c2.evaluate();
                            successors.add(c2);
                            if(i > 5 && b.gameBoard[i-5][j] == p.enemy && b.gameBoard[i-6][j] == ' '){
                                Board c3 = b.makeMove(space, space - 48);
                                c3.value = c3.evaluate();
                                successors.add(c3);
                            }
                        }
                    }
                }
            }
        }
        return successors;
    }
}
