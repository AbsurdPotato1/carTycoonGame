package object;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class IdToObject {
    public static final Class[] idObject = new Class[255];
    public static void setIdObject(){
        idObject[0] = ObjectCopperOre.class;
        idObject[1] = ObjectChest.class;
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
