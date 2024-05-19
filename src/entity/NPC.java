package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.util.Random;


public class NPC extends Entity { //Just a collection of NPC-wide methods
    public String[] dialogues = new String[30]; // Max 30 dialogs
    long lastTalkTime;
    boolean talkingTo = true;
    int dialogueNum = -1;

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
        if(dialogueNum != -1 && dialogues[dialogueNum] != null) {
            gp.ui.currentDialogue = dialogues[dialogueNum];
        }
    }

    public void doDialogue(Graphics2D g2){
        if(isCloseTo(gp.player)) {
            if (isClicked()) {
                gp.gameState = GamePanel.dialogueState;
                talkingTo = true;
            }
        }
        else{
            gp.gameState = GamePanel.playerState;
            dialogueNum = -1;
            lastTalkTime = 0;
            talkingTo = false;
            gp.ui.currentDialogue = "";
        }
        if(talkingTo) {
            speak();
            gp.ui.drawDialogueScreen(g2);
            if(gp.mouseH.mouseClicked){
                long currentTime;
                currentTime = System.nanoTime();
                long talkInterval = 30;
                if(dialogueNum == -1){
                    lastTalkTime = currentTime;
                    dialogueNum++;
                }
                else if((currentTime - lastTalkTime) / (1000000000 / gp.FPS) >= talkInterval) {
                    lastTalkTime = currentTime;
                    if(dialogueNum != dialogues.length - 1) {
                        dialogueNum++;
                    }
                }
            }
        }
    }

//    public void triggerDialogue(int dialogue_stage, Graphics2D g2) {
//        dialogues[dialogue_stage].runDialogue(g2);
//    }

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
