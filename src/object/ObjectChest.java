package object;

import main.GamePanel;
import main.UtilityTool;
import tile_interactive.InteractiveTile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ObjectChest extends InteractiveTile {
    public static final Integer objectId = 1;
    public static final boolean craftable = true; // change back to true later
    public static final boolean sellable = true;
    public static int sellPrice = 3;
    public static String sellDescription = "Sells for $" + sellPrice;
    public static String description = "Chest: holds a couple things";
    public static BufferedImage inventoryImage;
    public static HashMap<Integer, Integer> craftingRecipe = new HashMap<>();
    public boolean opened = false;
    public boolean beginInteract = false;
    public int displayItemId;

    private int[][] inventory = new int[27][2]; // each element is (objId, amount)

    public ObjectChest(GamePanel gp, int row, int col){
        super(gp, row, col);
        name = "chest";
        image = UtilityTool.getImage("blocks/chest.png");
        inventoryImage = image;
        collision = true;
        setCraftingRecipe();
    }
    public void setCraftingRecipe(){
        craftingRecipe.put(IdToObject.getIdFromClass(ObjectStick.class), 10);
        inventory[0] = new int[]{0, 1};
    }
    @Override
    public void customInteraction(){
        if(gp.mouseH.mouseInsideScreen(gp.ui.chestFrameX + 16, gp.ui.chestFrameX + gp.ui.chestWidth - 16, gp.ui.chestFrameY + 16, gp.ui.chestFrameY + gp.ui.chestHeight - 16)) {
            int hoverSlotX, hoverSlotY;
            hoverSlotX = (gp.mouseH.mouseScreenX - (gp.ui.chestFrameX + 16)) / 48;
            hoverSlotY = (gp.mouseH.mouseScreenY - (gp.ui.chestFrameY + 16)) % 64 <= 48 ? (gp.mouseH.mouseScreenY - (gp.ui.chestFrameY + 16)) / 48 : -1;
            if (hoverSlotX != -1 && hoverSlotY != -1 && hoverSlotX + hoverSlotY * 9 < 27) {
                int[] item = inventory[hoverSlotX + hoverSlotY * 9];
                if (item[1] != 0) {
                    displayItemId = item[0];
                    showDescription = true;
                } else {
                    showDescription = false;
                }
            } else {
                showDescription = false;
            }
        }else {
            showDescription = false;
        }
//        if(gp.mouseH.mouseInsideScreen())
//        if(!gp.mouseH.mouseClicked)curClicking = false;
//        if(gp.mouseH.mouseClicked && System.nanoTime() - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS)){ // if clicked less than one frame ago
//            if(!curClicking) {
//                if (gp.mouseH.mouseInsideScreen(gp.ui.craftingFrameX + 16, gp.ui.craftingFrameX + gp.ui.craftingWidth - 16, gp.ui.craftingFrameY + 16, gp.ui.craftingFrameY + gp.ui.craftingHeight - 16)) {
//                    // this if statement first does search pruning - optimization
//                    // checks if mouse is inside the sell UI area.
//                    int clickedSlotX, clickedSlotY;
//                    // % 48 <= 48 is to make sure that it is not inside a margin
//                    clickedSlotX = (gp.mouseH.mouseScreenX - (gp.ui.craftingFrameX + 16)) / 48; // x has no margins at the moment - will add in the future
//                    clickedSlotY = (gp.mouseH.mouseScreenY - (gp.ui.craftingFrameY + 16)) % 64 <= 48 ? (gp.mouseH.mouseScreenY - (gp.ui.craftingFrameY + 16)) / 48 : -1;
//
//                    if (clickedSlotX != -1 && clickedSlotY != -1) { // if not in margin
//                        Class item = getItemAtSlot(clickedSlotX, clickedSlotY); // item clicked
//                        if (item != null) { // if item is present at that location
//                            boolean hasAllItems = true;
//                            int objId = IdToObject.getIdFromClass(item);
//                            HashMap<Integer, Integer> recipe = (HashMap<Integer, Integer>)IdToObject.getStaticVariable(objId, "craftingRecipe");
//                            for(Integer recipeObjId : recipe.keySet()){
//                                if(gp.player.inventory.getOrDefault(recipeObjId, 0) < recipe.get(recipeObjId)){ // if player does not have enough npcs
//                                    hasAllItems = false;
//                                }
//                            }
//                            if (hasAllItems) { // if player has all items
//                                for(Integer recipeObjId : recipe.keySet()){
//                                    gp.player.addToInventory(IdToObject.getObjectFromId(recipeObjId), -recipe.get(recipeObjId)); // remove recipe items from player.
//                                }
//                                gp.player.addToInventory(item, 1);
//                            }
//                        }
//                    }
//                }
//                curClicking = true;
//            }
    }
    public void update(){
        interactWithInterface(true);
    }
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(interacting)gp.ui.drawChestScreen(g2, inventory);
        if(showDescription)gp.ui.showItemDescription(g2, IdToObject.getObjectFromId(displayItemId));
    }
}
