package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage image;
    public Sprite(BufferedImage image){
        this.image = image;
    }
    public void draw(Graphics g, int x, int y){
        g.drawImage(image, x, y, null);
    }
}
