package othello;

import nn.GameState;
import javax.swing.JPanel;
import java.awt.*;

public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int size, mas, width, height;
	GameState state;
	
	static final int BLACK = 1;
	static final int WHITE = -1;
	
	public Board(int size, int mas, int width, int height) {
		this.size = size;
		this.mas = mas;
		this.width = width;
		this.height = height;
		
		state = new GameState(mas);
	}
	
	public void paintComponent(Graphics g) {
		//îwåi
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		
		//ê¸
		g.setColor(Color.BLACK);
		for(int i = 0; i <= mas; i++) {
			g.drawLine(0, i * size, width, i * size);
			g.drawLine(i * size, 0, i * size, height);
		}
		
		//ãÓ
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
}
