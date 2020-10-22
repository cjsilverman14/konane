
public class BoardTree {
	BoardNode head;
	Algorithm alg;
	public BoardTree(Board h, char color){
		head = new BoardNode(h,0);
		alg = new Algorithm(true,color);
	}
	
	public void fillNodes(BoardNode h, int depth) {
		if(depth == 0) {
			
		}
		else {
			h.fillSuccessors(alg.findSuccessors(h.b), depth);
			for(int i = 0; i < h.successors.size(); i++) {
				fillNodes(h.successors.get(i),depth-1);
			}
		}
	}
}
