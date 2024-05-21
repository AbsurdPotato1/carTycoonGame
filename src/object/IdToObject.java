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
}
