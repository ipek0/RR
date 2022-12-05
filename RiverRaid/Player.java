package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInputs;

public class Player extends Entity{

	GamePanel gp;
	KeyInputs keyI;
	
	public int screenX;
	public int screenY;
	
	
	public Player (GamePanel gp, KeyInputs keyI) {
		this.gp=gp;
		this.keyI=keyI;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = 9*gp.screenHeight/10- (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 5;
		solidArea.y = 6;
		solidArea.width = 31;
		solidArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = 360; 
		worldY = 3750;//for now
		speed = 4;
		//direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			plane = ImageIO.read(getClass().getResourceAsStream("ucak.png")); 
			ship = ImageIO.read(getClass().getResourceAsStream("gemi.png"));
			helicopter = ImageIO.read(getClass().getResourceAsStream("helicopter.png"));
			fuel = ImageIO.read(getClass().getResourceAsStream("fuel.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if(keyI.upPressed == true || keyI.downPressed == true || keyI.leftPressed == true ||
				keyI.rightPressed == true ) {
			
			if(collisionOn == false) {
				if(keyI.upPressed == true) {
			
				//direction = "up";
				worldY -= speed;
				//screenY -=speed;
				//in java y values increases as they go down
				//in java x values increases to the right
				}
			/*else if(keyI.downPressed == true) {
				//direction = "down";
				worldY += speed;
				//screenY +=speed;
			}*/
				else if(keyI.leftPressed == true) {
				//direction = "left";
				worldX -= speed;
				screenX -=speed;
				}
				else if(keyI.rightPressed == true) {
				//direction = "right";
				worldX += speed;
				screenX +=speed;
				}
			
			}
		}
		// CHECH TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		// IF COLLISION IS FALSE, PLAYER CAN MOVE
		
	}
	
	
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		//draw rectangle and fill it with the color white
		
		BufferedImage image = null;
	
	/*	switch(direction) {
		case "up":
			if(spriteNum ==1)
				image = up1;
			if(spriteNum ==2)
				image =up2;
			break; ...
		}*/
		g2.drawImage(plane, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
	}
}
