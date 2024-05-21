package main;

import java.awt.Graphics2D;
import java.util.ArrayList;


public class Quests {
    GamePanel gp;
    public int pressInterval = 15;
    public long lastPressTime = 0;
    public boolean inQuestMenu = false;
    public ArrayList<String> questList = new ArrayList<>(); // stores current quests
    public Quests(GamePanel gp){

        this.gp = gp;
    }
    public void loadInitialQuests(){
        questList.add("Getting an upgrade");
        questList.add("Isn't it iron pick");
    }
    public void draw(Graphics2D g2){
        if(!inQuestMenu){
            gp.ui.drawQuestBox(g2);
        }else{
            gp.ui.drawQuests(g2);
        }
    }
    public void update(){
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
    }
}
