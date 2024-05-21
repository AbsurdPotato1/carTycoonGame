package main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public GamePanel gp;
    public int mouseWorldX, mouseWorldY; // Mouse world x and y
    public int mouseScreenX, mouseScreenY; // Mouse screen x and y
    public boolean mouseClicked;
    public long timeClicked;
    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public boolean mouseInsideScreen(int leftX, int rightX, int topY, int bottomY) {
        return this.mouseScreenX >= leftX &&
                this.mouseScreenX <= rightX &&
                this.mouseScreenY >= topY &&
                this.mouseScreenY <= bottomY;
    }
    public boolean mouseInsideWorld(int leftX, int rightX, int topY, int bottomY) {
        return this.mouseWorldX >= leftX &&
                this.mouseWorldX <= rightX &&
                this.mouseWorldY >= topY &&
                this.mouseWorldY <= bottomY;
    }

    public void updateMousePosition(){
        Point point = MouseInfo.getPointerInfo().getLocation();
        mouseScreenX = (int) point.getX();
        mouseScreenY = (int) point.getY();
        mouseWorldX = mouseScreenX + (int)gp.player.worldX - gp.player.screenX;
        mouseWorldY = mouseScreenY + (int)gp.player.worldY - gp.player.screenY;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicked = true;
        timeClicked = System.nanoTime();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
