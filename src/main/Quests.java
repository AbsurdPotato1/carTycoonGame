package main;

import object.IdToObject;
import object.ObjectChest;
import object.ObjectCopperOre;
import object.ObjectStick;
import object.ToolPickaxe;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Quests {
    GamePanel gp;
    public int pressInterval = 15;
    public long lastPressTime = 0;
    public boolean inQuestMenu = false;
    public static int questIncomplete = 0;
    public static int questComplete = 1;
//    public ArrayList<String> questList = new ArrayList<>(); // stores current quests
    public LinkedHashMap<String, Integer> questList = new LinkedHashMap<>(); // ordered hashmap - based on insertion order

    public Quests(GamePanel gp){

        this.gp = gp;
    }
    public void loadInitialQuests(){
        questList.put("Get a pickaxe", 0);
        questList.put("Mine some ore", 0);
        questList.put("Break some trees with your pickaxe", 0);
        questList.put("Craft a chest", 0);
        questList.put("Sell some things for money", 0);
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
        if(gp.player.inInventory(ObjectStick.class, 10)){
            questList.replace("Break some trees with your pickaxe", 1);
        }
        if(gp.player.inInventory(ObjectChest.class, 1)){
            questList.replace("Craft a chest", 1);
        }
        if(gp.player.money >= 10){
            questList.replace("Sell some things for money", 1);
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
