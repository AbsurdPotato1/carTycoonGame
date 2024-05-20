package tile_interactive;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class copperOreNode extends InteractiveTile{

    public copperOreNode(GamePanel gp, int row, int col){
        super(gp, row, col);
        image = gp.player.getImage("objects/copperOreNode.png"); // refactor in the future to not use gp.player
        destructible = true;
        name = "copperOreNode";
    }
    public void update(){

    }
}
