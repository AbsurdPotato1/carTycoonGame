package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class craftingBench extends InteractiveTile{
    public boolean crafting = false; // tracks crafting status - whether or not you are crafting
    public boolean beginCrafting = false; // tracks whether or not crafting has begun - used for one time calls then set to true
    public static final int objectId = 4;
    public static final boolean  craftable = true;
    public static final boolean sellable = false;
    public static BufferedImage inventoryImage;
    public craftingBench(GamePanel gp, int row, int col){
        super(gp, row, col);
        image = UtilityTool.getImage("objects/craftingBench.png");
        inventoryImage = image;
        destructible = false;
        name = "craftingBench";
    }
    public void update(){
        if(isCloseTo(gp.player)){
            if(isClicked()){
                crafting = true; // sets crafting status to true
            }
        }else {
            crafting = false;
        }
        if(crafting){
            if(!beginCrafting){ // only ran once every time player enters crafting object
                gp.keyH.unPressAll(); // stops ongoing movement/input
            }
            beginCrafting = true;
            gp.keyH.acceptMovement = false;
            gp.drawPlayer = false;
            if(gp.keyH.escapePressed){
                crafting = false;
                gp.keyH.acceptMovement = false;
                gp.drawPlayer = true;
            }
        }else {
            beginCrafting = false; // sets crafting status to false
        }
    }
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(crafting){
            gp.ui.drawCraftingScreen(g2);
        }
    }

}
