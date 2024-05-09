package main;

import object.ObjectChest;
import object.ObjectCopper;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new ObjectCopper();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new ObjectCopper();
        gp.obj[1].worldX = 14 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new ObjectChest();
        gp.obj[2].worldX = 3 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new ObjectChest();
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 9 * gp.tileSize;

        gp.obj[4] = new ObjectCopper(); // Objects are layer-able - be careful
        gp.obj[4].worldX = 4 * gp.tileSize;
        gp.obj[4].worldY = 9 * gp.tileSize;

    }
}
