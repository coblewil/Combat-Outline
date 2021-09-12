package game1.pkg1.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


/**
 *
 * @author acob1
 */
public class FontLoader {
    
    public static Font loadFont(String path, float size){
        try {
            InputStream in = FontLoader.class.getResourceAsStream("/"+path); //custom REVIEW THIS
            return Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(Font.PLAIN, size); //custom REVIEW THIS
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
