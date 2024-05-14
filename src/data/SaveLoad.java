package data;

import main.GamePanel;
import object.ObjectChest;
import object.ObjectCopperOre;

import java.io.*;
import java.io.File;

public class SaveLoad {
    GamePanel gp;
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }
//    public Entity getObject(String itemName){
//        Entity obj = null;
//
//        switch(itemName){
//            case "ObjectChest": obj = new ObjectChest(gp); break;
//            case "copperOre": obj = new ObjectCopperOre(gp); break;
//
//        }
//    }
    public void save(){
       try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

           DataStorage ds = new DataStorage();

//           ds.level = gp.player.level;
//           ds.maxLife = gp.player.maxLife;
//           ds.life = gp.player.life;
//           ds.maxMann = gp.player.maxMann;
//           ds.manna = gp.player.manna;
//           ds.strength = gp.player.strength;
//           ds.dexterity = gp.player.dexterity;
//           ds.exp = gp.player.exp;
//           ds.nextLevelExp = gp.player.nextLevelExp;
//           ds.coin = gp.player.coin;

           // Player inventory
//           for(int i =0; i< gp.player.inventory.size(); i++){
//               ds.itemNames.add(gp.player.inventory.get(i).name);
//               ds.itemAmounts.add(gp.player.inventory.get(i).amount);
//           }
           //wrtie the DataStorage object
           oos.writeObject(ds);
       }
       catch(Exception e){
           System.out.println("Save Exception");
        }
    }
    public void load(){
    try{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

        //Read the DataStorage object
        DataStorage ds = (DataStorage)ois.readObject();

//        gp.player.level = ds.level;
//        gp.player. maxLife = ds.maxLife;
//        gp.player.life = ds.life;
//        gp.player.maxMann = ds.maxMann;
//        gp.player.manna = ds.manna;
//        gp.player.strength = ds.strength;
//        gp.player.dexterity = ds.dexterity;
//        gp.player.exp = ds.exp;
//        gp.player.nextLeaveExp = ds.nextLeaveExp;
//        gp.player.coin = ds.coin;
    }
    catch(Exception e){
        System.out.print("Load Exception");
    }
    }

}
