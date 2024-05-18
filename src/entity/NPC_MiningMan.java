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
        image1 = getImage("npc/oldman_down_1.png");
    }
    public void setAction(){ // possible bug: sometimes gets stuck on tile corners
        // bug: player and entity can clip into each other if travelling into each other sometimes
        moveRandomly();
    }

    public void draw(Graphics2D g2 ) {
        super.draw(g2);
        if(isClicked()) {
            triggerDialogue(0, g2);

        }
    }
}
