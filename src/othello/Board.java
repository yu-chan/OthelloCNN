package othello;

import nn.GameState;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Board extends JPanel implements MouseListener, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int size, mas, width, height;
	GameState state;
	CPU cpu;
	
	static final int BLACK = 1;
	static final int WHITE = -1;
	static final int TURN = 60;
	
	public Board(int size, int mas, int width, int height) {
		addMouseListener(this);
		
		this.size = size;
		this.mas = mas;
		this.width = width;
		this.height = height;
		
		state = new GameState(mas);
		cpu = new CPU();
	}
	
	public void update(Observable o, Object arg) {
//		repaint();
//		invalidate();
//		validate();
	}
	
	public void paintComponent(Graphics g) {
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
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		
		if(state.turn == TURN) {
			System.out.println("オセロは終了しました");
		}
		
		
		if(state.turn % 2 != 0) { //自分のターン
			System.out.println("自分のターン");
			if(state.whether_put(x,  y, BLACK)) {
				state.turn++;
				state.data[x][y] = BLACK;
				System.out.println("playerは押せた");
			} else {
				System.out.println("playerは押せなかった");
			}
		} else if(state.turn % 2 == 0) { //CPUのターン
			System.out.println("CPUのターン");
//			state.turn++;
//			cpu.put(state);
		}
		
		/*
		//駒を置く
		if(state.data[x][y] == 0) {
			if(state.turn % 2 == 0) {//CPUのターンなら、白を置く
				state.data[x][y] = -1;
			} else if(state.turn % 2 == 1) {//プレイヤーのターンなら、黒を置く
				state.data[x][y] = 1;
			}
			state.turn++;
		}
		
		System.out.println("押された");
		System.out.println(state.data[x][y]);
		System.out.println(state.turn);
		*/
		
		repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
}
