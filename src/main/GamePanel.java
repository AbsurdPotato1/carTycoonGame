package main;//package main.Main;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
// this class will display the window
public class GamePanel extends JPanel implements Runnable {

    // screen settings

    final int originalTileSize = 16; // 16 x 16
    final int scale = 3; // may not use this stuff since not retro -- pixels; instead use sprites in future
    // Hopefully sprite classes work
    //https://www.reddit.com/r/gamedev/comments/mct51g/how_to_make_sprite_sheets_in_java/

    public final int tileSize = originalTileSize * scale; // 96 x 96;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 24;
    public final int screenWidth = tileSize * maxScreenCol; // 2048px
    public final int screenHeight = tileSize * maxScreenRow; // 1536px

    // world settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;

    public int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread; // This will run the code continuously (i.e. won't stop)

    // GRAPHICS
    public UI ui = new UI(this);

    // ENTITIES
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[100]; // display up to 10 objects at the same time

//    int player1X = 1024;
//    int player1Y = 768;
//    int player1SpeedVert = 4;
//    int player1SpeedHor = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        // While the thread is running, do things
        while(gameThread != null){
            /* TODO:
             *   UPDATE necessary data (sprite positions, attributes, etc.)
             *   DRAW: redraw screens with new info (frame updating)
             * */
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint(); // calls paintcomponent
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // just adds some useful functions

        // Tiles -- Keep in mind drawing order does matter.
        tileM.draw(g2);

        // Objects
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        // Player
        player.draw(g2);

        ui.draw(g2);

        g2.dispose(); // saves memory (optimization)
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop(); // repeat music
    }
    public void stopMusic(){
        sound.stop(); // stop music
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play(); // sound effects are short, only call once typically.
    }
}
