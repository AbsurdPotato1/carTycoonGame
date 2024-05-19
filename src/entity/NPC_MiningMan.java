package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.util.Random;


public class NPC_MiningMan extends NPC {

    public NPC_MiningMan(GamePanel gp) {
        super(gp);
//        direction[2] = true; // down
        getMiningManImage();
        speedHor = 2 * 60.0 / gp.FPS;
        speedVert = 2 * 60.0 / gp.FPS;

        dialogues[0] = new Dialogue(new String[]{"Hello!", "How are you?"});
    }
    public void getMiningManImage(){
        up1 = getImage("npc/oldman_up_1.png");
        right1 = getImage("npc/oldman_right_1.png");
        down1 = getImage("npc/oldman_down_1.png");
        left1 = getImage("npc/oldman_left_1.png");
    }
    public void setAction(){ // possible bug: sometimes gets stuck on tile corners
        // bug: player and entity can clip into each other if travelling into each other sometimes
//        moveRandomly();
    }
    @Override
    public void update(){
        super.update(); // check collisions

    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if(isClicked()) {
            System.out.println("clicked");
            triggerDialogue(0, g2);
        }
    }
}
