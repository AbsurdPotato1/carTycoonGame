package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Floor extends Entity {
    GamePanel gp;

    public Floor(GamePanel gp){
        this.gp = gp;
    }

    public void getFloorImage(){
        try {
            car1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/StartingCarTransparent.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
    }

}
