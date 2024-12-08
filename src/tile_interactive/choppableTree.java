package tile_interactive;

import main.GamePanel;
import main.UtilityTool;
import object.IdToObject;
import object.ObjectCopperOre;
import object.ObjectStick;
import object.ToolPickaxe;

import java.awt.image.BufferedImage;

public class choppableTree extends InteractiveTile{
    public static final Integer objectId = 6;
     public static final boolean craftable = false;
     public static final boolean sellable = false;
     public static BufferedImage inventoryImage;
     public boolean destructible = true;
    public choppableTree(GamePanel gp, int row, int col) {
        super(gp, row, col);
        image = UtilityTool.getImage("objects/tree.png");
        inventoryImage = image;
    }
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
            gp.obj.add(new ObjectStick(gp, worldX, worldY)); // drop 3 stick
            gp.obj.add(new ObjectStick(gp, worldX, worldY));
            gp.obj.add(new ObjectStick(gp, worldX, worldY));

            gp.iTile.remove(i); // remove from existence -- boom
        }
    }

}
