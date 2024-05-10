package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public double speedVert, speedHor;
//    public double curUpSpeed = 0;
//    public String direction = "up";
//    public boolean jumping = false;
    public boolean[] direction = new boolean[4]; // goes clockwise from the top: 0 - up, 1 - right, 2 - down, 3 - left
    public int accel;
//    public final double gravity = 0.25 * 60 / 60;
    public BufferedImage car1;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea; // solidArea.x and solidArea.y may be modified TEMPORARILY in CollisionChecker -- thus solidAreaDefaultX and Y exist
    public int solidAreaDefaultX, solidAreaDefaultY; // the normal positions of solidArea
    public boolean upCollisionOn = false;
    public boolean rightCollisionOn = false;
    public boolean downCollisionOn = false;
    public boolean leftCollisionOn = false;

}
