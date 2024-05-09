package object;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
           worldX < gp.player.worldX + gp.screenWidth - gp.player.screenX &&
           worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
           worldY < gp.player.worldY + gp.screenHeight - gp.player.screenY){
           g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
