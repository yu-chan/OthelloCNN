package othello;

import java.util.*;
import nn.GameState;

public class CPU {
	int color;//��
	
	public CPU() {
		color = -1;
	}
	
	public void put(GameState state, int color) {
		//�u����ꏊ���i�[
		ArrayList<int[]> array_put = new ArrayList<int[]>();
		
		//�u����ꏊ��T��
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				//�����Ȃ�A��΂�
				if(state.data[x][y] != 0) {
					continue;
				}
				
				//�u����̂ŁAarray_put�ɉ�����
			}
		}
		
		//�����_���Ɍ��߂�
	}
}
