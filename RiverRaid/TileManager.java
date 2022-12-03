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
	Tiles[] tile;
	int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp=gp;
		tile = new Tiles[10];//for now
		mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		try {
			tile[0]=new Tiles();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));
			
			tile[1]=new Tiles();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("water.png"));
			//başka packageden alacaksan /tiles/grass.png
			
			tile[2]=new Tiles();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("ev.png"));
			
			tile[3]=new Tiles();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("tree.png"));
			
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
			//extract a tile number which is stored in mapTileNum[0][0]
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			worldCol++;
			
			if(worldCol ==gp.maxScreenCol) {
				worldCol=0;
				worldRow++;
			}
		}
	}


}
