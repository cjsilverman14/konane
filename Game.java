
public class Game {

	public Game{
		
	}
	
	public void determineMove(Board b, char color, int depth) {
		BoardTree bt = new BoardTree(b,color);
		bt.fillNodes(bt.head,depth);
	}
	
	public boolean checkTerminal(Board b) {
		
	}
}
