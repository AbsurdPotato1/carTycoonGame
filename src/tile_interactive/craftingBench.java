package tile_interactive;

import main.GamePanel;
import main.UtilityTool;
import misc.Pair;
import object.IdToObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class craftingBench extends InteractiveTile{
    public boolean crafting = false; // tracks crafting status - whether or not you are crafting
    public boolean beginCrafting = false; // tracks whether or not crafting has begun - used for one time calls then set to true
    public static final int objectId = 4;
    public static final boolean craftable = false; // change back to true later
    public static final boolean sellable = false;
//    public static boolean showDescription = false;
    public static BufferedImage inventoryImage;
    public Class displayCraft;
    public boolean curClicking = false;
    public craftingBench(GamePanel gp, int row, int col){
        super(gp, row, col);
        image = UtilityTool.getImage("objects/craftingBench.png");
        inventoryImage = image;
        destructible = false;
        name = "craftingBench";
    }
    public Class getItemAtSlot(int slotX, int slotY){
        // make a list of all sellables in the future in assetsetter or setupgame(), then just access with % like in UI.inventory. In the future just use 2D array bruh. Refactoring will come later on.
        return IdToObject.craftables[slotX + slotY * 9];
    }
    @Override
    public void customInteraction(){
        if(gp.mouseH.mouseInsideScreen(gp.ui.craftingFrameX + 16, gp.ui.craftingFrameX + gp.ui.craftingWidth - 16, gp.ui.craftingFrameY + 16, gp.ui.craftingFrameY + gp.ui.craftingHeight - 16)){
            int hoverSlotX, hoverSlotY;
            hoverSlotX = (gp.mouseH.mouseScreenX - (gp.ui.craftingFrameX + 16)) / 48;
            hoverSlotY = (gp.mouseH.mouseScreenY - (gp.ui.craftingFrameY + 16)) % 64 <= 48 ? (gp.mouseH.mouseScreenY - (gp.ui.craftingFrameY + 16)) / 48 : -1;
            if(hoverSlotX != -1 && hoverSlotY != -1){
                Class item = getItemAtSlot(hoverSlotX, hoverSlotY); // item clicked
                if(item != null) { // if item is present at that location
                    displayCraft = item;
                    showDescription = true;
                }else{
                    showDescription = false;
                }
            }else{
                showDescription = false;
            }
        }else{
            showDescription = false;
        }
        if(!gp.mouseH.mouseClicked)curClicking = false;
        if(gp.mouseH.mouseClicked && System.nanoTime() - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS)){ // if clicked less than one frame ago
            if(!curClicking) {
                if (gp.mouseH.mouseInsideScreen(gp.ui.craftingFrameX + 16, gp.ui.craftingFrameX + gp.ui.craftingWidth - 16, gp.ui.craftingFrameY + 16, gp.ui.craftingFrameY + gp.ui.craftingHeight - 16)) {
                    // this if statement first does search pruning - optimization
                    // checks if mouse is inside the sell UI area.
                    int clickedSlotX, clickedSlotY;
                    // % 48 <= 48 is to make sure that it is not inside a margin
                    clickedSlotX = (gp.mouseH.mouseScreenX - (gp.ui.craftingFrameX + 16)) / 48; // x has no margins at the moment - will add in the future
                    clickedSlotY = (gp.mouseH.mouseScreenY - (gp.ui.craftingFrameY + 16)) % 64 <= 48 ? (gp.mouseH.mouseScreenY - (gp.ui.craftingFrameY + 16)) / 48 : -1;

                    if (clickedSlotX != -1 && clickedSlotY != -1) { // if not in margin
                        Class item = getItemAtSlot(clickedSlotX, clickedSlotY); // item clicked
                        if (item != null) { // if item is present at that location
                            boolean hasAllItems = true;
                            int objId = IdToObject.getIdFromClass(item);
                            HashMap<Integer, Integer> recipe = (HashMap<Integer, Integer>)IdToObject.getStaticVariable(objId, "craftingRecipe");
                            for(Integer recipeObjId : recipe.keySet()){
                                if(gp.player.invHasItems(recipeObjId, recipe.get(recipeObjId))){ // if player does not have enough objs
                                    hasAllItems = false;
                                    break;
                                }
                            }
                            if (hasAllItems) { // if player has all items
                                for(Integer recipeObjId : recipe.keySet()){
                                    gp.player.addToInventory(IdToObject.getObjectFromId(recipeObjId), -recipe.get(recipeObjId)); // remove recipe items from player.
                                }
                                gp.player.addToInventory(item, 1);
                            }
                        }
                    }
                }
                curClicking = true;
            }
        }
    }
    public void update(){
        interactWithInterface(true);
    }
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(interacting){
            gp.ui.drawCraftScreen(g2);
        }
        if(showDescription){
            gp.ui.showCraftDescription(g2, displayCraft);
        }
    }

}
