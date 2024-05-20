package tile_interactive;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class craftingBench extends InteractiveTile{

    public craftingBench(GamePanel gp, int row, int col){
        super(gp, row, col);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/craftingbench.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        destructible = true;
    }

}
