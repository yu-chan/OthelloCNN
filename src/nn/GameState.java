package nn;

public class GameState {
	private int data[][];
	private int turn;
	int player;
	private int black;
	private int white;
	
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
//	public void reverse(int x, int y) {
//	}
	
	//置けるかどうか
	public boolean whether_put(int x, int y, int color, boolean doReverse) {
		
		//駒があるなら、置けない
		if(data[x][y] != 0) {
			System.out.println("駒があるから、置けない");
			return false;
		}
		
		
		//逆にできないなら、置けない
		if(whether_reverse(x, y, color, doReverse) == false) {
			System.out.println("逆にできない");
			return false;
		}
		
		//駒を置く
		data[x][y] = color;
		turn++;
		
		return true;
	}
	
	//リバースできるか
	public boolean whether_reverse(int x, int y, int color, boolean doReverse) {
		int dir[][] = {
				{-1, -1}, {0, -1}, {1, -1},
				{-1,  0},		   {1,  0},
				{-1,  1}, {0,  1}, {1,  1}
		};
		
		boolean reverse = false;
		
		for(int i = 0; i < 8; i++) {
			//隣のマス
			int x0 = x + dir[i][0];
			int y0 = y + dir[i][1];
			
			//隣のマスがボード外なら、飛ばす
			if(isOut(x0, y0) == true) {
//				System.out.println("ボード外1 : " + i);
				continue;
			}
			
			int nextState = data[x0][y0];
//			if(nextState == 1) {//隣なマスが黒(プレイヤー)なら、飛ばす
			if(nextState == color) {//隣の駒が置いた駒と同じなら、飛ばす
				if(color == 1) {
//					System.out.println("隣は黒 : " + i);
				} else if(color == -1) {
//					System.out.println("隣は白 : " + i);
				}
				
				continue;
			} else if(nextState == 0) {//何もないなら、飛ばす
//				System.out.println("隣には何もなし : " + i);
				continue;
			}
			
			//隣のマスから走査して、colorがあればリバースする
			int j = 2;
			while(true) {
				int x1 = x + dir[i][0] * j;
				int y1 = y + dir[i][1] * j;
				
				if(isOut(x1, y1) == true) {
//					System.out.println("ボード外2 : " + i);
					break;
				}
				
				//走査しているマスが何もないなら、飛ばす
				if(data[x1][y1] == 0) {
					break;
				}
				
				//自分の駒があれば、リバースする
				if(data[x1][y1] == color) {
					if(doReverse) {
						for(int k = 1; k < j; k++) {
							int x2 = x + dir[i][0] * k;
							int y2 = y + dir[i][1] * k;
//							data[x2][y2] = 1;
							data[x2][y2] = color;
						}
					}
					reverse = true;
//					return true;
//					break;
				}
				
				j++;
			}
			
		}
		
//		return false;
		return reverse;
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
				if(whether_reverse(x, y, 1, false) == true) {
					return false;
				}
			}
		}
		
		//パスする
		turn++;
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
	
	public void result() {
		System.out.println("オセロは終了しました");
		countPiece();
		System.out.println("結果は");
		System.out.println("黒 : " + getBlack() + "白 : " + getWhite());
	}
	
	public int getData(int x, int y) {
		return this.data[x][y];
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public void incrementTurn() {
		this.turn++;
	}
	
	public int getBlack() {
		return this.black;
	}
	
	public int getWhite() {
		return this.white;
	}
}
