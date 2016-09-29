package othello;

import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.Container;

public class Othello {
	public static void main(String args[]) {
		JFrame frame = new JFrame("ÉIÉZÉç");
//		frame.getInsets().set(15, 4,4,4);
		frame.pack();
		Insets insets =  frame.getInsets();
//		insets.set(100,  100, 100, 100);
		/*insets.left = 100;
		insets.right = 100;
		insets.top = 100;
		insets.bottom = 100;*/
		/*ì‡ë§ÇÃëÂÇ´Ç≥Å@â°:-8   èc:-34*/
		frame.setBounds(200, 200, 200 + insets.left + insets.right, 200 + insets.top + insets.bottom);
//		frame.setBounds(200, 200, 100, 100);
		//frame.setSize(100, 100);
		frame.setVisible(true);
		System.out.println(insets.left);
		System.out.println(insets.right);
		System.out.println(insets.top);
		System.out.println(insets.bottom);
		System.out.println(frame.getInsets().left);
		System.out.println(frame.getInsets().right);
		System.out.println(frame.getInsets().top);
		System.out.println(frame.getInsets().bottom);
		System.out.println(frame.getInsets());
		System.out.println(insets);
		System.out.println(100 + insets.left + insets.right);
		System.out.println(100 + insets.top + insets.bottom);
	}
}
