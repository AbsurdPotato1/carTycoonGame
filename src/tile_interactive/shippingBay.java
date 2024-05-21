package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

public class shippingBay extends InteractiveTile {
    public shippingBay(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = UtilityTool.getImage("blocks/shippingBay.png");
        destructible = false;
        name = "shippingBay";
    }
    public void update(){

    }
}
