package tile_interactive;

import main.GamePanel;
import main.UtilityTool;
import object.IdToObject;
import object.ObjectCopperOre;
import object.ToolPickaxe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class copperOreNode extends InteractiveTile{
    public static final int objectId = 5;
    public static final boolean craftable = false;
    public static final boolean sellable = true;
    public static int sellPrice = 2;
    public static String sellDescription = "Sells for $" + sellPrice;
    public static BufferedImage inventoryImage;

    public copperOreNode(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = UtilityTool.getImage("objects/copperOreNode.png");
        inventoryImage = image;
        destructible = true;
        name = "copperOreNode";
    }
    @Override
    public void update(){

    }
    @Override
    public void interactWithTile(int i){
        long currentTime = System.nanoTime();
        if ((currentTime - gp.player.lastMineTime) / (1000000000 / gp.FPS) >= gp.player.mineInterval && // gp.FPS should be changed to 60
                currentTime - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS) &&
                gp.ui.hotbarCol < gp.player.inventory.length && gp.player.currentlyHolding(gp.ui.hotbarCol) == IdToObject.getIdFromClass(ToolPickaxe.class) &&
                isCloseTo(gp.player) && isClicked()) { // if holding pickaxe and adequate conditions met.
            gp.player.lastMineTime = currentTime;
            gp.obj.add(new ObjectCopperOre(gp, worldX, worldY)); // drop copper ore
            gp.iTile.remove(i); // remove from existence -- boom
        }
    }
}
