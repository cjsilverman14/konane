import java.util.*;
import java.util.ArrayList;

public class Algorithm {
    boolean max;
    Player p;
    public Algorithm(boolean minMax, char color){//Create an algorithm object
        max = minMax;
        p = new Player(color);
    }
    public ArrayList<Board> findSuccessors(Board b, boolean myMove){//Create an arraylist of boards that can be reached in one move
        ArrayList<Board> successors = new ArrayList<Board>();
        int space = 0;
        char successorsMover;
        char successorsEnemy;
        if(myMove){//Determine who is making the move
            successorsMover = p.color;
            successorsEnemy = p.enemy;
        }
        else{
            successorsMover = p.enemy;
            successorsEnemy = p.color;
        }
        for(int i = 0; i < 8; i++) {//Find all moves, including jumps
            for(int j = 0; j < 8; j++) {
                space++;
                if(b.gameBoard[i][j] == successorsMover) {//Check a single jump
                    if(j < 6 && b.gameBoard[i][j+1] == successorsEnemy && b.gameBoard[i][j+2] == ' ') {
                        Board c = b.makeMove(space, space + 2);
                        c.value = c.evaluate(p.color, p.enemy);//Set the values of the board
                        successors.add(c);
                        if(j < 4 && b.gameBoard[i][j+3] == successorsEnemy && b.gameBoard[i][j+4] == ' '){//Check a double jump
                            Board c2 = b.makeMove(space, space + 4);
                            c2.value = c2.evaluate(p.color, p.enemy);
                            successors.add(c2);
                            if(j < 2 && b.gameBoard[i][j+5] == successorsEnemy && b.gameBoard[i][j+6] == ' '){//Check a triple jump (Quadruple not possible)
                                Board c3 = b.makeMove(space, space + 6);
                                c3.value = c3.evaluate(p.color, p.enemy);
                                successors.add(c3);
                            }
                        }
                    }
                    if(j > 1 && b.gameBoard[i][j-1] == successorsEnemy && b.gameBoard[i][j-2] == ' ') {
                        Board c = b.makeMove(space, space - 2);
                        c.value = c.evaluate(p.color, p.enemy);
                        successors.add(c);
                        if(j > 3 && b.gameBoard[i][j-3] == successorsEnemy && b.gameBoard[i][j-4] == ' '){
                            Board c2 = b.makeMove(space, space - 4);
                            c2.value = c2.evaluate(p.color, p.enemy);
                            successors.add(c2);
                            if(j > 5 && b.gameBoard[i][j-5] == successorsEnemy && b.gameBoard[i][j-6] == ' '){
                                Board c3 = b.makeMove(space, space - 6);
                                c3.value = c3.evaluate(p.color, p.enemy);
                                successors.add(c3);
                            }
                        }
                    }
                    if(i < 6 && b.gameBoard[i+1][j] == successorsEnemy && b.gameBoard[i+2][j] == ' ') {
                        Board c = b.makeMove(space, space + 16);
                        c.value = c.evaluate(p.color, p.enemy);
                        successors.add(c);
                        if(i < 4 && b.gameBoard[i+3][j] == successorsEnemy && b.gameBoard[i+4][j] == ' '){
                            Board c2 = b.makeMove(space, space + 32);
                            c2.value = c2.evaluate(p.color, p.enemy);
                            successors.add(c2);
                            if(i < 2 && b.gameBoard[i+5][j] == successorsEnemy && b.gameBoard[i+6][j] == ' '){
                                Board c3 = b.makeMove(space, space + 48);
                                c3.value = c3.evaluate(p.color, p.enemy);
                                successors.add(c3);
                            }
                        }
                    }
                    if(i > 1 && b.gameBoard[i-1][j] == successorsEnemy && b.gameBoard[i-2][j] == ' ') {
                        Board c = b.makeMove(space, space - 16);
                        c.value = c.evaluate(p.color, p.enemy);
                        successors.add(c);
                        if(i > 3 && b.gameBoard[i-3][j] == successorsEnemy && b.gameBoard[i-4][j] == ' '){
                            Board c2 = b.makeMove(space, space - 32);
                            c2.value = c2.evaluate(p.color, p.enemy);
                            successors.add(c2);
                            if(i > 5 && b.gameBoard[i-5][j] == successorsEnemy && b.gameBoard[i-6][j] == ' '){
                                Board c3 = b.makeMove(space, space - 48);
                                c3.value = c3.evaluate(p.color, p.enemy);
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
