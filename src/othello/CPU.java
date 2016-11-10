package othello;

import java.util.*;
import nn.GameState;

public class CPU {
	int color;//��
	
	public CPU() {
		color = -1;
	}
	
	public void put(GameState state, int color) {
		//CPU�̃^�[���łȂ��Ȃ�A�҂�
		if(state.getTurn() % 2 != 0) {
			return;
		}
		
		System.out.println("cpu�̃^�[��");
		
		//�u����ꏊ���i�[
		ArrayList<int[]> array_put = new ArrayList<int[]>();
		
		//�u����ꏊ��T��
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				
				//�����Ȃ�A��΂�
				if(state.getData(x, y) != 0) {
					continue;
				}
				
				//�u����̂ŁAarray_put�ɉ�����
				if(state.whether_reverse(x, y, color, false)) {
					int pos[] = {x, y};
					array_put.add(pos);
				}
			}
		}
		
		//array_put�Ɉ���f�[�^���i�[����Ă��Ȃ��Ȃ�A�p�X����
		if(array_put.size() == 0) {
			System.out.println("cpu�͒u���܂���");
			state.incrementTurn();
			return;
		}
		
		//�����_���Ɍ��߂�
		Random rnd = new Random();
		int index = rnd.nextInt(array_put.size());
		
		//���̈ʒu�ɃR�}��u��
		int[] pos = array_put.get(index);
		state.whether_put(pos[0], pos[1], color, true);
		
		//���
		array_put.clear();
	}
}
