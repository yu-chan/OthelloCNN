package nn;

public class GameState {
	public int data[][];
	public int turn;
	int player;
	int black;
	int white;
	
	public GameState(int mas) {
		data = new int[mas][mas];
		
		//黒:1 白:-1 なし:0
		data[3][3] = 1;
		data[3][4] = -1;
		data[4][3] = -1;
		data[4][4] = 1;
		
		turn = 1;
		player = 1;//0:CPU 1:プレイヤー
		black = 2;
		white = 2;
	}
	
	//リバースする
	public void reverse(int x, int y) {
	}
	
	//置けるかどうか
	public boolean whether_put(int x, int y) {
		
		//駒があるなら、置けない
		if(data[x][y] != 0) {
			return false;
		}
		
		//逆にできないなら、置けない
		if(whether_reverse(x, y) == false) {
			return false;
		}
		
		//駒を置く
		data[x][y] = 1;
		turn++;
		
		return true;
	}
	
	//リバースできるか
	public boolean whether_reverse(int x, int y) {
		return false;
	}
	
	//パスをチェックする
	public boolean checkPass() {
		
		//ボードを走査する
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				//駒があるところは、飛ばす
				if(data[x][y] != 0) {
					continue;
				}
				
				//リバースできるなら、パスしない
				if(whether_reverse(x, y) == true) {
					return false;
				}
			}
		}
		
		//パスする
		return true;
	}
	
	//白と黒の駒を数える
	public void countPiece() {
		black = 0;
		white = 0;
		
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(data[x][y] == 1) {
					black++;
				} else if(data[x][y] == -1) {
					white++;
				}
			}
		}
	}
}
