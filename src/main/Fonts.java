package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class Fonts {
    public static Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    public static Font montserrat = Fonts.loadFont("fonts/Montserrat-VariableFont_wght.ttf");
    public static Font pressStart_2P = Fonts.loadFont("fonts/PressStart2P-Regular.ttf");
    public static Font pressStart_2P_strikethrough;


    public static Font loadFont(String path){
        try {
            InputStream fontStream = UI.class.getClassLoader().getResourceAsStream(path);
            Font temp = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(temp);
            return temp;
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setStrikethrough(){
        Map<TextAttribute, Object> attributes = new HashMap<>(pressStart_2P.getAttributes());
        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        pressStart_2P_strikethrough = pressStart_2P.deriveFont(attributes);
    }

}
