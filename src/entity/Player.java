package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX, screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 8 , gp.tileSize - 16, gp.tileSize - 8); //436 /4

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 50;
        worldY = 500;
        speedHor = 2 * 60.0 / gp.FPS;
        speedVert = 2 * 60.0 / gp.FPS;
    }

    public void getPlayerImage(){
        try {
            car1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void snapPlayerLoc() {
        if (downCollisionOn) {
            // Snap player to the nearest tile below
            int playerBottomY = worldY + solidArea.y + solidArea.height; // Calculate the bottom Y-coordinate of the player
            int nearestTileBelowY = ((playerBottomY + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile below

            // Calculate the distance to the nearest tile above
            int nearestTileAboveY = nearestTileBelowY - gp.tileSize;
            int distToTileAbove = worldY + solidArea.y + solidArea.height - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - (worldY + solidArea.height);

            // Snap to the nearest tile (above or below)
            if(distToTileAbove < 6){
                worldY = (nearestTileAboveY + 6) / gp.tileSize * gp.tileSize - solidArea.height;
            }else if(distToTileBelow < 6){
                worldY = (nearestTileBelowY + 6) / gp.tileSize * gp.tileSize - solidArea.height;;
            }
        }
        if (upCollisionOn) {
            // Snap player to the nearest tile below
            int playerTopY = worldY; // Calculate the bottom Y-coordinate of the player
            int nearestTileAboveY = ((playerTopY) / gp.tileSize) * gp.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileBelowY = nearestTileAboveY + gp.tileSize;
            int distToTileAbove = worldY - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - worldY;

            // Snap to the nearest tile (above or below)
            if(distToTileAbove < 6){
                worldY = (nearestTileAboveY + 6) / gp.tileSize * gp.tileSize;
            }else if(distToTileBelow < 6){
                worldY = (nearestTileBelowY + 6) / gp.tileSize * gp.tileSize;
            }
        }

        if (rightCollisionOn) {
            // Snap player to the nearest tile below
            int playerRightX = worldX + solidArea.x + solidArea.width; // Calculate the right X-coordinate of the player
            int nearestTileRightX = ((playerRightX + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile right

            // Calculate the distance to the nearest tile above
            int nearestTileLeftX = nearestTileRightX - gp.tileSize;
            int distToTileLeft = worldX + solidArea.x + solidArea.width - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - (worldX + solidArea.width);

            // Snap to the nearest tile (above or below)
            if(distToTileLeft < 6){
                worldX = (nearestTileLeftX + 6) / gp.tileSize * gp.tileSize - solidArea.width;
            }else if(distToTileRight < 6){
                worldX = (nearestTileRightX + 6) / gp.tileSize * gp.tileSize - solidArea.width;
            }
        }

        if (leftCollisionOn) {
            // Snap player to the nearest tile below
            int playerLeftX = worldX; // Calculate the left X-coordinate of the player
            int nearestTileLeftX = ((playerLeftX) / gp.tileSize) * gp.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileRightX = nearestTileLeftX + gp.tileSize;
            int distToTileLeft = worldX - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - worldX;

            // Snap to the nearest tile (above or below)
            if(distToTileLeft < 6){
                worldX = (nearestTileLeftX + 6) / gp.tileSize * gp.tileSize;
            }else if(distToTileRight < 6){
                worldX = (nearestTileRightX + 6) / gp.tileSize * gp.tileSize;
            }
        }
    }
    public void update(){
        if(keyH.jumpPressed){
            direction[0] = true;
        }else{
            direction[0] = false;
        }
        if(keyH.rightPressed){
            direction[1] = true;
        }else{
            direction[1] = false;
        }
        if(keyH.downPressed){
            direction[2] = true;
        }else{
            direction[2] = false;
        }
        if(keyH.leftPressed){
            direction[3] = true;
        }else{
            direction[3] = false;
        }

        upCollisionOn = false;
        rightCollisionOn = false;
        downCollisionOn = false;
        leftCollisionOn = false;
        gp.cChecker.checkTile(this);
        // IF COLLISION IS FALSE, PLAYER CAN MOVE


        System.out.println("X: " + worldX + ", Y: " + worldY);
//        System.out.print("UP: " + upCollisionOn);
//        System.out.print(", RIGHT: " + rightCollisionOn);
//        System.out.print(", DOWN: " + downCollisionOn);
//        System.out.println(", LEFT: " + leftCollisionOn);
        if(!upCollisionOn) {
            if (keyH.jumpPressed) {
                worldY -= speedVert;
            }
        }
        if(!rightCollisionOn) {
            if (keyH.rightPressed) {
                worldX += speedHor;
            }
        }
        if(!downCollisionOn){
            if (keyH.downPressed) {
                worldY += speedVert;
            }
        }
        if(!leftCollisionOn) {
            if (keyH.leftPressed) {
                worldX -= speedHor;
//                System.out.println("HI");
            }
        }
        gp.cChecker.checkTile(this);
        snapPlayerLoc();
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        image = car1;
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }


}
