package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

		/* This stores variables that will be used in player, 
		 * monster, and NPC classes.
		 */
	public int worldX,worldY;
	//public int screenX,screenY;
	public int speed;
	
	public BufferedImage ship, plane, helicopter, tree, house, fuel, water, grass;
	/* It describes an image with an accesible buffer of
	 * image data.(we use this to store our image file
	 */
	
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
}
