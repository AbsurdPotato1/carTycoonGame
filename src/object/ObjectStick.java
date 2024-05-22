package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.image.BufferedImage;

public class ObjectStick extends SuperObject {
     public static final Integer objectId = 7;
     public static final boolean craftable = false;
     public static final boolean sellable = true;
     public static int sellPrice = 1;
    public static String sellDescription = "Sells for $" + sellPrice;
     public static BufferedImage inventoryImage;
    public ObjectStick(GamePanel gp, int x, int y) {
        super(gp, x, y);
        image = UtilityTool.getImage("objects/stick.png");
        inventoryImage = image;
        name = "stick";
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
