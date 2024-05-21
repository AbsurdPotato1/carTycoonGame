package entity;

import main.GamePanel;
import main.KeyHandler;
import object.IdToObject;
import object.ObjectCopperOre;
import object.SuperObject;
import object.ToolPickaxe;

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
    long lastPickUpTime = 0;
    long lastMineTime = 0;
    public HashMap<Integer, Integer> inventory = new HashMap<>();
    public Integer[] inventoryKeysAsArray; // change inventory to use a array, not hashmap in the future - will be simpler and better
    public int maxObjectPerSlot = 99;
    public int money;
//    public int[] inventory = new int[255]; // size is number of objects.
//    public ArrayList<SuperObject> inventory = new ArrayList<>();
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (GamePanel.tileSize / 2);
        screenY = gp.screenHeight / 2 - (GamePanel.tileSize / 2);

        solidArea = new Rectangle(8, 8 , GamePanel.tileSize - 16, GamePanel.tileSize - 8); // sets hitbox - 8 pixels from left, right, and top of sprite, i.e. hitbox is 32x40
//        solidArea = new Rectangle(0, 0, GamePanel.tileSize, GamePanel.tileSize); // use for testing
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setPlayerStartingItems();
    }
    public void setDefaultValues(){
        worldX = 50;
        worldY = 500;
        speedHor = 2 * 60.0 / gp.FPS;
        speedVert = 2 * 60.0 / gp.FPS;
    }
    public void addOneToInventory(Class c){
        try {
            inventory.merge((Integer)c.getField("objectId").get(null), 1, Integer::sum); // increments key (count) of value (SuperObject)
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public void setPlayerStartingItems(){
//        addOneToInventory(ObjectCopperOre.class);
//        addOneToInventory(ObjectCopperOre.class);
//        addOneToInventory(ObjectCopperOre.class);
    }

    public void getPlayerImage(){
        up1 = getImage("player/boy_up_1.png");
        right1 = getImage("player/boy_right_1.png");
        down1 = getImage("player/boy_down_1.png");
        left1 = getImage("player/boy_left_1.png");
    }

    public void getPlayerAttackImage(){
        attackUp1 = getImage("player/boy_attack_up_1.png");
        attackUp2 = getImage("player/boy_attack_up_2.png");
        attackRight1 = getImage("player/boy_attack_right_1.png");
        attackRight2 = getImage("player/boy_attack_right_2.png");
        attackDown1 = getImage("player/boy_attack_down_1.png");
        attackDown2 = getImage("player/boy_attack_down_2.png");
        attackLeft1 = getImage("player/boy_attack_left_1.png");
        attackLeft2 = getImage("player/boy_attack_left_2.png");
    }

    public void snapPlayerLoc() {
        if (downCollisionOn) {
            // Snap player to the nearest tile below
            int playerBottomY = (int)worldY + solidArea.y + solidArea.height; // Calculate the bottom Y-coordinate of the player
            int nearestTileBelowY = ((playerBottomY + GamePanel.tileSize - 1) / GamePanel.tileSize) * GamePanel.tileSize; // Calculate nearest tile below

            // Calculate the distance to the nearest tile above
            int nearestTileAboveY = nearestTileBelowY - GamePanel.tileSize;
            int distToTileAbove = (int)worldY + solidArea.y + solidArea.height - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - ((int)worldY + solidArea.height);

            // Snap to the nearest tile (above or below)
            if(distToTileAbove < 6){
                worldY = (nearestTileAboveY + 6) / GamePanel.tileSize * GamePanel.tileSize - solidArea.height;
            }else if(distToTileBelow < 6){
                worldY = (nearestTileBelowY + 6) / GamePanel.tileSize * GamePanel.tileSize - solidArea.height;;
            }
        }
        if (upCollisionOn) {
            // Snap player to the nearest tile below
            int playerTopY = (int)worldY; // Calculate the bottom Y-coordinate of the player
            int nearestTileAboveY = ((playerTopY) / GamePanel.tileSize) * GamePanel.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileBelowY = nearestTileAboveY + GamePanel.tileSize;
            int distToTileAbove = (int)worldY - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - (int)worldY;

            // Snap to the nearest tile (above or below)
            if(distToTileAbove < 6){
                worldY = (nearestTileAboveY + 6) / GamePanel.tileSize * GamePanel.tileSize;
            }else if(distToTileBelow < 6){
                worldY = (nearestTileBelowY + 6) / GamePanel.tileSize * GamePanel.tileSize;
            }
        }

        if (rightCollisionOn) {
            // Snap player to the nearest tile below
            int playerRightX = (int)worldX + solidArea.x + solidArea.width; // Calculate the right X-coordinate of the player
            int nearestTileRightX = ((playerRightX + GamePanel.tileSize - 1) / GamePanel.tileSize) * GamePanel.tileSize; // Calculate nearest tile right

            // Calculate the distance to the nearest tile above
            int nearestTileLeftX = nearestTileRightX - GamePanel.tileSize;
            int distToTileLeft = (int)worldX + solidArea.x + solidArea.width - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - ((int)worldX + solidArea.width);

            // Snap to the nearest tile (above or below)
            if(distToTileLeft < 6){
                worldX = (nearestTileLeftX + 6) / GamePanel.tileSize * GamePanel.tileSize - solidArea.width;
            }else if(distToTileRight < 6){
                worldX = (nearestTileRightX + 6) / GamePanel.tileSize * GamePanel.tileSize - solidArea.width;
            }
        }

        if (leftCollisionOn) {
            // Snap player to the nearest tile below
            int playerLeftX = (int)worldX; // Calculate the left X-coordinate of the player
            int nearestTileLeftX = ((playerLeftX) / GamePanel.tileSize) * GamePanel.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileRightX = nearestTileLeftX + GamePanel.tileSize;
            int distToTileLeft = (int)worldX - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - (int)worldX;

            // Snap to the nearest tile (above or below)
            if(distToTileLeft < 6){
                worldX = (nearestTileLeftX + 6) / GamePanel.tileSize * GamePanel.tileSize;
            }else if(distToTileRight < 6){
                worldX = (nearestTileRightX + 6) / GamePanel.tileSize * GamePanel.tileSize;
            }
        }
    }
    public boolean spaceInInventory(int objectId){
        return gp.ui.inventorySize < 27 || (gp.ui.inventorySize == 27 && inventory.get(objectId) % maxObjectPerSlot > 0);
    }
    public void pickUpObject(int i){
        long currentTime;
        currentTime = System.nanoTime();
        long pickUpInterval = 5;
        if((currentTime - lastPickUpTime) / (1000000000 / gp.FPS) >= pickUpInterval){ // checks if last pickup >= 5 frames ago -- TODO: seems to be going 6 frames (10 items / sec) - due to slight inaccuracy in timing
            lastPickUpTime = currentTime;
            if(i != 99999){

                String objectName = gp.obj.get(i).name;

                switch(objectName){
                    case "copperOre":
                        if(spaceInInventory(ObjectCopperOre.objectId)) {
                            gp.playSE(1); // sound effect
                            addOneToInventory(ObjectCopperOre.class);
                            gp.obj.remove(i);
                            gp.ui.showMessage("You got a copper ore!");
                        }
                        break;
                    case "chest":
                        gp.ui.showMessage("L bozo chests don't work yet");
                        break;

                    }

            }
        }
    }
    public void pickUpTool(int i) { // bug: pickaxe sometimes does not pick up immediately.
        long currentTime;
        currentTime = System.nanoTime();
        long pickUpInterval = 5;
        // bug caused due to line below this - could have been fixed by changing lastPickUpTime = System.nanoTime() to lastPickUpTime = 0; possibly
        if((currentTime - lastPickUpTime) / (1000000000 / gp.FPS) >= pickUpInterval){ // checks if last pickup >= 5 frames ago -- TODO: seems to be going 6 frames (10 items / sec) - due to slight inaccuracy in timing
            lastPickUpTime = currentTime;
            if(i != 99999){

                String objectName = gp.tools.get(i).name;

                switch(objectName){
                    case "pickaxe":
                        if(spaceInInventory(ToolPickaxe.objectId)) {
                            gp.playSE(1); // sound effect
                            addOneToInventory(ToolPickaxe.class);
                            gp.tools.remove(i);
                            System.out.println("pickaxe");
                            gp.ui.showMessage("You got a pickaxe");
                        }
                        break;
                }
            }
        }
    }
    public void interactWithTile(int i){
        long mineInterval = 5;
        switch(gp.iTile.get(i).name) {
            case "copperOreNode":
                long currentTime = System.nanoTime();
                if ((currentTime - lastMineTime) / (1000000000 / gp.FPS) >= mineInterval &&
                        currentTime - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS) &&
                        gp.ui.hotbarCol < inventory.size() && inventoryKeysAsArray[gp.ui.hotbarCol] == ToolPickaxe.objectId &&
                        gp.iTile.get(i).isCloseTo(this) && gp.iTile.get(i).isClicked()) {
                    lastMineTime = currentTime;
                    gp.obj.add(new ObjectCopperOre(gp, gp.iTile.get(i).worldX, gp.iTile.get(i).worldY));
                    gp.iTile.remove(i);
                }
                break;
        }
    }
    public void interactNPC(int i){
        if(i != 99999){
//            System.out.println("you bonk");
        }
    }
    public void update(){
        inventoryKeysAsArray = inventory.keySet().toArray(new Integer[0]);
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
        // Check tile collision
        gp.cChecker.checkTile(this);
        // Check interaction tile collision

        gp.cChecker.checkTile(this, gp.iTile);
        for(int i = 0; i < gp.iTile.size(); i++){
            interactWithTile(i);
        }

        // IF COLLISION IS FALSE, PLAYER CAN MOVE

        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // Check NPC Collision
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        int toolIndex = gp.cChecker.checkTool(this, true);
        pickUpTool(toolIndex);

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
            }
        }
        gp.cChecker.checkTile(this);
        snapPlayerLoc();
    }



    public void draw(Graphics2D g2){

        BufferedImage image = null;

        if(direction[1])image = right1;
        else if(direction[3])image = left1;
        else if(direction[0])image = up1;
        else if(direction[2])image = down1;
        else image = down1;
        g2.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);


    }


}
