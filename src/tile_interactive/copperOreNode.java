package tile_interactive;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class copperOreNode extends InteractiveTile{

    public copperOreNode(GamePanel gp, int row, int col){
        super(gp, row, col);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/copperOreNode.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        destructible = true;
    }

}
