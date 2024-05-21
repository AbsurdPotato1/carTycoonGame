package tile_interactive;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;

public class copperOreNode extends InteractiveTile{

    public copperOreNode(GamePanel gp, int x, int y){
        super(gp, x, y);
        image = UtilityTool.getImage("objects/copperOreNode.png");
        destructible = true;
        name = "copperOreNode";
    }
    public void update(){

    }
}
