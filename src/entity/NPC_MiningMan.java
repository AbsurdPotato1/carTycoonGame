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
        up1 = getImage("npc/miner_up_1.png");
        right1 = getImage("npc/miner_right_1.png");
        down1 = getImage("npc/miner_down_1.png");
        left1 = getImage("npc/miner_left_1.png");
    }
    public void setDialogue(){
        dialogues.add("Hi I am the chief miner here.");
        dialogues.add("To get started on your mining journey, you need a pickaxe.");
        dialogues.add("Since it's your first time, i'll give it to you for free.");
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
        }
        if(numTimesTalked == 2){
            dialogues.clear();
            dialogues.add("... Get back to mining.");
        }
        if(numTimesTalked == 3){
            dialogues.clear();
            dialogues.add("... Why are you still talking to me?");
            dialogues.add("Don't tell me you've lost my pickaxe.");
        }
    }
    @Override
    public void doDialogue(Graphics2D g2){
        long talkInterval = 30; // Minimum time between dialogue transition
        if(gp.keyH.escapePressed){
            talking = false;
            gp.gameState = GamePanel.playerState;
        }
        if(isCloseTo(gp.player)){ // allow dialogue
            if ((System.nanoTime() - lastTalkTime) / (1000000000 / gp.FPS) >= talkInterval && !talking && isClicked() && System.nanoTime() - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS)) { // first time talked
                gp.gameState = GamePanel.dialogueState;
                talking = true;
                lastTalkTime = System.nanoTime();
            }
            if(talking){
                speak();
                gp.ui.drawDialogueScreen(g2);
                if (gp.mouseH.mouseClicked &&  System.nanoTime() - gp.mouseH.timeClicked <= 2 * (1000000000 / gp.FPS)) { // if mouse clicked
                    long currentTime;
                    currentTime = System.nanoTime();
                    if ((currentTime - lastTalkTime) / (1000000000 / gp.FPS) >= talkInterval) { // if last time talked is sufficiently long enough (30 frames)
                        lastTalkTime = currentTime;
                        if(dialogueNum == dialogues.size() - 1) { // check if player is done with the current dialogue and clicked
                            talking = false;
                            dialogueNum = 0;
                            if(numTimesTalked == 0 && gp.player.spaceInInventory(ToolPickaxe.objectId)) { // if first dialogue completed
                                gp.player.addToInventory(ToolPickaxe.class, 1);
                            }
                            numTimesTalked++;
                            changeDialogue(); // switch to new dialogue
                            gp.gameState = GamePanel.playerState;
                        }
                        else {
                            dialogueNum++;
                        }
                    }
                }
            }
        } else { // if player not close enough
            if(dialogueNum == dialogues.size() - 1){ // check if player is done with the current dialogue
                if(numTimesTalked == 0 && gp.player.spaceInInventory(ToolPickaxe.objectId)) { // if first dialogue completed
                    gp.player.addToInventory(ToolPickaxe.class, 1);
                }
                numTimesTalked++;
                changeDialogue();
            }
            dialogueNum = 0;
            talking = false; // npc no longer talking
            gp.gameState = GamePanel.playerState;
        }
    }

    @Override
    public void update(){
        super.update(); // check collisions
        if(gp.gameState != GamePanel.dialogueState) {
            moveRandomly();
        }
        if(gp.gameState == GamePanel.dialogueState){
            direction[0] = false;
            direction[1] = false;
            direction[2] = false;
            direction[3] = false;
        }
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Fonts.pressStart_2P);
        doDialogue(g2);
    }

}
