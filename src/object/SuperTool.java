package object;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperTool {
    GamePanel gp;
    public BufferedImage image;
    public BufferedImage inventoryImage; // only exists for readibility (every subclass has a public static BufferedImage inventoryImage)
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int collisionWidth, collisionHeight; // width and height of collision hitbox
    public int objectWidth, objectHeight; // width and height of object image (often same as collisionWidth and collisionHeight)
    public int collisionXOffset = 0; // horizontal offset into the tile for an object - usually 0
    public int collisionYOffset = 0; // vertical offset into the tile for an object
    public String description = "";
    public int objectId;

    public SuperTool(GamePanel gp, int x, int y){
        this.gp = gp;
        worldX = x;
        worldY = y;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - (int) gp.player.worldX + gp.player.screenX;
        int screenY = worldY - (int) gp.player.worldY + gp.player.screenY;

        if (worldX > gp.player.worldX - gp.player.screenX - GamePanel.tileSize &&
                worldX < gp.player.worldX + gp.screenWidth - gp.player.screenX &&
                worldY > gp.player.worldY - gp.player.screenY - GamePanel.tileSize &&
                worldY < gp.player.worldY + gp.screenHeight - gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }
}
