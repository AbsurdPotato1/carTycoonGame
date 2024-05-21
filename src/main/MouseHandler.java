package main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public GamePanel gp;
    public int mouseX, mouseY; // Screen x and y
    public boolean mouseClicked;
    public long timeClicked;
    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public boolean mouseInside(int x1, int x2, int y1, int y2) {
        return this.mouseX >= x1 &&
                this.mouseX <= x2 &&
                this.mouseY >= y1 &&
                this.mouseY <= y2;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicked = true;
        Point point = MouseInfo.getPointerInfo().getLocation();
        mouseX = (int) point.getX();
        mouseY = (int) point.getY();
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
