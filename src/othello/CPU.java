package othello;

import nn.GameState;

public class CPU {
	int color;//”’
	
	public CPU() {
		color = -1;
	}
	
	public void put(GameState state) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				//‹î‚ª‚ ‚é‚È‚çA”ò‚Î‚·
				if(state.data[x][y] != 0) {
					continue;
				}
			}
		}
	}
}
