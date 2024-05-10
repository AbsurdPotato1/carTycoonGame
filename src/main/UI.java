package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_40;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Copper = " + gp.player.numCopper, 25, 50);
    }

//    public void drawSubWindow(int x, int y, int width, int height){
//        g2.setColor(Color.black);
//    }
}
