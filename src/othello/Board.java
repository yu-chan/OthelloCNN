package othello;

import javax.swing.JPanel;
import java.awt.*;

public class Board extends JPanel {
	int size, mas, width, height;
	public Board(int size, int mas, int width, int height) {
		this.size = size;
		this.mas = mas;
		this.width = width;
		this.height = height;
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
		
		//ÉRÉ}
	}
}
