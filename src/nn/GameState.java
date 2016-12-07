package nn;

public class GameState {
	private int data[][];
	private int priority[][] = {
			{1, 5, 4, 4, 4, 4, 5, 1},
			{5, 5, 3, 3, 3, 3, 5, 5},
			{4, 3, 2, 2, 2, 2, 3, 4},
			{4, 3, 2, 0, 0, 2, 3, 4},
			{4, 3, 2, 0, 0, 2, 3, 4},
			{4, 3, 2, 2, 2, 2, 3, 4},
			{5, 5, 3, 3, 3, 3, 5, 5},
			{1, 5, 4, 4, 4, 4, 5, 1}
	};
	private int turn;
	int player;
	private int black;
	private int white;
	
	public GameState(int mas) {
		data = new int[mas][mas];
		
		//黒：1 白:2 なし：0
		data[3][3] = 1;
		data[3][4] = 2;
		data[4][3] = 2;
		data[4][4] = 1;
		
		turn = 1;
		player = 1;//0:CPU 1:プレイヤー
		black = 2;
		white = 2;
	}
	
	//プレイヤーとCPU、どちらも置けるかどうか
	public boolean whether_put() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				
			}
		}
		return false;
	}
	
	//x、yの位置に置けるかどうか
	public boolean whether_put(int x, int y, int color, boolean doReverse) {
		
		//駒があるなら、置けない
		if(data[y][x] != 0) {
//			System.out.println("駒があるから、置けない");
			return false;
		}
		
		
		//逆にできないなら、置けない
		if(whether_reverse(x, y, color, doReverse) == false) {
//			System.out.println("逆にできない");
			return false;
		}
		
		//駒を置く
		if(doReverse) {
			data[y][x] = color;
			turn++;
		}
		
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
				continue;
			}
			
			int nextState = data[y0][x0];
			if(nextState == color) {//隣の駒が置いた駒と同じなら、飛ばす
				continue;
			} else if(nextState == 0) {//何もないなら、飛ばす
				continue;
			}
			
			//隣のマスから走査して、 colorがあればリバースする
			int j = 2;
			while(true) {
				int x1 = x + dir[i][0] * j;
				int y1 = y + dir[i][1] * j;
				
				if(isOut(x1, y1) == true) {
					break;
				}
				
				//走査しているマスが何もないなら、飛ばす
				if(data[y1][x1] == 0) {
					break;
				}
				
				//自分の駒があれば、リバースする
				if(data[y1][x1] == color) {
					if(doReverse) {
						for(int k = 1; k < j; k++) {
							int x2 = x + dir[i][0] * k;
							int y2 = y + dir[i][1] * k;
							data[y2][x2] = color;
						}
					}
					reverse = true;
					break;
				}
				
				j++;
			}
			
		}
		
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
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				
				//駒があるところは、飛ばす
				if(data[y][x] != 0) {
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
		
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(data[y][x] == 1) {
					black++;
				} else if(data[y][x] == 2) {
					white++;
				}
			}
		}
	}
	
	//データをコピー
	public void copyData(int[][] copy) {
		copy = (int[][])data.clone();
	}
	
	public void releaseData() {
		data = null;
	}
	
	public void result() {
		System.out.println("オセロは終了しました");
		countPiece();
		System.out.println("結果は");
		System.out.println("黒　:　" + getBlack() + "白　:　" + getWhite());
	}
	
	public int getData(int x, int y) {
		return this.data[y][x];
	}
	
	public int getPriority(int x, int y) {
		return this.priority[y][x];
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
