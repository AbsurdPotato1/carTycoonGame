package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import main.Fonts;
import object.ToolPickaxe;

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
        dialogues.add("Hi. I am the chief miner here.");
        dialogues.add("To get started on your mining journey, you need a\npickaxe.");
        dialogues.add("Since it's your first time, i'll give it to you for\n free.");
        dialogues.add("");
//        dialogues[3] = "*You receive a pickaxe*";
    }
    public void setAction(){ // possible bug: sometimes gets stuck on tile corners
        // bug: player and entity can clip into each other if travelling into each other sometimes
        // unused for now
    }


    public void speak(){
        super.speak();
    }
    public void changeDialogue(){
        if(numTimesTalked == 1) {
            dialogues.clear();
            dialogues.add("How's your mining going?");
            dialogues.add("I hope you're putting my pickaxe to good use.");
            dialogues.add("");
        }
        if(numTimesTalked == 2){
            dialogues.clear();
            dialogues.add("... Get back to mining.");
            dialogues.add("");
        }
        if(numTimesTalked == 3){
            dialogues.clear();
            dialogues.add("... Why are you still talking to me?");
            dialogues.add("Don't tell me you've lost my pickaxe.");
            dialogues.add("");
        }
    }
    @Override
    public void doDialogue(Graphics2D g2){
        long talkInterval = 30; // Minimum time between dialogue transition
        if(isCloseTo(gp.player)) {
            if ((System.nanoTime() - lastTalkTime) / (1000000000 / gp.FPS) >= talkInterval && !beganTalking && isClicked() && System.nanoTime() - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS)) {
                gp.gameState = GamePanel.dialogueState;
                beganTalking = true;
                lastTalkTime = System.nanoTime();
            }
            if (beganTalking) {
                speak();
                gp.ui.drawDialogueScreen(g2);
                if (gp.mouseH.mouseClicked &&  System.nanoTime() - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS)) {
                    long currentTime;
                    currentTime = System.nanoTime();
                    if ((currentTime - lastTalkTime) / (1000000000 / gp.FPS) >= talkInterval) {
                        lastTalkTime = currentTime;
                        dialogueNum++;
                    }
                }
            }

            if(firstTimeDialogue && dialogueNum == dialogues.size() - 1){
                firstTimeDialogue = false;
                gp.player.addOneToInventory(ToolPickaxe.class);
            }
            if(dialogueNum == dialogues.size() - 1){
                numTimesTalked++;
                changeDialogue();
            }

        }
        else {
            dialogueNum = 0;
            beganTalking = false;
        }
        if(dialogueNum >= dialogues.size()){
            beganTalking = false;
            dialogueNum = 0;
            lastTalkTime = System.nanoTime();
        }
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
