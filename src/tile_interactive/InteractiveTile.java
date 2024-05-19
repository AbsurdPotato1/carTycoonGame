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
    public boolean isClicked() {
        if(gp.mouseH.mouseClicked){
            if(gp.mouseH.mouseX >= (this.worldX + solidArea.x) && gp.mouseH.mouseX <= (this.worldX + solidArea.x + solidArea.width) &&
                gp.mouseH.mouseY >= (this.worldY + solidArea.y) && gp.mouseH.mouseY <= (this.worldY + solidArea.y + solidArea.height )) { // Checks that mouseclick is inside
                return true;
            }
        }
        return false;
    }
}
