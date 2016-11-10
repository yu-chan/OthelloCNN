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
	static final char BLACK_PIECE = '●';
	static final char WHITE_PIECE = '○';
	static final char EMPTY = '・';
	
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
		char suuti = '０';
		//マスの位置
		System.out.println("　０　１　２　３　４　５　６　７");
		
		//駒を表示
		for(int x = 0; x < MAS; x++) {
			System.out.print(suuti);
			for(int y = 0; y < MAS; y++) {
				if(state.getData(x, y) == BLACK) {
					System.out.print(" " + BLACK_PIECE + "　");
				} else if(state.getData(x, y) == WHITE) {
					System.out.print(" " + WHITE_PIECE + "　");
				} else {
					System.out.print(" " + EMPTY + "　");
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
			System.out.println("playerは置けません");
			return;
		}
		
		System.out.println("playerのターン");
		
		int x, y;
		
		//コンソールから入力
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
		
		//入力した数字が適切でない
		if(x < 0 || x >= MAS || y < 0 || y >= MAS) {
			System.out.println("もう一度入力してください");
			return;
		}
		
		if(state.whether_put(x, y, BLACK, true)) {
			System.out.println("playerは押せた");
		} else {
			System.out.println("playerは押せなかった");
		}
	}
}
