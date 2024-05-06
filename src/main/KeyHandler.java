package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//package main;
public class KeyHandler implements KeyListener {
    /* possibleTODO:
     *  Change to use KeyBindings instead; much more versatile -- ONLY IF NECESSARY, unessential at the moment
     *  https://stackoverflow.com/questions/22741215/how-to-use-key-bindings-instead-of-key-listeners
     * *///

    public boolean leftPressed, rightPressed, jumpPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
