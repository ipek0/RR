package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
	int entityLeftWorldX = entity.worldX + entity.solidArea.x;	
	int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
	int entityTopWorldY = entity.worldY + entity.solidArea.y;
	int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
	
	int entityLeftCol = entityLeftWorldX/gp.tileSize;
	int entityRightCol = entityRightWorldX/gp.tileSize;
	int entityTopRow = entityTopWorldY/gp.tileSize;
	int entityBottomRow = entityBottomWorldY/gp.tileSize;
	
	int tileNum1, tileNum2, tileNum3, tileNum4;
	
	tileNum1 =gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
	tileNum2 =gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
	tileNum3 =gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];
	tileNum4 =gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];
	
	if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ||
			gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
		entity.collisionOn = true;
	}
	
	
	}

	public int checkObject (Entity entity, boolean player) {
		int index = 999;
		for(int i =0; i< gp.Obj.length ; i++) {
			if ( gp.Obj[i] != null) {
				 
				//get entitiys solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get the objects solid are position
				gp.Obj[i].solidArea.x = gp.Obj[i].worldX + gp.Obj[i].solidArea.x;
				gp.Obj[i].solidArea.y = gp.Obj[i].worldY + gp.Obj[i].solidArea.y;
				
				switch(entity.Direction) { //direction niye kýrmzý off
				case "up":
					//y için de yapabiliriz ama bi dk
						break;
				case "down":
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.Obj[i].solidArea))
						System.out.println("left collision!");	//bu check etmek için
						if (gp.Obj[i].collision = true) {
						entity.collisionOn  = true;
					}
						if (player == true) {
							index = i;
						}
					break;
				case "right":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.Obj[i].solidArea))
						System.out.println("right collision!");	
					if (gp.Obj[i].collision = true) {
						entity.collisionOn  = true;     //uçak objeyle temas ederse anlamak için bu sanýrým
					}
						if (player == true) {
							index = i;
						}
					break;
				
				}
				entity.solidArea.x = entity.solidAreaDefoultX;
				entity.solidArea.y = entity.solidAreaDefoultY;
				gp.Obj[i].solidArea.x = gp.Obj[i].solidAreaDefoultX;
				gp.Obj[i].solidArea.y = gp.Obj[i].solidAreaDefoultY;
				
				
			}
		
}
		return index;
	}
}
	
	
	
	
	
	