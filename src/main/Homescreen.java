package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Homescreen {
    GamePanel gp;
    boolean playClicked;

    public Homescreen(GamePanel gp) {
        this.gp = gp;
        playClicked = false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/earth.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.setFont(Fonts.pressStart_2P.deriveFont(30f));
        int iheight = gp.screenHeight;
        int iwidth = iheight;
        g2.drawImage(image, (gp.screenWidth - iwidth)/ 2, (gp.screenHeight - iheight)/2, iwidth, iheight, null);

        // TODO: Make white
        String toDisplay = "Press space to start!";
        int loadingScreenTextLength = (int)g2.getFontMetrics().getStringBounds(toDisplay, g2).getWidth();
        int loadingScreenTextHeight = (int)g2.getFontMetrics().getStringBounds(toDisplay, g2).getHeight();
        g2.drawString(toDisplay, (gp.screenWidth - loadingScreenTextLength) / 2, (gp.screenHeight - loadingScreenTextHeight - 100) / 2);

        g2.dispose();
    }

}
