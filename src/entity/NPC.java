package entity;

import main.GamePanel;

import java.util.Random;


public class NPC extends Entity { //Just a collection of NPC-wide methods

    public NPC(GamePanel gp) {
        super(gp);
    }
    public void moveRandomly() {
        actionLockCounter++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(132) + 1; // randInt from 1 to 132
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
}
