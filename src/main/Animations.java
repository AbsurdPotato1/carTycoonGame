package main;

import java.awt.*;

public class Animations {
    GamePanel gp;
    public float alpha = 0.0f;
    public Animations(GamePanel gp) {
        this.gp = gp;
    }
    public void fadeOut(Graphics2D g2) {
//        for(float alpha = 0.0f ; alpha < 1.0 ; alpha += 0.05f) {
            g2.setColor(new Color(0,0,0,alpha));
            g2.drawRect(0,0,gp.screenWidth, gp.screenHeight);
            g2.dispose();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }
}
