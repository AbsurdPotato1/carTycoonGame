package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import main.Fonts;

public class NPC_MiningMan extends NPC {

    public NPC_MiningMan(GamePanel gp, int x, int y) {
        super(gp);
        getMiningManImage();
        worldX = x;
        worldY = y;
        speedHor = 2 * 60.0 / gp.FPS;
        speedVert = 2 * 60.0 / gp.FPS;
        setDialogue();
    }
    public void getMiningManImage(){
        up1 = getImage("npc/oldman_up_1.png");
        right1 = getImage("npc/oldman_right_1.png");
        down1 = getImage("npc/oldman_down_1.png");
        left1 = getImage("npc/oldman_left_1.png");
    }
    public void setDialogue(){
        dialogues[0] = "hi bozo";
        dialogues[1] = "Why are you still here?";
        dialogues[2] = "Just to suffer?";
        dialogues[3] = "THEN DIE RAHHHHHH";
        dialogues[4] = "BOOOM";
        dialogues[5] = "*you momentarily lose conciousness*\nuh oh";
    }
    public void setAction(){ // possible bug: sometimes gets stuck on tile corners
        // bug: player and entity can clip into each other if travelling into each other sometimes
        // unused for now
    }

    public void speak(){
        super.speak();
    }

    @Override
    public void update(){
        if(gp.gameState != GamePanel.dialogueState) {
            super.update(); // check collisions
            moveRandomly();
        }
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Fonts.pressStart_2P);
        doDialogue(g2);
    }
}
