package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import entity.TileManager;

public class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	final int originalSize = 16; //16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalSize * scale;  //48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow =12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyInputs keyI = new KeyInputs();
	Thread gameThread;
	Player player =new Player(this, keyI);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		//set the size of this class(JPanel)
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		// if set to true, all the drawing from this component will be done 
		//in an offscreen painting buffer.
		this.addKeyListener(keyI);
		this.setFocusable(true);
		//With this, this GamePanel can be focused to receive key input
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	/*public void run() {
		
		double drawInterval = 1000000000/FPS;//0,016666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

		while(gameThread != null) {
			//System.out.println("The loop is running...");
			
			//long currentTime = System.nanoTime();
		//	Returns the current value of the running JVM's high-resolution time source,in nanoseconds.
			
			// 1  Update: update information such as character positions 
			update();
			// 2 Draw: draw the screen with the updated information
			repaint();
			// if fps is 30, the program does this (1&2) 30 times per second
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime <0) {
					remainingTime=0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	//2.yol
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			timer += (currentTime - lastTime);
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: "+ drawCount);
				drawCount = 0;
				timer=0;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		//Graphics2D class extends the graphic class to provide more sophisticaded control
		//over geometry, coordinate trandformations, color management, and text layout.
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
		//Dispose of this graphics context and release any system
		//resources that is using.
	}
	
}
