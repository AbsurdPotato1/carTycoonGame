package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.SuperObject;

public class InteractiveTile extends SuperObject {
    GamePanel gp;
    public boolean destructible = false;
    public InteractiveTile(GamePanel gp, int row, int col){
        super(gp, row, col);
        this.gp = gp;
    }
    public void update(){

    }
}
