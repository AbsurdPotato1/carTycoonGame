package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ToolPickaxe extends SuperObject {
    public static final int objectId = 0;
    public static final boolean craftable = true;
    public static final boolean sellable = true;
    public static int sellPrice = 10;
    public static String sellDescription = "Sells for $" + sellPrice;
    public static BufferedImage inventoryImage;
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
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tools/pickaxe.png")); // image provides the location where the object is drawn.
            inventoryImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tools/pickaxe.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        name = "pickaxe";
    }
}
