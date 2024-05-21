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
