package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectChest extends SuperObject {
//    public static final String name = "chest";
    public static final Integer objectId = 1;
    public static BufferedImage inventoryImage;
    public static boolean sellable = true;
    public ObjectChest(GamePanel gp, int row, int col){
        super(gp, row, col);
//        objectId = 1;
        name = "chest";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("blocks/chest.png"));
            inventoryImage = image;
        } catch(IOException e){
            e.printStackTrace();
        }
        description = "Chest\nHolds a couple things";
        collision = true;
    }
}
