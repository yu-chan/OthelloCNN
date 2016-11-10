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
	static final char BLACK_PIECE = '��';
	static final char WHITE_PIECE = '��';
	static final char EMPTY = '�E';
	
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
	
	/*public void update(Observable o, Object arg) {
//		repaint();
//		invalidate();
//		validate();
	}*/
	
	/*public void paintComponent(Graphics g) {
		//�w�i
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		
		//��
		g.setColor(Color.BLACK);
		for(int i = 0; i <= mas; i++) {
			g.drawLine(0, i * size, width, i * size);
			g.drawLine(i * size, 0, i * size, height);
		}
		
		//��
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
//			System.out.println("�I�Z���͏I�����܂���");
//			state.countPiece();
//			System.out.println("�� : " + state.black + " �� : " + state.white);
//		}
//		
//		
//		if(state.turn % 2 != 0) { //�����̃^�[��
////			System.out.println("�����̃^�[��");
//			System.out.println("���̃^�[��");
//			if(state.whether_put(x,  y, BLACK, true)) {
////				state.turn++;
////				state.data[x][y] = BLACK;
////				System.out.println("player�͉�����");
//				System.out.println("���͉�����");
//			} else {
////				System.out.println("player�͉����Ȃ�����");
//				System.out.println("���͉����Ȃ�����");
//			}
//		}
//		if(state.turn % 2 == 0) { //CPU�̃^�[��
//			/*
////			System.out.println("CPU�̃^�[��");
//			System.out.println("���̃^�[��");
//			if(state.whether_put(x,  y, WHITE, true)) {
////				state.turn++;
////				state.data[x][y] = WHITE;
////				System.out.println("player�͉�����");
//				System.out.println("���͉�����");
//			} else {
////				System.out.println("player�͉����Ȃ�����");
//				System.out.println("���͉����Ȃ�����");
//			}
//			*/
////			state.turn++;
//			cpu.put(state, WHITE);
//		}
//		
//		/*
//		//���u��
//		if(state.data[x][y] == 0) {
//			if(state.turn % 2 == 0) {//CPU�̃^�[���Ȃ�A����u��
//				state.data[x][y] = -1;
//			} else if(state.turn % 2 == 1) {//�v���C���[�̃^�[���Ȃ�A����u��
//				state.data[x][y] = 1;
//			}
//			state.turn++;
//		}
//		
//		System.out.println("�����ꂽ");
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
