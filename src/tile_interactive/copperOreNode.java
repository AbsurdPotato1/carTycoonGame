package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class copperOreNode extends InteractiveTile{
    public static final int objectId = 5;
    public static final boolean craftable = false;
    public static final boolean sellable = true;
    public static BufferedImage inventoryImage;

    public copperOreNode(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = UtilityTool.getImage("objects/copperOreNode.png");
        inventoryImage = image;
        destructible = true;
        name = "copperOreNode";
    }
    public void update(){

    }
}
