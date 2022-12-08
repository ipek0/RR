package main;

import object.OBJ_Fuel;
import object.OBJ_Helicopter;
import object.OBJ_Ship;

public class AssetSetter {

	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Helicopter();
		gp.obj[0].worldX = 250;
		gp.obj[0].worldY = 7500;
		
		gp.obj[1] = new OBJ_Helicopter();
		gp.obj[1].worldX = 450;
		//gp.obj[1].worldX = 72 * gp.tileSize;
		gp.obj[1].worldY = 7450;	
		
		gp.obj[2] = new OBJ_Fuel();
		gp.obj[2].worldX = 280;
		gp.obj[2].worldY = 7400;
		
		gp.obj[3] = new OBJ_Ship();
		gp.obj[3].worldX = 450;
		gp.obj[3].worldY = 7300;
		
	}
}
