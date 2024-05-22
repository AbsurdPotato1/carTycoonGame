package main;

import entity.NPC_MiningMan;
import object.ObjectChest;
import object.ObjectCopperOre;
import object.ObjectStick;
import object.ToolPickaxe;
import tile_interactive.choppableTree;
import tile_interactive.copperOreNode;
import tile_interactive.craftingBench;
import tile_interactive.shippingBay;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj.add(new ObjectChest(gp, 26 * GamePanel.tileSize, 21 * GamePanel.tileSize));
    }

    public void setTool(){
        new ToolPickaxe(gp, 0, 0);
        new ObjectStick(gp, 0, 0);
    }

    public void setInteractiveTile(){
        for(int i = 32; i <= 35; i++){
            for(int j = 17; j <= 19; j++){
                gp.iTile.add(new choppableTree(gp, i * GamePanel.tileSize, j * GamePanel.tileSize));
            }
        }
        for(int i = 23; i <= 27; i++){
            for(int j = 27; j <= 29; j++){
                gp.iTile.add(new choppableTree(gp, i * GamePanel.tileSize, j * GamePanel.tileSize));
            }
        }
        for(int i = 42; i <= 46; i++){
            for(int j = 22; j <= 24; j++){
                gp.iTile.add(new choppableTree(gp, i * GamePanel.tileSize, j * GamePanel.tileSize));
            }
        }
        for(int i = 42; i <= 45; i++){
            for(int j = 31; j <= 35; j++){
                gp.iTile.add(new choppableTree(gp, i * GamePanel.tileSize, j * GamePanel.tileSize));
            }
        }


        for(int i = 32; i < 35; i++){
            for(int j = 24; j <= 27; j++) {
                gp.iTile.add(new copperOreNode(gp, i * GamePanel.tileSize, j * GamePanel.tileSize));
            }
        }

        gp.iTile.add(new shippingBay(gp, 28 * GamePanel.tileSize, 21 * GamePanel.tileSize));
        gp.iTile.add(new craftingBench(gp, 25 * GamePanel.tileSize, 21 * GamePanel.tileSize));
    }

    public void setNPC(){
        gp.npc.add(new NPC_MiningMan(gp, 1000, 800));
    }

}
