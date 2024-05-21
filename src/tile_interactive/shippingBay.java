package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;

public class shippingBay extends InteractiveTile {
    public boolean selling = false;
    public boolean beginSelling = false;
    public shippingBay(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = UtilityTool.getImage("blocks/shippingBay.png");
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
