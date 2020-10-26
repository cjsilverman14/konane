
public class Game {

    public Game(){//Nothing needed in the constructor

    }

    public Board determineMove(Board b, char color, int depth) {//Find the next move to make
        BoardTree bt = new BoardTree(b,color);//Make a tree
        bt.fillNodes(bt.head,depth,true, true);
        Board nextBoard = new Board();
        if(bt.head.successors.size() > 1) {//If there is more than one successor
            for(BoardNode bN : bt.head.successors) {//For each successor
                bN.value = determineMove(bN,false,depth-1,-1000000,1000000);//Get the value recursively
            }
            int x = -1000000;
            for(int i = 0; i < bt.head.successors.size(); i++) {//For each successor
                if(bt.head.successors.get(i).value > x) {//Find the best successor
                    nextBoard = bt.head.successors.get(i).b;
                    x = bt.head.successors.get(i).value;
                }
            }
        }
        else if(bt.head.successors.size() == 0){//If there are no successors, the AI has lost
            System.out.println("Game Over");
            return b;
        }
        else {//If there is only one successor, the AI has no choice
            nextBoard = bt.head.successors.get(0).b;
        }
        bt = null;
        System.gc();//Attempt to clear some space, it didn't 
        return nextBoard;
    }

    public int determineMove(BoardNode bt, boolean max, int depth, int alpha, int beta) {
        if(depth == 0) {//If it's a leaf node, return the value
            return bt.value;
        }
        if(max) {//If this is a max part of the tree
            int moveVal = -1000000;
            for(int i = 0; i < bt.successors.size(); i++){//For each successor
                int value = determineMove(bt.successors.get(i),false,depth-1,alpha,beta);//Find the value of this node
                moveVal = Math.max(moveVal, value);//Find the max moveVal, and set the alpha value
                alpha = Math.max(alpha,  moveVal);
                
                if(beta <= alpha) {//If beta <= alpha, then it can be pruned
                    Controller.cutOffs++;
                    for(int j = i + 1; j < bt.successors.size(); ){
                        bt.successors.remove(j);
                    }
                    return moveVal;
                }
                
            }
            return moveVal;
        }
        else {
            int moveVal = 10000000;
            for(int i = 0; i < bt.successors.size(); i++){//For each successor
                int value = determineMove(bt.successors.get(i),true,depth-1,alpha,beta);//Recursively set value of node
                moveVal = Math.min(moveVal, value);//Find lowest valued successor
                beta = Math.min(beta, moveVal);//Set beta value
                
                if(beta <= alpha) {//If beta <= alpha, this branch can be pruned
                    Controller.cutOffs++;
                    for(int j = i + 1; j < bt.successors.size(); ){
                        bt.successors.remove(j);
                    }
                    return moveVal;
                }
                
            }
            return moveVal;
        }
    }

}
