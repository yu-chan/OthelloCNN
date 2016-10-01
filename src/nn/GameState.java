package nn;

public class GameState {
	public int data[][];
	public int turn;
	int player;
	int black;
	int white;
	
	public GameState(int mas) {
		data = new int[mas][mas];
		
		//��:1 ��:-1 �Ȃ�:0
		data[3][3] = 1;
		data[3][4] = -1;
		data[4][3] = -1;
		data[4][4] = 1;
		
		turn = 1;
		player = 1;//0:CPU 1:�v���C���[
		black = 2;
		white = 2;
	}
	
	//���o�[�X����
	public void reverse(int x, int y) {
	}
	
	//�u���邩�ǂ���
	public boolean whether_put(int x, int y) {
		
		//�����Ȃ�A�u���Ȃ�
		if(data[x][y] != 0) {
			return false;
		}
		
		//�t�ɂł��Ȃ��Ȃ�A�u���Ȃ�
		if(whether_reverse(x, y) == false) {
			return false;
		}
		
		//���u��
		data[x][y] = 1;
		turn++;
		
		return true;
	}
	
	//���o�[�X�ł��邩
	public boolean whether_reverse(int x, int y) {
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
				if(whether_reverse(x, y) == true) {
					return false;
				}
			}
		}
		
		//�p�X����
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
				} else if(data[x][y] == -1) {
					white++;
				}
			}
		}
	}
}
