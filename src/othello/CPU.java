package othello;

import java.util.*;
import nn.GameState;
import nn.CNN;

public class CPU {
	int color;//白
	Random rnd;
	
	public CPU() {
		color = 2;
		rnd = new Random();
	}
	
	public boolean whether_put(GameState state) {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(state.whether_put(x, y, color, false)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void put(GameState state, int color) {
		//CPUのターンでないから、待つ
		if(state.getTurn() % 2 != 0) {
			return;
		}
		
		System.out.println("cpuのターン");
		
		//置ける場所を格納
		ArrayList<int[]> array_put = new ArrayList<int[]>();
		
		//置ける場所を探す
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				//駒があるなら、飛ばす
				if(state.getData(x, y) != 0) {
					continue;
				}
				
				//置けるので、array_putに加える
				if(state.whether_reverse(x, y, color, false)) {
					int pos[] = {x, y};
					array_put.add(pos);
				}
			}
		}
		
		//array_putに一つもデータが隠されていないなら、パスする
		if(array_put.size() == 0) {
			System.out.println("cpuは置けません");
			state.incrementTurn();
			return;
		}
		
		//ランダムに決める
//		Random rnd = new Random();
		int index = rnd.nextInt(array_put.size());
		
		//その位置にコマを置く
		int[] pos = array_put.get(index);
		state.whether_put(pos[0], pos[1], color, true);
		
		//解放
		array_put.clear();
	}
}
