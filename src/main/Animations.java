package main;

import java.awt.*;

public class Animations {
    public static void fadeOut(Graphics2D g2) throws InterruptedException {
        // PLEASE NOTE THIS DOESN'T ACTUALLY WORK!!!
        // https://stackoverflow.com/questions/3952993/how-to-fade-in-fade-out-a-java-graphics
        for(int i = 0 ; i < 256 ; i++) {
            g2.setColor(new Color(0, 0, 0, i));
            Thread.sleep(10);
        }

    }
    public static void fadeIn(Graphics2D g2) throws InterruptedException {
        // DOESNT WORK NEED FIX LINE 8
        for(int i = 255 ; i > -1 ; i--) {
            g2.setColor(new Color (0,0,0, i));
            Thread.sleep(10);
        }

    }
}
