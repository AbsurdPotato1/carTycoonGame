package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectCopper extends SuperObject {
    public ObjectCopper(){
        name = "copperOre";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/copperOre.png"));
        } catch(IOException e){
            e.printStackTrace();
        }


//        solidAreaDefaultY = 0;
    }
}
