package othello;

import nn.GameState;
import java.io.InputStreamReader;
import java.io.BufferedReader;
/*import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;*/

//public class Board extends JPanel implements MouseListener, Observer {
public class Board {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
//	int size, mas, width, height;
//	int mas;
	static GameState state;
	static CPU cpu;
	
	static final int BLACK = 1;
	static final int WHITE = -1;
	static final int TURN = 60;
	static final int MAS = 8;
	static final char BLACK_PIECE = '●';
	static final char WHITE_PIECE = '○';
	static final char EMPTY = '・';
	
	/*public Board(int size, int mas, int width, int height) {
		addMouseListener(this);
		
		this.size = size;
		this.mas = mas;
		this.width = width;
		this.height = height;
		
		state = new GameState(mas);
		cpu = new CPU();
	}*/
	
	/*public Board(int mas) {
		this.mas = mas;
		state = new GameState(mas);
		cpu = new CPU();
	}*/
	
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
	
	/*public void update(Observable o, Object arg) {
//		repaint();
//		invalidate();
//		validate();
	}*/
	
	/*public void paintComponent(Graphics g) {
		//背景
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		
		//線
		g.setColor(Color.BLACK);
		for(int i = 0; i <= mas; i++) {
			g.drawLine(0, i * size, width, i * size);
			g.drawLine(i * size, 0, i * size, height);
		}
		
		//駒
		for(int x = 0; x < mas; x++) {
			for(int y = 0; y < mas; y++) {
				if(state.data[x][y] == BLACK) {
					g.setColor(Color.BLACK);
					g.fillOval(x * size, y * size, size, size);
				} else if(state.data[x][y] == WHITE) {
					g.setColor(Color.WHITE);
					g.fillOval(x * size, y * size, size, size);
				}
			}
		}
	}*/
	
//	public void mousePressed(MouseEvent e) {
//		int x = e.getX() / size;
//		int y = e.getY() / size;
//		
//		if(state.turn > TURN) {
//			System.out.println("オセロは終了しました");
//			state.countPiece();
//			System.out.println("黒 : " + state.black + " 白 : " + state.white);
//		}
//		
//		
//		if(state.turn % 2 != 0) { //自分のターン
////			System.out.println("自分のターン");
//			System.out.println("黒のターン");
//			if(state.whether_put(x,  y, BLACK, true)) {
////				state.turn++;
////				state.data[x][y] = BLACK;
////				System.out.println("playerは押せた");
//				System.out.println("黒は押せた");
//			} else {
////				System.out.println("playerは押せなかった");
//				System.out.println("黒は押せなかった");
//			}
//		}
//		if(state.turn % 2 == 0) { //CPUのターン
//			/*
////			System.out.println("CPUのターン");
//			System.out.println("白のターン");
//			if(state.whether_put(x,  y, WHITE, true)) {
////				state.turn++;
////				state.data[x][y] = WHITE;
////				System.out.println("playerは押せた");
//				System.out.println("白は押せた");
//			} else {
////				System.out.println("playerは押せなかった");
//				System.out.println("白は押せなかった");
//			}
//			*/
////			state.turn++;
//			cpu.put(state, WHITE);
//		}
//		
//		/*
//		//駒を置く
//		if(state.data[x][y] == 0) {
//			if(state.turn % 2 == 0) {//CPUのターンなら、白を置く
//				state.data[x][y] = -1;
//			} else if(state.turn % 2 == 1) {//プレイヤーのターンなら、黒を置く
//				state.data[x][y] = 1;
//			}
//			state.turn++;
//		}
//		
//		System.out.println("押された");
//		System.out.println(state.data[x][y]);
//		System.out.println(state.turn);
//		*/
//		
//		repaint();
//	}
	
//	public void mouseClicked(MouseEvent e) {
//		
//	}
//	
//	public void mouseEntered(MouseEvent e) {
//		
//	}
//	
//	public void mouseExited(MouseEvent e) {
//		
//	}
//	
//	public void mouseReleased(MouseEvent e) {
//		
//	}
}
