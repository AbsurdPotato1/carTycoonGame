package main;

import object.ObjectCopperOre;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font arial_40, montserrat;
    BufferedImage copperOreImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    double playTime;
    File fontFile;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        ObjectCopperOre copperOre = new ObjectCopperOre();
        copperOreImage = copperOre.image;
        montserrat = loadFont("fonts/Montserrat-VariableFont_wght.ttf");
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
    public Font loadFont(String path){
        try {
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream(path);
            fontFile = new File(path);
            Font temp = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(temp);
            return temp;
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        int playTimeTextLength;
        g2.setFont(montserrat);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.setColor(Color.white);
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

//    public void drawSubWindow(int x, int y, int width, int height){
//        g2.setColor(Color.black);
//    }
}
