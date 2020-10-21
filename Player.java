
public class Player {
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
