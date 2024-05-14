package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public final class Fonts {
    static Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    static Font montserrat = Fonts.loadFont("fonts/Montserrat-VariableFont_wght.ttf");
    static Font pressStart_2P = Fonts.loadFont("fonts/PressStart2P-Regular.ttf");



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

}
