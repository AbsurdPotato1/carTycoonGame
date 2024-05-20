package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ToolPickaxe extends SuperTool {
    public static final int objectId = 0;
    public static BufferedImage inventoryImage;
    public ToolPickaxe(GamePanel gp, int row, int col) {
        super(gp, row, col);
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
