
public class Player {//A simple class to store which player is which, and who they're playing against
	char color;
	char enemy;
	public Player(char c) {
		color = c;
		if(c == 'B') {
			enemy = 'W';
		}
		else {
			enemy = 'B';
		}
	}
}
