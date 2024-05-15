package main;

import entity.NPC_MiningMan;
import object.ObjectChest;
import object.ObjectCopperOre;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        for(int i = 0; i < 255; i++){
            gp.obj[i] = new ObjectCopperOre();
            gp.obj[i].worldX = 9 * gp.tileSize;
            gp.obj[i].worldY = 9 * gp.tileSize;
        }
        gp.obj[0] = new ObjectCopperOre();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new ObjectCopperOre();
        gp.obj[1].worldX = 14 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new ObjectChest();
        gp.obj[2].worldX = 3 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new ObjectChest();
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 9 * gp.tileSize;

        gp.obj[4] = new ObjectCopperOre(); // Objects are layer-able - be careful
        gp.obj[4].worldX = 4 * gp.tileSize;
        gp.obj[4].worldY = 9 * gp.tileSize;

    }
    public void setNPC(){
        gp.npc[0] = new NPC_MiningMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 11;
    }

}
