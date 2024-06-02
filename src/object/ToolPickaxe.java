package object;

import main.GamePanel;
import main.UtilityTool;
import tile_interactive.copperOreNode;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ToolPickaxe extends SuperObject {
    public static final int objectId = 0;
    public static final boolean craftable = true;
    public static final boolean sellable = true;
    public static int sellPrice = 10;
    public static String sellDescription = "Sells for $" + sellPrice;
    public static String description = "Used to mine ores";
    public static BufferedImage inventoryImage;
    public static HashMap<Integer, Integer> craftingRecipe = new HashMap<>();
    public void setCraftingRecipe(){
        craftingRecipe.put(IdToObject.getIdFromClass(ObjectCopperOre.class), 3);
        craftingRecipe.put(IdToObject.getIdFromClass(ObjectStick.class), 2);
    }
    public ToolPickaxe(GamePanel gp, int x, int y) {
        super(gp, x, y);
        collisionWidth = 48;
        collisionHeight = 48;
        objectWidth = 48;
        objectHeight = 48;
        collisionXOffset = 0;
        collisionYOffset = 0;
//        objectId = 0;
        solidArea = new Rectangle(collisionXOffset, collisionYOffset, collisionWidth, collisionHeight);
        image = UtilityTool.getImage("tools/pickaxe.png");
        inventoryImage = image;
        name = "pickaxe";
        setCraftingRecipe();
    }
    public void pickUpObject(int i){
        long currentTime;
        currentTime = System.nanoTime();
        if((currentTime - gp.player.lastPickUpTime) / (1000000000 / gp.FPS) >= gp.player.pickUpInterval){
            gp.player.lastPickUpTime = currentTime;
            if(gp.player.spaceInInventory(this.objectId)) {
                gp.playSE(1); // sound effect
                gp.player.addToInventory(getClass(), 1);
                gp.obj.remove(i);
            }
        }
    }
}
