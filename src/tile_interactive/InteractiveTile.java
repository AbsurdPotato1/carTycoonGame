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
    public boolean destructible = false;
    public boolean firstInteract = true;
    public boolean interacting = false;
    public boolean showDescription = false;
    public InteractiveTile(GamePanel gp, int row, int col){
        super(gp, row, col);
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

    public void interactWithTile(int i) {
    }
    public void customInteraction(){}
    public void interactWithInterface(boolean hidePlayer){
        if(isCloseTo(gp.player)){
            if(isClicked()){
                interacting = true; // sets crafting status to true
//                gp.gameState = GamePanel.interactingState;
            }
        }else {
            interacting = false;
//            gp.gameState = GamePanel.interactingState;
        }
        if(interacting){
//            gp.gameState = GamePanel.interactingState;
            if(firstInteract){ // only ran once every time player enters crafting object
                gp.keyH.unPressAll(); // stops ongoing movement/input
            }
            firstInteract = false;
            gp.keyH.acceptMovement = false;
            if(hidePlayer)gp.drawPlayer = false;
            customInteraction();
            if(gp.keyH.escapePressed){
                interacting = false;
                gp.keyH.acceptMovement = true;
                if(hidePlayer)gp.drawPlayer = true;
            }
        }else {
            firstInteract = true; // sets crafting status to false
            showDescription = false;
//            gp.gameState = GamePanel.interactingState;
        }
    }
}
