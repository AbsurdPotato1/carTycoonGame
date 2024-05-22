package main;

import object.IdToObject;
import object.ObjectCopperOre;
import object.ToolPickaxe;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;


public class Quests {
    GamePanel gp;
    public int pressInterval = 15;
    public long lastPressTime = 0;
    public boolean inQuestMenu = false;
    public static int questIncomplete = 0;
    public static int questComplete = 1;
//    public ArrayList<String> questList = new ArrayList<>(); // stores current quests
    public HashMap<String, Integer> questList = new HashMap<>();

    public Quests(GamePanel gp){

        this.gp = gp;
    }
    public void loadInitialQuests(){
        questList.put("Get a pickaxe", 0);
        questList.put("Mine some ore", 0);
    }
    public void draw(Graphics2D g2){
        if(!inQuestMenu){
            gp.ui.drawQuestBox(g2);
        }else{
            gp.ui.drawQuests(g2);
        }
    }
    public void update(){
        if(isClicked()){
            inQuestMenu = true;
        }
        if(gp.keyH.questPressed){
            long currentTime = System.nanoTime();
            if((currentTime - lastPressTime) / (1000000000 / gp.FPS) >= pressInterval){
                lastPressTime = System.nanoTime();
                inQuestMenu = !inQuestMenu;
            }
        }
        if(gp.keyH.escapePressed){
            inQuestMenu = false;
        }
        if(gp.player.inInventory(ToolPickaxe.class, 1)) {
            questList.replace("Get a pickaxe", 1); // replace only replaces if the key is already present
        }
        if(gp.player.inInventory(ObjectCopperOre.class, 5)) {
            questList.replace("Mine some ore", 1);
        }
    }
    public boolean isClicked() {
        if(gp.mouseH.mouseClicked && (System.nanoTime() - gp.mouseH.timeClicked) / (1000000000 / gp.FPS) <= 2){
            if(gp.mouseH.mouseScreenX >= (gp.ui.questBoxX) && gp.mouseH.mouseScreenX <= (gp.ui.questBoxX + gp.ui.questBoxWidth) &&
                    gp.mouseH.mouseScreenY >= (gp.ui.questBoxY) && gp.mouseH.mouseScreenY <= (gp.ui.questBoxY + gp.ui.questBoxHeight)){
                return true;
            }
        }
        return false;
    }
}
