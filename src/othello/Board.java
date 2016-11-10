package othello;

import nn.GameState;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Board {
	static GameState state;
	static CPU cpu;
	
	static final int BLACK = 1;
	static final int WHITE = -1;
	static final int TURN = 60;
	static final int MAS = 8;
	static final char BLACK_PIECE = '��';
	static final char WHITE_PIECE = '��';
	static final char EMPTY = '�E';
	
	public static void main(String[] args) {
		state = new GameState(MAS);
		cpu = new CPU();
		while(state.getTurn() <= 60) {
			display();
			put();
			cpu.put(state, WHITE);
		}
		state.result();
	}
	
	public static void display() {
		char suuti = '�O';
		//�}�X�̈ʒu
		System.out.println("�@�O�@�P�@�Q�@�R�@�S�@�T�@�U�@�V");
		
		//���\��
		for(int x = 0; x < MAS; x++) {
			System.out.print(suuti);
			for(int y = 0; y < MAS; y++) {
				if(state.getData(x, y) == BLACK) {
					System.out.print(" " + BLACK_PIECE + "�@");
				} else if(state.getData(x, y) == WHITE) {
					System.out.print(" " + WHITE_PIECE + "�@");
				} else {
					System.out.print(" " + EMPTY + "�@");
				}
			}
			System.out.println();
			suuti++;
		}
	}
	
	public static void put() {
		if(state.getTurn() % 2 == 0) {
			return;
		}
		
		if(state.checkPass()) {
			System.out.println("player�͒u���܂���");
			return;
		}
		
		System.out.println("player�̃^�[��");
		
		int x, y;
		
		//�R���\�[���������
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		
		try {
			String buf = br.readLine();
			String[] str = buf.split(" ", 0);
			x = Integer.parseInt(str[0]);
			y = Integer.parseInt(str[1]);
		} catch(Exception e) {
			x = -1;
			y = -1;
		}
		
		//���͂����������K�؂łȂ�
		if(x < 0 || x >= MAS || y < 0 || y >= MAS) {
			System.out.println("������x���͂��Ă�������");
			return;
		}
		
		if(state.whether_put(x, y, BLACK, true)) {
			System.out.println("player�͉�����");
		} else {
			System.out.println("player�͉����Ȃ�����");
		}
	}
}
