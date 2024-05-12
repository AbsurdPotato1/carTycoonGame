package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

public class ObjectCopperOre extends SuperObject {
    public ObjectCopperOre(){
        collisionWidth = 22;
        collisionHeight = 19;
        objectWidth = 22;
        objectHeight = 19;
        objectXOffset = 13;
        objectYOffset = 14;
        solidArea = new Rectangle(objectXOffset, objectYOffset, collisionWidth, collisionHeight);
        name = "copperOre";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/copperOre22x19.png"));
        } catch(IOException e){
            e.printStackTrace();
        }


//        solidAreaDefaultY = 0;
    }
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX < gp.player.worldX + gp.screenWidth - gp.player.screenX &&
                worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY < gp.player.worldY + gp.screenHeight - gp.player.screenY){
            g2.drawImage(image, screenX + objectXOffset, screenY + objectYOffset, objectWidth, objectHeight, null);
        }
    }
}
