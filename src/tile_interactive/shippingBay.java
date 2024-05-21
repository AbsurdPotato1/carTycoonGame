package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class shippingBay extends InteractiveTile {
    public boolean selling = false; // tracks selling status - whether or not you are selling
    public boolean beginSelling = false; // tracks whether or not selling has begun - used for one time calls then set to true
    public static final int objectId = 3;
    public static final boolean craftable = false;
    public static final boolean sellable = false;
    public static BufferedImage inventoryImage;
    public shippingBay(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = UtilityTool.getImage("blocks/shippingBay.png");
        inventoryImage = image;
        destructible = false;
        name = "shippingBay";
    }

    public void getItemAtSlot(int slotX, int slotY){
        // make a list of all sellables in the future in assetsetter or setupgame(), then just access with % like in UI.inventory. In the future just use 2D array bruh. Refactoring will come later on.
    }

    public void doSales(){
        if(gp.mouseH.mouseClicked && System.nanoTime() - gp.mouseH.timeClicked <= 1 * (1000000000 / gp.FPS)){ // if clicked less than one frame ago
            if(gp.mouseH.mouseInsideScreen(gp.ui.sellFrameX, gp.ui.sellFrameX + gp.ui.sellWidth, gp.ui.sellFrameY, gp.ui.sellFrameY + gp.ui.sellHeight)){
                // this if statement first does search pruning - optimization
                // checks if mouse is inside the sell UI area.
                int clickedSlotX, clickedSlotY;
                // % 48 <= 48 is to make sure that it is not inside a margin
                clickedSlotX = (gp.mouseH.mouseScreenX - (gp.ui.sellFrameX + 16)) % 64 <= 48 ? (gp.mouseH.mouseScreenX - (gp.ui.sellFrameX + 16)) / 48 : -1;
                clickedSlotY = (gp.mouseH.mouseScreenY - (gp.ui.sellFrameY + 16)) % 64 <= 48 ? (gp.mouseH.mouseScreenY - (gp.ui.sellFrameY + 16)) / 48 : -1;
                if(clickedSlotX != -1 && clickedSlotY != -1){

                }
            }
        }
    }

    public void update(){
        if(isCloseTo(gp.player)){
            if(isClicked()) {
                selling = true; // sets selling status to true
            }
        }else {
            selling = false;
        }
        if(selling){
            if(!beginSelling) { // only ran once every time player enters object
                gp.keyH.unPressAll(); // stops ongoing movement/input
            }
            beginSelling = true;
            gp.keyH.acceptMovement = false;
            gp.drawPlayer = false;
            doSales();
            if(gp.keyH.escapePressed){
                selling = false;
                gp.keyH.acceptMovement = true;
                gp.drawPlayer = true;
            }
        }else{
            beginSelling = false; // sets selling status to false
        }
    }
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(selling){
            gp.ui.drawSellScreen(g2);
        }
    }
}
