package othello;

import nn.GameState;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Board {
	static GameState state;
	static Player player;
	static CPU cpu;
	
	static final int EMPTY = 0;
	static final int BLACK = 1;
	static final int WHITE = 2;
	static final int TURN = 60;
	static final int MAS = 8;
	static final char[] PIECE = {'�E', '��', '��'};
//	static final char BLACK_PIECE = '��';
//	static final char WHITE_PIECE = '��';
//	static final char EMPTY_PIECE = '�E';
	
	public static void main(String[] args) {
		state = new GameState(MAS);
		player = new Player();
		cpu = new CPU();
		/*while(state.getTurn() <= TURN + 1) {
			display();
			put();
			display();
			cpu.put(state, WHITE);
		}*/
		while(whether_put() || cpu.whether_put(state)) {
			display();
			put();
			state.countPiece();
			display();
			cpu.put(state, WHITE);
			state.countPiece();
		}
		display();
		state.result();
		state.releaseData();
	}
	
	public static void display() {
//		char suuti = '�O';
		//�}�X�̈ʒu
		System.out.println("�@�O�@�P�@�Q�@�R�@�S�@�T�@�U�@�V");
		
		//���\��
		for(int x = 0; x < MAS; x++) {
			System.out.print((char)('�O' + x));
			for(int y = 0; y < MAS; y++) {
				System.out.print(PIECE[state.getData(x, y)] + "�@");
				/*if(state.getData(x, y) == BLACK) {
					System.out.print(" " + BLACK_PIECE + "�@");
				} else if(state.getData(x, y) == WHITE) {
					System.out.print(" " + WHITE_PIECE + "�@");
				} else {
					System.out.print(" " + EMPTY_PIECE + "�@");
				}*/
			}
			System.out.println();
//			suuti++;
		}
	}
	
	public static boolean whether_put() {
//		boolean player = false, cpu = false;
		for(int x = 0; x < MAS; x++) {
			for(int y = 0; y < MAS; y++) {
				if(state.whether_put(x, y, BLACK, false)) {
					return true;
				}
			}
		}
		return false;
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
