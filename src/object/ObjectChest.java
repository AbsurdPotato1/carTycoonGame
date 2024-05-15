package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject {
//    public static final String name = "chest";
    public static final Integer objectId = 1;
    public ObjectChest(){
//        objectId = 1;
        name = "chest";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        description = "Chest\nHolds a couple things";
        collision = true;
    }
}
