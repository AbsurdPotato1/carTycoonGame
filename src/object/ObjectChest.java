package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ObjectChest extends SuperObject {
    public static final Integer objectId = 1;
    public static final boolean craftable = true; // change back to true later
    public static final boolean sellable = true;
    public static int sellPrice = 3;
    public static String sellDescription = "Sells for $" + sellPrice;
    public static BufferedImage inventoryImage;
    public static HashMap<Integer, Integer> craftingRecipe = new HashMap<>();
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
        setCraftingRecipe();
    }
    public void setCraftingRecipe(){
        craftingRecipe.put(IdToObject.getIdFromClass(ObjectStick.class), 10);
    }
    public void update(){
//        if(isCloseTo(gp.player)){
//            if(isClicked()){
//                crafting = true; // sets crafting status to true
////                gp.gameState = GamePanel.interactingState;
//            }
//        }else {
//            crafting = false;
////            gp.gameState = GamePanel.interactingState;
//        }
//        if(crafting){
////            gp.gameState = GamePanel.interactingState;
//            if(!beginCrafting){ // only ran once every time player enters crafting object
//                gp.keyH.unPressAll(); // stops ongoing movement/input
//            }
//            beginCrafting = true;
//            gp.keyH.acceptMovement = false;
//            gp.drawPlayer = false;
//            doCrafting();
//            if(gp.keyH.escapePressed){
//                crafting = false;
//                gp.keyH.acceptMovement = true;
//                gp.drawPlayer = true;
//            }
//        }else {
//            beginCrafting = false; // sets crafting status to false
//            showDescription = false;
////            gp.gameState = GamePanel.interactingState;
//        }
    }
    public void draw(Graphics2D g2){
        super.draw(g2);
    }
}
