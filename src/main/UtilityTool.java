package main;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UtilityTool {
    public static BufferedImage getImage(String filePath){
        try {
            return ImageIO.read(UtilityTool.class.getClassLoader().getResourceAsStream(filePath));
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static int getStringWidth(Graphics2D g2, String text){
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }
    public static int getStringHeight(Graphics2D g2, String text){
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
    }
    public static int getStringArrHeight(Graphics2D g2, ArrayList<String> text, int margin){
        int width = 0;
        for(String str : text){
            width += getStringHeight(g2, str);
        }
        return width + (text.size()-1) * (margin);
    }

    public static ArrayList<String> getFitFormatString(Graphics2D g2, String text, int maxWidth){
        // returns an ArrayList of strings that fit within the space specified.
        String[] textArr = text.split(" ");
        ArrayList<String> res = new ArrayList<>();
        int j = 0;
        while (j < textArr.length) {
            String curString = "";
            if(j < textArr.length && getStringWidth(g2, textArr[j] + " ") >= maxWidth){ // handle edge case: one word is longer than entire space.
                curString += textArr[j] + " ";
                j++;
            } else {
                while (j < textArr.length && getStringWidth(g2, curString + textArr[j] + " ") < maxWidth) {
                    curString += textArr[j] + " ";
                    j++;
                }
            }
            res.add(curString);
        }
        return res;
    }
}
