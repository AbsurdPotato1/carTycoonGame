package main;

import entity.NPC_MiningMan;
import object.ObjectChest;
import object.ObjectCopperOre;
import object.ToolPickaxe;
import tile_interactive.copperOreNode;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        for(int i = 0; i < 255; i++){
            gp.obj[i] = new ObjectCopperOre(gp, 3, 3);
        }
        gp.obj[0] = new ObjectCopperOre(gp, 9, 9);

        gp.obj[1] = new ObjectCopperOre(gp, 14, 8);

        gp.obj[2] = new ObjectChest(gp, 3, 10);

        gp.obj[3] = new ObjectChest(gp, 4, 9);

        gp.obj[4] = new ObjectCopperOre(gp,  4, 9); // Objects are layer-able - be careful

    }

    public void setTool(){
        gp.tools[0] = new ToolPickaxe(gp, 4, 5);
    }

    public void setInteractiveTile(){
        for(int i = 0; i < 10; i++){
            gp.iTile[i] = new copperOreNode(gp, i+10, 4);
        }
    }

    public void setNPC(){
        gp.npc[0] = new NPC_MiningMan(gp, GamePanel.tileSize * 5, GamePanel.tileSize * 5);
    }

}
