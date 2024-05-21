package entity;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public double speedVert, speedHor;
//    public double curUpSpeed = 0;
//    public String direction = "up";
//    public boolean jumping = false;
    public boolean[] direction = new boolean[4]; // goes clockwise from the top: 0 - up, 1 - right, 2 - down, 3 - left
    public int accel;
    public BufferedImage up1, right1, down1, left1;
    public BufferedImage attackUp1, attackUp2, attackRight1, attackRight2,
            attackDown1, attackDown2, attackLeft1, attackLeft2;
    //    public final double gravity = 0.25 * 60 / 60;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // solidArea.x and solidArea.y may be modified TEMPORARILY in CollisionChecker -- thus solidAreaDefaultX and Y exist
    public int solidAreaDefaultX, solidAreaDefaultY; // the normal positions of solidArea
    public boolean upCollisionOn = false;
    public boolean rightCollisionOn = false;
    public boolean downCollisionOn = false;
    public boolean leftCollisionOn = false;
    public int actionLockCounter = 0;
    public boolean attacking = false;


    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public BufferedImage getImage(String imagePath){
        try {
            return ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setAction(){}

    public void update(){
        setAction();
        upCollisionOn = false;
        rightCollisionOn = false;
        downCollisionOn = false;
        leftCollisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        if(!upCollisionOn) {
            if (direction[0]) {
                worldY -= speedVert;
            }
        }
        if(!rightCollisionOn) {
            if (direction[1]) {
                worldX += speedHor;
            }
        }
        if(!downCollisionOn){
            if (direction[2]) {
                worldY += speedVert;
            }
        }
        if(!leftCollisionOn) {
            if (direction[3]) {
                worldX -= speedHor;
            }
        }
    }

    public boolean isCloseTo(Entity entity){
        return Math.abs(entity.worldX - worldX) <= 96 &&
                Math.abs(entity.worldY - worldY) <= 96;
    }
    public boolean isCloseTo(InteractiveTile it){
        return Math.abs(it.worldX - worldX) <= 96 &&
                Math.abs(it.worldY - worldY) <= 96;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if(direction[1])image = right1;
        else if(direction[3])image = left1;
        else if(direction[0])image = up1;
        else if(direction[2])image = down1;
        else image = down1;
//        image = down1;
        int screenX = worldX - (int)gp.player.worldX + gp.player.screenX;
        int screenY = worldY - (int)gp.player.worldY + gp.player.screenY;

        if(worldX > gp.player.worldX - gp.player.screenX - GamePanel.tileSize &&
                worldX < gp.player.worldX + gp.screenWidth - gp.player.screenX &&
                worldY > gp.player.worldY - gp.player.screenY - GamePanel.tileSize &&
                worldY < gp.player.worldY + gp.screenHeight - gp.player.screenY){
            g2.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }

}
