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
}
