import java.util.*;
public class BoardNode {
	Board b;
	ArrayList<BoardNode> successors;
	int depth;
	int value;
	public BoardNode(Board bd, int depth) {
		b = bd;
		successors = new ArrayList<BoardNode>();
		this.depth = depth;
	}
	
	
	
	public void fillSuccessors(ArrayList<Board> boards, int depth) {
		ArrayList<BoardNode> newSuccessors = new ArrayList<BoardNode>();
		for(int i = 0; i < boards.size(); i++) {
			BoardNode bn = new BoardNode(boards.get(i), depth);
			newSuccessors.add(bn);
		}
		this.successors = newSuccessors;
	}
}
