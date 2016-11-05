package othello;

import java.util.*;
import nn.GameState;

public class CPU {
	int color;//白
	
	public CPU() {
		color = -1;
	}
	
	public void put(GameState state, int color) {
		//置ける場所を格納
		ArrayList<int[]> array_put = new ArrayList<int[]>();
		
		//置ける場所を探す
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				//駒があるなら、飛ばす
				if(state.data[x][y] != 0) {
					continue;
				}
				
				//置けるので、array_putに加える
			}
		}
		
		//ランダムに決める
	}
}
