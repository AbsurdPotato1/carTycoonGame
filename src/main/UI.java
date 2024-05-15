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
    Font arial_40, montserrat;
    BufferedImage copperOreImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    double playTime;
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        ObjectCopperOre copperOre = new ObjectCopperOre();
        copperOreImage = copperOre.image;
//        try {
//            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/Montserrat-VariableFont_wght.ttf");
//            fontFile = new File("fonts/Montserrat-VariableFont_wght.ttf");
//            montserrat = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(14f);
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(montserrat);
//        } catch (IOException | FontFormatException e) {
//            throw new RuntimeException(e);
//        }
    }


    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        drawHotbar(g2);
        int playTimeTextLength;
        g2.setFont(Fonts.montserrat);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.setColor(Color.white);

        //drawing the title state
//        if(gp.gameState == gp.titleState){
//            //drawTitleScreen();
//            g2.setColor(new Color(60,70,90));
//
//            String text = "CarSimulator";
//            int x = gp.screenWidth/2 -  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
//            int y = gp.tileSize*3;
//            g2.drawString(text, x, y);
//            //menu
//            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
//
//            text = "NEW START";
//            x =gp.screenWidth/2 -  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
//            y += gp.tileSize*4;
//            g2.drawString(text, x, y);
//
//            text = "Load GAME";
//            x =gp.screenWidth/2 -  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
//            y += gp.tileSize;
//            g2.drawString(text, x, y);
//
//            text = "QUIT";
//            x =gp.screenWidth/2 -  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
//            y += gp.tileSize;
//            g2.drawString(text, x, y);
//
//        }


        playTime += (double) 1/gp.FPS; // each frame add 1/60 of time
        playTimeTextLength = (int)g2.getFontMetrics().getStringBounds((int)playTime + "Time: ", g2).getWidth();

        g2.drawString("Time: " + (int)playTime, gp.screenWidth - playTimeTextLength - 30, 65);
        g2.drawImage(copperOreImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.numCopper, 75, 50);
        if(messageOn){
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }

    }
//    public void drawTitleScreen(){
//
//        //Title name
//        //changing the font  g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
//        String text = "CarSimulator";
//        int x = gp.screenWidth/2 -  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
//    }
    public void drawHotbar(Graphics2D g2){
        int frameWidth = 992;
        int frameHeight = 80;
        int frameX = gp.screenWidth / 2 - frameWidth / 2;
        int frameY = gp.screenHeight - frameHeight;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

        final int slotXstart = frameX + (80 - gp.tileSize) / 2;
        final int slotYstart = frameY + (80 - gp.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

//        for (Integer objId : gp.player.inventory.keySet()) {
//            Class<? extends SuperObject> objClass = IdToObject.getObjectFromId(objId);
//
//            try {
//                // Access the static 'inventoryImage' field from the class
//                BufferedImage inventoryImage = (BufferedImage) objClass.getField("inventoryImage").get(null);
//
//                // Draw the image if it exists
//                if (inventoryImage != null) {
//                    g2.drawImage(inventoryImage, slotX, slotY, 48, 48, null);
//                }
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                e.printStackTrace(); // Handle exceptions appropriately
//            }
//
//            slotX += gp.tileSize;
//        }
        for(Integer objId : gp.player.inventory.keySet()){
            g2.drawImage(IdToObject.getImageFromId(objId), slotX, slotY, 48, 48, null);
            slotX += gp.tileSize;
        }

        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


    }

    public void drawInventory(Graphics2D g2){
        int frameWidth = 992;
        int frameHeight = 80;
        int frameX = gp.screenWidth / 2 - frameWidth / 2;
        int frameY = gp.screenHeight - frameHeight;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

        final int slotXstart = frameX + (80 - gp.tileSize) / 2;
        final int slotYstart = frameY + (80 - gp.tileSize) / 2;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for(Integer objId : gp.player.inventory.keySet()){
            g2.drawImage(IdToObject.getImageFromId(objId), slotX, slotY, 48, 48, null);
            slotX += gp.tileSize;
        }
//        for(int i = 0; i < gp.player.inventory.size(); i++){
//            g2.drawImage(gp.player.inventory.get(i).inventoryImage, slotX, slotY, 48, 48, null);
//
//            slotX += gp.tileSize;
//        }

        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


    }

    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2){
        Color c = new Color(216, 178, 129, 127); // black
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 7, y + 7, width - 14, height - 14, 25, 25);
    }
}
