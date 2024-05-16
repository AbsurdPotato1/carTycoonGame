package entity;

import main.GamePanel;

import java.util.Random;


public class NPC_MiningMan extends NPC {

    public NPC_MiningMan(GamePanel gp) {
        super(gp);
//        direction[2] = true; // down
        getMiningManImage();
        speedHor = 2 * 60.0 / gp.FPS;
        speedVert = 2 * 60.0 / gp.FPS;
    }
    public void getMiningManImage(){
        image1 = getImage("npc/oldman_down_1.png");
    }
    public void setAction(){ // possible bug: sometimes gets stuck on tile corners
        // bug: player and entity can clip into each other if travelling into each other sometimes
        moveRandomly();


    }
}
