package nn;

public class GameState {
	private int data[][];
	private int turn;
	int player;
	private int black;
	private int white;
	
	public GameState(int mas) {
		data = new int[mas][mas];
		
		//��:1 ��:-1 �Ȃ�:0
		data[3][3] = 1;
		data[3][4] = 2;
		data[4][3] = 2;
		data[4][4] = 1;
		
		turn = 1;
		player = 1;//0:CPU 1:�v���C���[
		black = 2;
		white = 2;
	}
	
	//�v���C���[��CPU�A�ǂ�����u���邩�ǂ���
	public boolean whether_put() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
			}
		}
		return false;
	}
	
	//x�Ay�̈ʒu�ɒu���邩�ǂ���
	public boolean whether_put(int x, int y, int color, boolean doReverse) {
		
		//�����Ȃ�A�u���Ȃ�
		if(data[x][y] != 0) {
//			System.out.println("����邩��A�u���Ȃ�");
			return false;
		}
		
		
		//�t�ɂł��Ȃ��Ȃ�A�u���Ȃ�
		if(whether_reverse(x, y, color, doReverse) == false) {
//			System.out.println("�t�ɂł��Ȃ�");
			return false;
		}
		
		//���u��
		if(doReverse) {
			data[x][y] = color;
			turn++;
		}
		
		return true;
	}
	
	//���o�[�X�ł��邩
	public boolean whether_reverse(int x, int y, int color, boolean doReverse) {
		int dir[][] = {
				{-1, -1}, {0, -1}, {1, -1},
				{-1,  0},		   {1,  0},
				{-1,  1}, {0,  1}, {1,  1}
		};
		
		boolean reverse = false;
		
		for(int i = 0; i < 8; i++) {
			//�ׂ̃}�X
			int x0 = x + dir[i][0];
			int y0 = y + dir[i][1];
			
			//�ׂ̃}�X���{�[�h�O�Ȃ�A��΂�
			if(isOut(x0, y0) == true) {
				continue;
			}
			
			int nextState = data[x0][y0];
			if(nextState == color) {//�ׂ̋�u������Ɠ����Ȃ�A��΂�
				continue;
			} else if(nextState == 0) {//�����Ȃ��Ȃ�A��΂�
				continue;
			}
			
			//�ׂ̃}�X���瑖�����āAcolor������΃��o�[�X����
			int j = 2;
			while(true) {
				int x1 = x + dir[i][0] * j;
				int y1 = y + dir[i][1] * j;
				
				if(isOut(x1, y1) == true) {
					break;
				}
				
				//�������Ă���}�X�������Ȃ��Ȃ�A��΂�
				if(data[x1][y1] == 0) {
					break;
				}
				
				//�����̋����΁A���o�[�X����
				if(data[x1][y1] == color) {
					if(doReverse) {
						for(int k = 1; k < j; k++) {
							int x2 = x + dir[i][0] * k;
							int y2 = y + dir[i][1] * k;
							data[x2][y2] = color;
						}
					}
					reverse = true;
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
	
	//�p�X���`�F�b�N����
	public boolean checkPass() {
		
		//�{�[�h�𑖍�����
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				//�����Ƃ���́A��΂�
				if(data[x][y] != 0) {
					continue;
				}
				
				//���o�[�X�ł���Ȃ�A�p�X���Ȃ�
				if(whether_reverse(x, y, 1, false) == true) {
					return false;
				}
			}
		}
		
		//�p�X����
		turn++;
		return true;
	}
	
	//���ƍ��̋�𐔂���
	public void countPiece() {
		black = 0;
		white = 0;
		
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(data[x][y] == 1) {
					black++;
				} else if(data[x][y] == 2) {
					white++;
				}
			}
		}
	}
	
	//�f�[�^���R�s�[
	public void copyData(int[][] copy) {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				copy[i][j] = data[i][j];
			}
		}
	}
	
	public void releaseData() {
		data = null;
	}
	
	public void result() {
		System.out.println("�I�Z���͏I�����܂���");
		countPiece();
		System.out.println("���ʂ�");
		System.out.println("�� : " + getBlack() + "�� : " + getWhite());
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
