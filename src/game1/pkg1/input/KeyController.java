package game1.pkg1.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author acob1
 */
public class KeyController implements KeyListener{
    private boolean[] keys;
    //private boolean[] keysFree;
    public static final int UP_KEY = KeyEvent.VK_UP, DOWN_KEY = KeyEvent.VK_DOWN, LEFT_KEY = KeyEvent.VK_LEFT, RIGHT_KEY = KeyEvent.VK_RIGHT;
    public static final int ACT1_KEY = KeyEvent.VK_A, ACT2_KEY = KeyEvent.VK_Z, BACK_KEY = KeyEvent.VK_ESCAPE;
    public boolean up, down, left, right, act1, act2;
    
    private boolean keyFree;
    public int keyDown;
    public KeyController(){
        keys = new boolean[256];
        //keysFree = new boolean[256];
        keyDown = -1;
        keyFree = true;
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        keyDown = e.getKeyCode();
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        keyFree = true;
        keyDown = -1;
    }
    
    public boolean isKeyDown(){
        if(keyDown<0){
            return false;
        }else{
            return true;
        }
    }
    
    public void setKeyFree(boolean state){
        keyFree = state;
    }
    
    public boolean isKeyFree(){
        return keyFree;
    }
    
    /*public boolean isKeyFree(int keyCode){
        return keysFree[keyCode];
    }*/
    
    public void tick(){
        up = keys[UP_KEY];
        down = keys[DOWN_KEY];
        left = keys[LEFT_KEY];
        right = keys[RIGHT_KEY];
        act1 = keys[ACT1_KEY];
        act2 = keys[ACT2_KEY];
    }
    
}
