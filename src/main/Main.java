package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;
    public static void main(String[] args){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("xCar");
//        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // fits panel to size

        window.setLocationRelativeTo(null); // center of the screen woot woot
        window.setVisible(true);

        gamePanel.setUpGame();

        gamePanel.startGameThread();
    }
}