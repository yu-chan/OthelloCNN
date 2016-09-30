package nn;

public class GameState {
	int data[][];
	int turn;
	int player;
	int black;
	int white;
	
	public GameState(int mas) {
		data = new int[mas][mas];
	}
}
