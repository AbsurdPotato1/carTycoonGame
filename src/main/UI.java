package main;

import object.IdToObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    double playTime;
    public int hotbarCol = 0;
    public int inventoryRow = 0;
    public int inventoryCol = 0;
    public int inventorySize;
    public String currentDialogue = "";
    public int inventoryWidth, inventoryHeight, inventoryFrameX, inventoryFrameY;
    public int craftingWidth, craftingHeight, craftingFrameX, craftingFrameY;
    public int sellWidth, sellHeight, sellFrameX, sellFrameY;

    public UI(GamePanel gp){
        this.gp = gp;
    }


    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        if(gp.keyH.inventoryPressed){
            drawInventory(g2);
        }else {
            drawHotbar(g2);
        }
//        int playTimeTextLength;
        g2.setFont(Fonts.pressStart_2P);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.setColor(Color.white);

        playTime += (double) 1/gp.FPS; // each frame add 1/60 of time
//        playTimeTextLength = (int)g2.getFontMetrics().getStringBounds((int)playTime + "Time: ", g2).getWidth();

//        g2.drawString("Time: " + (int)playTime, gp.screenWidth - playTimeTextLength - 30, 65);
        if(gp.gameState != gp.dialogueState) {
            drawMoney(g2);
        }
        if(messageOn){
            g2.drawString(message, GamePanel.tileSize / 2, GamePanel.tileSize * 5);

            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }

    }

    public void drawMoney(Graphics2D g2) {
        int moneyTextLength = (int)g2.getFontMetrics().getStringBounds("Money: $" + gp.player.money, g2).getWidth();
        g2.drawString("Money: $" + gp.player.money, gp.screenWidth - moneyTextLength - 30, 65);
    }

    public void drawHotbar(Graphics2D g2){
        int frameWidth = 464;
        int frameHeight = 80;
        int frameX = gp.screenWidth / 2 - frameWidth / 2;
        int frameY = gp.screenHeight - frameHeight;
        Color outerColor = new Color(216, 178, 129, 127);
        Color innerColor = new Color(255, 255, 255);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2, outerColor, innerColor);

        final int slotXstart = frameX + (80 - GamePanel.tileSize) / 2;
        final int slotYstart = frameY + (80 - GamePanel.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int hotbarSlot = 0;
        for(Integer objId : gp.player.inventory.keySet()){
            if(gp.player.inventory.get(objId) == 0){
                continue; // if player has 0 of the object
            }
            if(hotbarSlot == 9){
                break;
            }
            int numObject = gp.player.inventory.get(objId);
            int numDrawn = 0; // number of objects of current object that have been drawn
            for(int i = 0; i < (numObject-1) / gp.player.maxObjectPerSlot + 1; i++){
                if(hotbarSlot == 9){
                    break;
                }
                g2.setFont(Fonts.pressStart_2P);
                g2.drawImage((BufferedImage)IdToObject.getStaticVariable(objId, "inventoryImage"), slotX, slotY, 48, 48, null);
                int curNumObj = Math.min(gp.player.maxObjectPerSlot, numObject - numDrawn); // number to draw
                int numLength = (int)g2.getFontMetrics().getStringBounds(String.valueOf(curNumObj), g2).getWidth(); // length of number string to draw
                g2.drawString(String.valueOf(curNumObj), slotX+44 - numLength, slotY+44); // draw number of objects
                slotX += GamePanel.tileSize;
                numDrawn += gp.player.maxObjectPerSlot;
                hotbarSlot++;
            }
        }

        int cursorX = slotXstart + (GamePanel.tileSize * hotbarCol);
        int cursorY = slotYstart;
        int cursorWidth = GamePanel.tileSize;
        int cursorHeight = GamePanel.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


    }

    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2, Color outer, Color inner){
        g2.setColor(outer);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(inner);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 7, y + 7, width - 14, height - 14, 25, 25);
    }

    public void drawDialogueScreen(Graphics2D g2) {
        int x = 96;
        int y = 24;
        int width = gp.screenWidth - (GamePanel.tileSize * 4);
        int height = GamePanel.tileSize * 5;
        Color outerColor = new Color(0, 0, 0, 200);
        Color innerColor = new Color(255, 255, 255);
        drawSubWindow(x, y, width, height, g2, outerColor, innerColor);
        g2.setFont(g2.getFont().deriveFont(32F));
        x += GamePanel.tileSize / 2;
        y += 55;

        for(String line : currentDialogue.split("\n")) { // im not gonna split the text with string length lmao id rather die
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawInventory(Graphics2D g2){
        inventoryWidth = 464; // 9 * 48 + 2 * 16 -- 16 is margins, 48 is tile size
        inventoryHeight = 208; // 4 * 16 + 48 * 3
        inventoryFrameX = 20;
        inventoryFrameY = 20;
        Color outerColor = new Color(216, 178, 129, 127);
        Color innerColor = new Color(255, 255, 255);
        drawSubWindow(inventoryFrameX, inventoryFrameY, inventoryWidth, inventoryHeight, g2, outerColor, innerColor);

        final int slotXstart = inventoryFrameX + (80 - GamePanel.tileSize) / 2; // 16px margins
        final int slotYstart = inventoryFrameY + (80 - GamePanel.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        int curInventorySlot = 0;
        for(Integer objId : gp.player.inventory.keySet()){
            if(gp.player.inventory.get(objId) == 0){
                continue; // if player has 0 of the object
            }
            if(curInventorySlot == 9 || curInventorySlot == 18){
                slotY += GamePanel.tileSize + 16;
                slotX = slotXstart;
            }
            int numObject = gp.player.inventory.get(objId); // number of objects
            int numDrawn = 0; // number of objects of current object that have been drawn
            for(int i = 0; i < (numObject-1) / gp.player.maxObjectPerSlot + 1; i++){
                if(curInventorySlot == 9 || curInventorySlot == 18){
                    slotY += GamePanel.tileSize + 16;
                    slotX = slotXstart;
                }
                g2.setFont(Fonts.pressStart_2P);
                g2.drawImage((BufferedImage)IdToObject.getStaticVariable(objId, "inventoryImage"), slotX, slotY, 48, 48, null);
                int curNumObj = Math.min(gp.player.maxObjectPerSlot, numObject - numDrawn); // number to draw
                int numLength = (int)g2.getFontMetrics().getStringBounds(String.valueOf(curNumObj), g2).getWidth(); // length of number string to draw
                g2.drawString(String.valueOf(curNumObj), slotX+44 - numLength, slotY+44); // draw number of objects
                slotX += GamePanel.tileSize;
                numDrawn += gp.player.maxObjectPerSlot;
                curInventorySlot++;
            }
        }
        inventorySize = Math.max(inventorySize, curInventorySlot);

        int cursorX = slotXstart + (GamePanel.tileSize * inventoryCol);
        int cursorY = slotYstart + (GamePanel.tileSize * inventoryRow) + inventoryRow * 16;
        int cursorWidth = GamePanel.tileSize;
        int cursorHeight = GamePanel.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }
    public void drawCraftingScreen(Graphics2D g2) {
        // this method does not account for the number of items being greater than the number there is (make second page in the future)
        // scrolling is probably difficult - pages easier lol

        craftingWidth = 464; // 9 * 48 + 2 * 16 -- 16 is margins, 48 is tile size
        craftingHeight= 336; // 6 * 16 + 48 * 5
        craftingFrameX = gp.screenWidth / 2 - craftingWidth / 2;
        craftingFrameY = gp.screenHeight / 2 - craftingHeight/ 2;
        Color outerColor = new Color(216, 178, 129, 200);
        Color innerColor = new Color(0, 0, 0);
        drawSubWindow(craftingFrameX, craftingFrameY, craftingWidth, craftingHeight, g2, outerColor, innerColor);

        final int slotXstart = craftingFrameX + (80 - GamePanel.tileSize) / 2;
        final int slotYstart = craftingFrameY + (80 - GamePanel.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        int curCraftSlot = 0;
        for(int objId = 0; objId < IdToObject.numObjs; objId++){
            if((boolean)IdToObject.getStaticVariable(objId, "craftable")){
                if(curCraftSlot != 0 && curCraftSlot % 9 == 0){
                    slotY += GamePanel.tileSize + 16;
                    slotX = slotXstart;
                }
                g2.setFont(Fonts.pressStart_2P);
                g2.drawImage((BufferedImage)IdToObject.getStaticVariable(objId, "inventoryImage"), slotX, slotY, 48, 48, null);
                slotX += GamePanel.tileSize;
                curCraftSlot++;
            }
        }
    }

    public void drawSellScreen(Graphics2D g2){
        // this method does not account for the number of items being greater than the number there is (make second page in the future)
        // scrolling is probably difficult - pages easier lol

        sellWidth = 464; // 9 * 48 + 2 * 16 -- 16 is margins, 48 is tile size
        sellHeight = 336; // 6 * 16 + 48 * 5
        sellFrameX = gp.screenWidth / 2 - sellWidth / 2;
        sellFrameY = gp.screenHeight / 2 - sellHeight / 2;
        Color outerColor = new Color(216, 178, 129, 200);
        Color innerColor = new Color(0, 0, 0);

        drawSubWindow(sellFrameX, sellFrameY, sellWidth, sellHeight, g2, outerColor, innerColor);

        final int slotXstart = sellFrameX + (80 - GamePanel.tileSize) / 2; // 16px margins
        final int slotYstart = sellFrameY + (80 - GamePanel.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        int curSellSlot = 0;
        for(int objId = 0; objId < IdToObject.numObjs; objId++){ // draw all objects
            if((boolean)IdToObject.getStaticVariable(objId, "sellable")){
                if(curSellSlot != 0 && curSellSlot % 9 == 0){
                    slotY += GamePanel.tileSize + 16;
                    slotX = slotXstart; // TODO: change slotX to have margins in the future
                }
                g2.setFont(Fonts.pressStart_2P);
                g2.drawImage((BufferedImage)IdToObject.getStaticVariable(objId, "inventoryImage"), slotX, slotY, 48, 48, null);
                slotX += GamePanel.tileSize;
                curSellSlot++;
            }
        }
    }

    public void showSellDescription(Graphics2D g2, String description){
        g2.setFont(Fonts.pressStart_2P.deriveFont(18f));
        int width = (int)g2.getFontMetrics().getStringBounds(description, g2).getWidth() + 52;
        int height = 64;
        Color outerColor = new Color(0, 0, 0, 127);
        Color innerColor = new Color(255, 255, 255, 255);
        drawSubWindow(gp.mouseH.mouseScreenX, gp.mouseH.mouseScreenY, width, height, g2, outerColor, innerColor);
        int descriptionTextLength = (int)g2.getFontMetrics().getStringBounds(description, g2).getWidth();
        g2.drawString(description, gp.mouseH.mouseScreenX + 20, gp.mouseH.mouseScreenY + 40);
    }

}
