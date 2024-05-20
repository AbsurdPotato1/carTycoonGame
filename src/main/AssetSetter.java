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
            gp.obj.add(new ObjectCopperOre(gp, 3 * GamePanel.tileSize, 3 * GamePanel.tileSize));
        }
        gp.obj.add(new ObjectCopperOre(gp, 9 * GamePanel.tileSize, 9 * GamePanel.tileSize));

        gp.obj.add(new ObjectCopperOre(gp, 14 * GamePanel.tileSize, 8 * GamePanel.tileSize));

        gp.obj.add(new ObjectChest(gp, 3 * GamePanel.tileSize, 10 * GamePanel.tileSize));

        gp.obj.add(new ObjectChest(gp, 4 * GamePanel.tileSize, 9 * GamePanel.tileSize));

        gp.obj.add(new ObjectCopperOre(gp,  4 * GamePanel.tileSize, 9 * GamePanel.tileSize)); // Objects are layer-able - be careful

    }

    public void setTool(){
        gp.tools.add(new ToolPickaxe(gp, 4 * GamePanel.tileSize, 5 * GamePanel.tileSize));
    }

    public void setInteractiveTile(){
        for(int i = 0; i < 10; i++){
            gp.iTile[i] = new copperOreNode(gp, (i+10) * GamePanel.tileSize, 4 * GamePanel.tileSize);
        }
    }

    public void setNPC(){
        gp.npc[0] = new NPC_MiningMan(gp, GamePanel.tileSize * 7, GamePanel.tileSize * 5);
    }

}
