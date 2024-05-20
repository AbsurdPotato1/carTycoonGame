package tile_interactive;

import main.GamePanel;

public class shippingBay extends InteractiveTile {
    public shippingBay(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = gp.player.getImage("blocks/shippingBay.png"); // refactor in the future to not use gp.player
        destructible = false;
        name = "shippingBay";
    }
    public void update(){

    }
}
