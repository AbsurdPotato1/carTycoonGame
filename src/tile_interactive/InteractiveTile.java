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
        int screenX = worldX - (int)gp.player.worldX + gp.player.screenX;
        int screenY = worldY - (int)gp.player.worldY + gp.player.screenY;
        if(gp.mouseH.mouseClicked){
            if(gp.mouseH.mouseX >= (screenX) && gp.mouseH.mouseX <= (screenX + solidArea.x + solidArea.width) &&
                    gp.mouseH.mouseY >= (screenY) && gp.mouseH.mouseY <= (screenY + solidArea.y + solidArea.height)){
                return true;
            }
        }
        return false;
    }

    public boolean isCloseTo(Entity entity){
        return Math.abs(entity.worldX - worldX) <= 96 &&
                Math.abs(entity.worldY - worldY) <= 96;
    }

}
