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
	public boolean whether_put(int x, int y, int color) {
		
		//駒があるなら、置けない
		if(data[x][y] != 0) {
			System.out.println("駒があるから、置けない");
			return false;
		}
		
		/*
		//逆にできないなら、置けない
		if(whether_reverse(x, y) == false) {
			System.out.println("逆にできない");
			return false;
		}*/
		
		//駒を置く
//		data[x][y] = color;
//		turn++;
		
		return true;
	}
	
	//リバースできるか
	public boolean whether_reverse(int x, int y) {
		int dir[][] = {
				{-1, -1}, {0, -1}, {1, -1},
				{-1,  0},		   {1,  0},
				{-1,  1}, {0,  1}, {1,  1}
		};
		
		for(int i = 0; i < 8; i++) {
			//隣のマス
			int x0 = x + dir[i][0];
			int y0 = y + dir[i][0];
			
			//隣のマスがボード外なら、飛ばす
			if(isOut(x0, y0) == true) {
				continue;
			}
			
			int nextState = data[x0][y0];
			if(nextState == 1) {//隣なマスが黒(プレイヤー)なら、飛ばす
				continue;
			} else if(nextState == 0) {//何もないなら、飛ばす
				continue;
			}
			
			//隣のマスから走査して、黒があればリバースする
			int j = 2;
			while(true) {
				int x1 = x + dir[i][0] * j;
				int y1 = y + dir[i][1] * j;
				if(isOut(x1, y1) == true) {
					break;
				}
				
				//自分の駒があれば、リバースする
				if(data[x1][y1] == 1) {
					for(int k = 1; k < j; k++) {
						int x2 = x + dir[i][0] * k;
						int y2 = y + dir[i][1] * k;
						data[x2][y2] = 1;
					}
					break;
				}
				
				//走査しているマスが何もないなら、飛ばす
				if(data[x1][y1] == 0) {
					break;
				}
				
				j++;
			}
			
		}
		
		return false;
	}
	
	public boolean isOut(int x, int y) {
		if(x < 0 || y < 0 || x >= 8 || y >= 8) {
			return true;
		}
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
