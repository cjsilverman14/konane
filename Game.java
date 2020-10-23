
public class Game {

	public Game(){
		
	}
	
	public Board determineMove(Board b, char color, int depth) {
		BoardTree bt = new BoardTree(b,color);
		bt.fillNodes(bt.head,depth);
		Board nextBoard = new Board();
		if(bt.head.successors.size() > 1) {
			for(BoardNode bN : bt.head.successors) {
				bN.value = determineMove(bN,true,depth,-1000000,1000000);
			}
			int x = -1000000;
			for(int i = 0; i < bt.head.successors.size(); i++) {
				if(bt.head.successors.get(i).value > x) {
					nextBoard = bt.head.successors.get(i).b;
					x = bt.head.successors.get(i).value;
				}
			}
		}
		else {
			nextBoard = bt.head.successors.get(0).b;
		}
		return nextBoard;
	}
	
	public int determineMove(BoardNode bt, boolean max, int depth, int alpha, int beta) {
		if(depth == 0) {
			return bt.value;
		}
		if(max) {
			int moveVal = -1000000;
			for(BoardNode b : bt.successors) {
				int value = determineMove(b,false,depth-1,alpha,beta);
				moveVal = Math.max(moveVal, value);
				alpha = Math.max(alpha,  moveVal);
				if(beta <= alpha) {
					return moveVal;
				}
			}
			return moveVal;
		}
		else {
			int moveVal = 10000000;
			for(BoardNode b : bt.successors) {
				int value = determineMove(b,true,depth-1,alpha,beta);
				moveVal = Math.min(moveVal, value);
				beta = Math.min(beta, moveVal);
				if(beta <= alpha) {
					return moveVal;
				}
			}
			return moveVal;
		}
	}
	
}
