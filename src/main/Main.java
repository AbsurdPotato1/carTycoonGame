package main;

import javax.swing.JFrame;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Main {

    public static JFrame window;
    public static void main(String[] args){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("xCar");
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // fits panel to size

        window.setLocationRelativeTo(null); // center of the screen woot woot
        window.setVisible(true);
        window.setCursor(window.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );
        gamePanel.setUpGame();

        gamePanel.startGameThread();
    }
}