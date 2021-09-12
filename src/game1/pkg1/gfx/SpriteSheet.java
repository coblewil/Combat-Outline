package game1.pkg1.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author acob1
 */
public class SpriteSheet {
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
    
    public int getSheetWidth(){
        return sheet.getWidth();
    }
    
    public int getSheetHeight(){
        return sheet.getHeight();
    }
}

