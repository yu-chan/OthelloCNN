package othello;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Insets;

public class Othello {
	public static void main(String args[]) {
		JFrame frame = new JFrame("ƒIƒZƒ");
		Insets insets =  frame.getInsets();
		/*“à‘¤‚Ì‘å‚«‚³@‰¡:-8   c:-34*/
		frame.setBounds(200, 200, 100 + insets.left + insets.right, 150 + insets.top + insets.bottom);
		frame.setVisible(true);
	}
}
