package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.IOException;

public class craftingBench extends InteractiveTile{
    public boolean crafting = false;
    public boolean beginCrafting = false;
    public craftingBench(GamePanel gp, int row, int col){
        super(gp, row, col);
        image = UtilityTool.getImage("objects/craftingbench.png");
        destructible = false;
        name = "craftingbench";
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
