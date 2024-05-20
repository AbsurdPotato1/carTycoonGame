package main;

import object.IdToObject;
import object.ObjectCopperOre;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
    public ArrayList<Integer> craftables = new ArrayList<Integer>();

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
            if(gp.player.isCloseTo(gp.iTile[11])) {
                drawCraftingUI(g2);
            }
        }else {
            drawHotbar(g2);
        }
        int playTimeTextLength;
        g2.setFont(Fonts.pressStart_2P);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.setColor(Color.white);

        playTime += (double) 1/gp.FPS; // each frame add 1/60 of time
        playTimeTextLength = (int)g2.getFontMetrics().getStringBounds((int)playTime + "Time: ", g2).getWidth();

//        g2.drawString("Time: " + (int)playTime, gp.screenWidth - playTimeTextLength - 30, 65);
        if(messageOn){
            g2.drawString(message, GamePanel.tileSize / 2, GamePanel.tileSize * 5);

            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }

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
                g2.drawImage(IdToObject.getImageFromId(objId), slotX, slotY, 48, 48, null);
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
        int frameWidth = 464; // 9 * 48 + 2 * 16 -- 16 is margins, 48 is tile size
        int frameHeight = 208; // 4 * 16 + 48 * 3
        int frameX = 20;
        int frameY = 20;
        Color outerColor = new Color(216, 178, 129, 127);
        Color innerColor = new Color(255, 255, 255);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2, outerColor, innerColor);

        final int slotXstart = frameX + (80 - GamePanel.tileSize) / 2;
        final int slotYstart = frameY + (80 - GamePanel.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        int inventorySlot = 0;
        for(Integer objId : gp.player.inventory.keySet()){
            if(inventorySlot == 9 || inventorySlot == 18){
                slotY += GamePanel.tileSize + 16;
                slotX = slotXstart;
            }
            int numObject = gp.player.inventory.get(objId); // number of objects
            int numDrawn = 0; // number of objects of current object that have been drawn
            for(int i = 0; i < (numObject-1) / gp.player.maxObjectPerSlot + 1; i++){
                if(inventorySlot == 9 || inventorySlot == 18){
                    slotY += GamePanel.tileSize + 16;
                    slotX = slotXstart;
                }
                g2.setFont(Fonts.pressStart_2P);
                g2.drawImage(IdToObject.getImageFromId(objId), slotX, slotY, 48, 48, null);
                int curNumObj = Math.min(gp.player.maxObjectPerSlot, numObject - numDrawn); // number to draw
                int numLength = (int)g2.getFontMetrics().getStringBounds(String.valueOf(curNumObj), g2).getWidth(); // length of number string to draw
                g2.drawString(String.valueOf(curNumObj), slotX+44 - numLength, slotY+44); // draw number of objects
                slotX += GamePanel.tileSize;
                numDrawn += gp.player.maxObjectPerSlot;
                inventorySlot++;
            }
        }
        inventorySize = Math.max(inventorySize, inventorySlot);

        int cursorX = slotXstart + (GamePanel.tileSize * inventoryCol);
        int cursorY = slotYstart + (GamePanel.tileSize * inventoryRow) + inventoryRow * 16;
        int cursorWidth = GamePanel.tileSize;
        int cursorHeight = GamePanel.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }
    public void drawCraftingUI(Graphics2D g2) {

        int frameWidth = 464; // 9 * 48 + 2 * 16 -- 16 is margins, 48 is tile size
        int frameHeight = 16*2 + 48; // 4 * 16 + 48 * 3
        int frameX = 20;
        int frameY = 248; // 20 px below the inventory
        Color outerColor = new Color(216, 178, 129, 127);
        Color innerColor = new Color(255, 255, 255);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2, outerColor, innerColor);

        final int slotXstart = frameX + (80 - GamePanel.tileSize) / 2;
        final int slotYstart = frameY + (80 - GamePanel.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for(int i = 0 ; i < craftables.size() ; i++) {
            g2.drawImage(IdToObject.getImageFromId(i), slotX, slotY, 48, 48, null);
            slotX += GamePanel.tileSize;

        }

        int cursorX = slotXstart + (GamePanel.tileSize * inventoryCol);
        int cursorY = slotYstart + (GamePanel.tileSize * inventoryRow) + inventoryRow * 16;
        int cursorWidth = GamePanel.tileSize;
        int cursorHeight = GamePanel.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

    }

    public void initCraftables() {
        for(int i = 0 ; i < IdToObject.numObjs ; i++) {
            try {
                Boolean c = IdToObject.idObject[i].getField("craftable").getBoolean(null);
                craftables.add(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
