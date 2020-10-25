
public class BoardTree {//The BoardTree class for storing successors
    BoardNode head;
    Algorithm alg;
    public BoardTree(Board h, char color){//Constructor, forms the algorithm class and head of the tree
        head = new BoardNode(h,0);
        alg = new Algorithm(true,color);
    }

    public void fillNodes(BoardNode h, int depth, boolean level, boolean stepOneNode) {//Populate the tree with nodes
        if(depth == 0) {//If it's a leaf, nothing happens
            return;
        }
        

        h.fillSuccessors(alg.findSuccessors(h.b,level, stepOneNode), depth);//Set the successors of a node
        level = !level;//Change who it is finding successive moves for
        Controller.branches++;
        for(int i = 0; i < h.successors.size(); i++) {
            Controller.branchCount++;
            fillNodes(h.successors.get(i),depth-1, level, false);//Recursively run this function on successive nodes
        }

    }
}
