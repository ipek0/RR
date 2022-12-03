package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//This lets the window properly close when user clicks the close button.
		window.setResizable(false);
		window.setTitle("River raid");
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);
		
		window.pack(); // size: 768x576
		/* Causes this Window to be sized to fit the preferred size
		 * and layouts of its subcomponents(=GamePanel).
		 */
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamepanel.startGameThread();
	}

}