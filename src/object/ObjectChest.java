package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject {
    public ObjectChest(){
        name = "copperOre";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
