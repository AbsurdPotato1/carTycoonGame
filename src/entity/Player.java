package entity;

import main.GamePanel;
import main.KeyHandler;
import object.ObjectCopperOre;
import object.SuperObject;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX, screenY;
    public int numCopper = 0; // change to inventory in future
    long lastPickUpTime = System.nanoTime();
    public HashMap<SuperObject, Integer> inventory = new HashMap<>();
//    public int[] inventory = new int[255]; // size is number of objects.
//    public ArrayList<SuperObject> inventory = new ArrayList<>();
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 8 , gp.tileSize - 16, gp.tileSize - 8); // sets hitbox - 8 pixels from left, right, and top of sprite, i.e. hitbox is 32x40
//        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize); // use for testing
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
        setItems();
    }
    public void setDefaultValues(){
        worldX = 50;
        worldY = 500;
        speedHor = 2 * 60.0 / gp.FPS;
        speedVert = 2 * 60.0 / gp.FPS;
    }
    public void setItems(){
        inventory.merge(new ObjectCopperOre(), 1, Integer::sum); // increments key (count) of value (SuperObject)
        inventory.merge(new ObjectCopperOre(), 1, Integer::sum); // increments key (count) of value (SuperObject)
        inventory.merge(new ObjectCopperOre(), 1, Integer::sum); // increments key (count) of value (SuperObject)

    }

    public void getPlayerImage(){
        image1 = getImage("player/boy_down_1.png");
    }
    public void snapPlayerLoc() {
        if (downCollisionOn) {
            // Snap player to the nearest tile below
            int playerBottomY = (int)worldY + solidArea.y + solidArea.height; // Calculate the bottom Y-coordinate of the player
            int nearestTileBelowY = ((playerBottomY + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile below

            // Calculate the distance to the nearest tile above
            int nearestTileAboveY = nearestTileBelowY - gp.tileSize;
            int distToTileAbove = (int)worldY + solidArea.y + solidArea.height - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - ((int)worldY + solidArea.height);

            // Snap to the nearest tile (above or below)
            if(distToTileAbove < 6){
                worldY = (nearestTileAboveY + 6) / gp.tileSize * gp.tileSize - solidArea.height;
            }else if(distToTileBelow < 6){
                worldY = (nearestTileBelowY + 6) / gp.tileSize * gp.tileSize - solidArea.height;;
            }
        }
        if (upCollisionOn) {
            // Snap player to the nearest tile below
            int playerTopY = (int)worldY; // Calculate the bottom Y-coordinate of the player
            int nearestTileAboveY = ((playerTopY) / gp.tileSize) * gp.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileBelowY = nearestTileAboveY + gp.tileSize;
            int distToTileAbove = (int)worldY - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - (int)worldY;

            // Snap to the nearest tile (above or below)
            if(distToTileAbove < 6){
                worldY = (nearestTileAboveY + 6) / gp.tileSize * gp.tileSize;
            }else if(distToTileBelow < 6){
                worldY = (nearestTileBelowY + 6) / gp.tileSize * gp.tileSize;
            }
        }

        if (rightCollisionOn) {
            // Snap player to the nearest tile below
            int playerRightX = (int)worldX + solidArea.x + solidArea.width; // Calculate the right X-coordinate of the player
            int nearestTileRightX = ((playerRightX + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile right

            // Calculate the distance to the nearest tile above
            int nearestTileLeftX = nearestTileRightX - gp.tileSize;
            int distToTileLeft = (int)worldX + solidArea.x + solidArea.width - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - ((int)worldX + solidArea.width);

            // Snap to the nearest tile (above or below)
            if(distToTileLeft < 6){
                worldX = (nearestTileLeftX + 6) / gp.tileSize * gp.tileSize - solidArea.width;
            }else if(distToTileRight < 6){
                worldX = (nearestTileRightX + 6) / gp.tileSize * gp.tileSize - solidArea.width;
            }
        }

        if (leftCollisionOn) {
            // Snap player to the nearest tile below
            int playerLeftX = (int)worldX; // Calculate the left X-coordinate of the player
            int nearestTileLeftX = ((playerLeftX) / gp.tileSize) * gp.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileRightX = nearestTileLeftX + gp.tileSize;
            int distToTileLeft = (int)worldX - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - (int)worldX;

            // Snap to the nearest tile (above or below)
            if(distToTileLeft < 6){
                worldX = (nearestTileLeftX + 6) / gp.tileSize * gp.tileSize;
            }else if(distToTileRight < 6){
                worldX = (nearestTileRightX + 6) / gp.tileSize * gp.tileSize;
            }
        }
    }
    public void pickUpObject(int i){
        long currentTime;
        currentTime = System.nanoTime();
        long pickUpInterval = 5;
        if((currentTime - lastPickUpTime) / (1000000000 / gp.FPS) >= pickUpInterval){ // last pickup >= 5 frames ago? -- TODO: seems to be going 6 frames (10 items / sec)
            lastPickUpTime = currentTime;
            if(i != 99999){

                String objectName = gp.obj[i].name;

                switch(objectName){
                    case "copperOre":
                        gp.playSE(1); // sound effect
                        numCopper++;
                        gp.obj[i] = null;
                        gp.ui.showMessage("You got a copper ore!");
                        break;
                    case "chest":
                        gp.ui.showMessage("L bozo chests don't work yet");
                        break;

                    }

            }
        }
    }
    public void interactNPC(int i){
        if(i != 99999){
            System.out.println("you bonk");
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

        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // Check NPC Collision
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

//        System.out.println("X: " + worldX + ", Y: " + worldY);
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

        image = image1;
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }


}
