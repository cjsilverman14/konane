import java.util.ArrayList;
import java.util.Random;

public class RandomAI{
	
	
	public static Board getRandomMoves(Board b, char color){
		Algorithm alg = new Algorithm(true,color);
		ArrayList<Board> possibleMoves = alg.findSuccessors(b,true,true);
		Random rand = new Random();
		int x = rand.nextInt(possibleMoves.size());
		System.out.println(possibleMoves.get(x).move);
		return possibleMoves.get(x);
	}
}