package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static main.Main.window;


//package main;
public class KeyHandler implements KeyListener {
    /* possibleTODO:
     *  Change to use KeyBindings instead; much more versatile -- ONLY IF NECESSARY, unessential at the moment
     *  https://stackoverflow.com/questions/22741215/how-to-use-key-bindings-instead-of-key-listeners
     * *///

    public boolean leftPressed, rightPressed, jumpPressed, downPressed;
    public GamePanel gp;
    double frameInterval;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
        frameInterval = 1000000000/gp.FPS;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        /* TODO:
         *   UPDATE necessary data (sprite positions, attributes, etc.)
         *   DRAW: redraw screens with new info (frame updating)
         * */

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_W){
            jumpPressed = true;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        window.addMouseWheelListener(scroll -> {
            currentTime = System.nanoTime();
            if(currentTime - lastTime >= frameInterval){
                int notches = scroll.getWheelRotation();
                if (notches < 0) {
                    // Scroll up
                    if(gp.ui.slotCol != 0) {
                        gp.ui.slotCol--;
                        lastTime = System.nanoTime();
                    }
                } else {
                    // Scroll down
                    if(gp.ui.slotCol != 19) { // (UI.drawHotbar.frameWidth - 32) / 48 - 1
                        gp.ui.slotCol++;
                        lastTime = System.nanoTime();
                    }
                }
            }

        });
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_W){
            jumpPressed = false;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = false;
        }
    }
}
