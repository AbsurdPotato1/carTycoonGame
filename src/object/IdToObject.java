package object;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class IdToObject {
    public static int numObjs = 3; // REMEMBER TO CHANGE THIS VALUE WHEN ADDING NEW OBJECTS!!!!
    public static final Class[] idObject = new Class[numObjs];
    public static void setIdObject(){
        idObject[0] = ToolPickaxe.class;
        idObject[1] = ObjectChest.class;
        idObject[2] = ObjectCopperOre.class;
    }
    public static Class getObjectFromId(int id){ // helper function
        return idObject[id];
    }
    public static BufferedImage getImageFromId(int id){ // retrieves image of object based on Id
        try {
            return (BufferedImage)IdToObject.getObjectFromId(id).getField("inventoryImage").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
