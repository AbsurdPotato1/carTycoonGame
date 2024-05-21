package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.SuperObject;

public class InteractiveTile extends SuperObject { // WHEN CREATING A NEW SUPEROBJECT OR INTERACTIVE TILE MAKE SURE TO ASSIGN THESE:
    // public static final Integer objectId = 1;
    // public static final boolean craftable = someValue;
    // public static final boolean sellable = someValue;
    // public static BufferedImage inventoryImage; <-- assigned in constructor
    // increase IdToObject numObjs
    // Place your object's class in IdToObject's idObject class
    GamePanel gp;
    public boolean destructible = false;
    public InteractiveTile(GamePanel gp, int row, int col){
        super(gp, row, col);
        this.gp = gp;
    }
    public void update(){

    }
    public boolean isClicked() {
        if(gp.mouseH.mouseClicked){
            if(gp.mouseH.mouseWorldX >= (worldX) && gp.mouseH.mouseWorldX <= (worldX + solidArea.x + solidArea.width) &&
                    gp.mouseH.mouseWorldY >= (worldY) && gp.mouseH.mouseWorldY <= (worldY + solidArea.y + solidArea.height)){
                return true;
            }
        }
        return false;
    }

    public boolean isCloseTo(Entity entity){
        return Math.abs(entity.worldX - worldX) <= 96 &&
                Math.abs(entity.worldY - worldY) <= 96;
    }

}
