package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.util.Random;


public class NPC extends Entity { //Just a collection of NPC-wide methods
    public String[] dialogues = new String[30]; // Max 30 dialogs
    long lastTalkTime;
    boolean beganTalking = false;
    int dialogueNum = 0;

    public NPC(GamePanel gp) {
        super(gp);
    }
    public void moveRandomly() {
        actionLockCounter++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(192) + 1; // randInt from 1 to 132
            if (i <= 12) { // up
                direction[0] = true;
                direction[1] = false;
                direction[2] = false;
                direction[3] = false;
            } else if (i <= 24) { // right
                direction[0] = false;
                direction[1] = true;
                direction[2] = false;
                direction[3] = false;
            } else if (i <= 36) { // down
                direction[0] = false;
                direction[1] = false;
                direction[2] = true;
                direction[3] = false;
            } else if (i <= 48) { // left
                direction[0] = false;
                direction[1] = false;
                direction[2] = false;
                direction[3] = true;
            } else if (i <= 60) { // up right
                direction[0] = true;
                direction[1] = true;
                direction[2] = false;
                direction[3] = false;
            } else if (i <= 72) { // down right
                direction[0] = false;
                direction[1] = true;
                direction[2] = true;
                direction[3] = false;
            } else if (i <= 84) { // down left
                direction[0] = false;
                direction[1] = false;
                direction[2] = true;
                direction[3] = true;
            } else if (i <= 96) { // up left
                direction[0] = true;
                direction[1] = false;
                direction[2] = false;
                direction[3] = true;
            }
            else{
                direction[0] = false;
                direction[1] = false;
                direction[2] = false;
                direction[3] = false;
            }
            actionLockCounter = 0;
        }
    }

    //dialogue_stage has not yet been implemented but is intended to access an array for the corresponding dialogue that the NPC should say
    //ds is 0 through 9

    public void speak(){
        if(dialogues[dialogueNum] != null) {
            gp.ui.currentDialogue = dialogues[dialogueNum];
        }
    }

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
        }
        else {
            dialogueNum = 0;
            beganTalking = false;
        }
        if(dialogues[dialogueNum] == null){
            beganTalking = false;
            dialogueNum = 0;
            lastTalkTime = System.nanoTime();
        }
    }

    public boolean isClicked() {
        int screenX = worldX - (int)gp.player.worldX + gp.player.screenX;
        int screenY = worldY - (int)gp.player.worldY + gp.player.screenY;
        if(gp.mouseH.mouseClicked){
            if(gp.mouseH.mouseX >= (screenX) && gp.mouseH.mouseX <= (screenX + solidArea.width) &&
                    gp.mouseH.mouseY >= (screenY) && gp.mouseH.mouseY <= (screenY + solidArea.height)){
                return true;
            }
        }
        return false;
    }

}
