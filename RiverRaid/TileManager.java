package entity;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tiles[] tile;
	public int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp=gp;
		tile = new Tiles[10];//for now
		mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world_map.txt");
	}
	
	public void getTileImage() {
		
		try {
			tile[0]=new Tiles();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));
			tile[0].collision = true;
			
			tile[1]=new Tiles();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("water.png"));
			//başka packageden alacaksan /tiles/grass.png
			
			tile[2]=new Tiles();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("house.png"));
			tile[2].collision = true;
			
			tile[3]=new Tiles();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("tree.png"));
			tile[3].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filemap) {
		 try {
			 
			 InputStream is = getClass().getResourceAsStream(filemap);
			 //to know the file
			 BufferedReader br = new BufferedReader(new InputStreamReader(is));
			 //to read the file
		 
			 int col=0;
			 int row =0;
			 
			 while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
				 
				 String line  = br.readLine();//read a line of text
				 
				 while(col < gp.maxWorldCol) {
					 String numbers[] = line.split(" ");
					 //String.split(String) --> split this string arounda matches of the given regular expression
					 //.split(" ") --> split the string at a space
					 int num = Integer.parseInt(numbers[col]);
					 //we store the extracted number in the mapTileNum[][]

					 mapTileNumber[col][row] = num;
					 col++;
				 }
				 if(col == gp.maxWorldCol) {
					 col=0;
					 row++;
				 }
			 }
			 br.close();
		 }catch(Exception e) {
			 
		 }
	}
	
	public void draw(Graphics2D g2) {
		
	//	g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
	//	g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum=mapTileNumber[worldCol][worldRow];
			
			int worldX = worldCol *gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//time --> video 5 14:40
			//extract a tile number which is stored in mapTileNum[0][0]
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize > gp.player.worldY - gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
					
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			worldCol++;
			
			if(worldCol ==gp.maxScreenCol) {
				worldCol=0;
				worldRow++;
			}
		}
	}


}
