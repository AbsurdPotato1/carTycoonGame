package object;

import tile_interactive.InteractiveTile;
import tile_interactive.copperOreNode;
import tile_interactive.craftingBench;
import tile_interactive.shippingBay;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class IdToObject { // this entire class completely disregards type safety lmao - use with care
    public static final int numObjs = 6;
    public static final Class[] idObject = new Class[255];
    public static final Class[] craftables = new Class[255];
    public static final Class[] sellables = new Class[255];
    public static void setIdObject(){
        idObject[0] = ToolPickaxe.class;
        idObject[1] = ObjectChest.class;
        idObject[2] = ObjectCopperOre.class;
        idObject[3] = shippingBay.class;
        idObject[4] = craftingBench.class;
        idObject[5] = copperOreNode.class;
    }
    public static Class getObjectFromId(int id){ // helper function
        return idObject[id];
    }
    public static Object getStaticVariable(int id, String varName){ // retrieves image of object based on Id
        try {
            return IdToObject.getObjectFromId(id).getField(varName).get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getIdFromClass(Class c){
        try {
            return (int)c.getField("objectId").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getAllCraftables(){ // populates craftables
        int j = 0;
        for(int i = 0; i < numObjs; i++){
            if((boolean)getStaticVariable(i, "craftable")){
                craftables[j] = getObjectFromId(i);
                j++;
            }
        }
    }
    public static void getAllSellables(){ // populates sellables
        int j = 0;
        for(int i = 0; i < numObjs; i++){
            if((boolean)getStaticVariable(i, "sellable")){
                sellables[j] = getObjectFromId(i);
                j++;
            }
        }
    }

}
