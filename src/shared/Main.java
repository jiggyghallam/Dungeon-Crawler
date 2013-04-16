package shared;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main{
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createBase();
			}
		});
		
		
	}

	private static void createBase() {
		JFrame frame = new JFrame();
		frame.setSize(1280, 720);
		JPanel panel = new JPanel(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		GameMain b = new GameMain();
		panel.add(b);
		b.start();
		b.Startup();
		
	}

}
