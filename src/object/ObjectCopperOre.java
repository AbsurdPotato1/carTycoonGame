package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectCopperOre extends SuperObject {
//    public static final String name = "copperOre";
    public static final int objectId = 2;
    public static BufferedImage inventoryImage;
    public ObjectCopperOre(GamePanel gp, int x, int y){
        super(gp, x, y);
        collisionWidth = 22;
        collisionHeight = 19;
        objectWidth = 48;
        objectHeight = 48;
        collisionXOffset = 14;
        collisionYOffset = 17;
//        objectId = 0;
        solidArea = new Rectangle(collisionXOffset, collisionYOffset, collisionWidth, collisionHeight);
        name = "copperOre";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/copperOreNew.png")); // image provides the location where the object is drawn.
            inventoryImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/copperOreNew.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        description = "Copper Ore\nUsed to make things.";


//        solidAreaDefaultY = 0;
    }
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - (int)gp.player.worldX + gp.player.screenX;
        int screenY = worldY - (int)gp.player.worldY + gp.player.screenY;

        if(worldX > gp.player.worldX - gp.player.screenX - GamePanel.tileSize &&
                worldX < gp.player.worldX + gp.screenWidth - gp.player.screenX &&
                worldY > gp.player.worldY - gp.player.screenY - GamePanel.tileSize &&
                worldY < gp.player.worldY + gp.screenHeight - gp.player.screenY){
            g2.drawImage(image, screenX, screenY, objectWidth, objectHeight, null);
        }
    }
}
