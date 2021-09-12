package game1.pkg1.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author acob1
 */
public class Animation {
    int speed, index;
    long lastTime, timer;
    private BufferedImage[] frames;
    
    public Animation(BufferedImage[] frames, int speed){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public boolean isEnded(){
        if(index == frames.length - 1){
            index = 0;
            return true;
        }else{
            return false;
        }
    }
    
    public void tick(){
        timer+=System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed){
            index++;
            timer = 0;
            if(index>=frames.length){
                index = 0;
            }
        }
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
