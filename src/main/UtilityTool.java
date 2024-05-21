package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UtilityTool {
    public static BufferedImage getImage(String filePath){
        try {
            return ImageIO.read(UtilityTool.class.getClassLoader().getResourceAsStream(filePath));
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
