package main;//package main.Main;
import data.SaveLoad;
import entity.NPC;
import entity.Player;
import object.IdToObject;
import object.SuperObject;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

// this class will display the window
public class GamePanel extends JPanel implements Runnable {

    // screen settings

    final static int originalTileSize = 16; // 16 x 16
    final static int scale = 3; // may not use this stuff since not retro -- pixels; instead use sprites in future
    // Hopefully sprite classes work
    //https://www.reddit.com/r/gamedev/comments/mct51g/how_to_make_sprite_sheets_in_java/

    public static final int tileSize = originalTileSize * scale; // 96 x 96;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 18;
    public int screenWidth = tileSize * maxScreenCol; // 1536px
    public int screenHeight = tileSize * maxScreenRow; // 864

    // world settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;

    public int FPS = 60;
    public boolean gameStarted = false;
    // SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Thread gameThread; // This will run the code continuously (i.e. won't stop)en hs = new TitleScreen(this);
    public TitleScreen ts = new TitleScreen(this);

    // GRAPHICS
    public UI ui = new UI(this);

    //Save and Load
    SaveLoad saveLoad = new SaveLoad(this);

    // ENTITIES
    public Player player = new Player(this, keyH);
//    public SuperObject[] obj = new SuperObject[1000]; // display up to 100 objects at the same time
    public ArrayList<SuperObject> obj = new ArrayList<>();
    public ArrayList<SuperObject> tools = new ArrayList<>();
//    public SuperTool[] tools = new SuperTool[100];
    public ArrayList<NPC> npc = new ArrayList<>();
//    public NPC npc[] = new NPC[100];
    public ArrayList<InteractiveTile> iTile = new ArrayList<>();

    //states
    public int gameState;
    public static final int titleState = 0;
    public static final int playerState = 1;
    public static final int pauseState = 2;
    public static final int dialogueState = 3;

    public boolean drawPlayer = true; // whether or not to draw player

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        IdToObject.setIdObject();
        aSetter.setObject();
        aSetter.setTool();
        aSetter.setNPC();
        aSetter.setInteractiveTile();
        playMusic(0);
        setFullScreen();
        //game state
        gameState = GamePanel.titleState;
    }

    public void setFullScreen(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth = (int) width;
        screenHeight = (int) height;
    //offset factor to be used by mouse listener or mouse motion listener if you are using cursor in your game. Multiply your e.getX()e.getY() by this.
//        float fullScreenOffsetFactor = (float) screenWidth / (float) screenWidth2;
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
//                drawToTempScreen(); // draw to bufferedimage - done instead of straight on the JPanel to allow resizing
//                drawToScreen(); // draws bufferedimage to screen
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
        if(gameState == titleState){
            keyH.acceptMovement = false;
        }
        if(gameState == playerState || gameState == dialogueState){
            keyH.acceptMovement = true;
        }
        player.update();
        for(int i = 0; i < npc.size(); i++){
            npc.get(i).update();
        }
        for(int i = 0; i < iTile.size(); i++){
            iTile.get(i).update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // just adds some useful functions

        if (gameState == GamePanel.titleState) {
            ts.draw(g2);

        }
        if(gameState != GamePanel.titleState){
            // Tiles -- Keep in mind drawing order does matter.
            tileM.draw(g2);

            // Objects
            for (int i = 0; i < obj.size(); i++) {
                obj.get(i).draw(g2);
            }
            for(int i = 0; i < tools.size(); i++){
                tools.get(i).draw(g2);
            }

            //NPCs
            for (int i = 0; i < npc.size(); i++) {
                npc.get(i).draw(g2);
            }


            for(int i = 0; i < iTile.size(); i++){
                iTile.get(i).draw(g2);
            }

            // Player
            if(drawPlayer) {
                player.draw(g2);
            }

            ui.draw(g2);
        }
        if(gameState == GamePanel.dialogueState){

        }
        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop(); // repeat music
    }
    public void stopMusic(){
        music.stop(); // stop music
    }
    public void playSE(int i){
        se.setFile(i);
        se.play(); // sound effects are short, only call once typically.
    }
}