package game1.pkg1.states;

import game1.pkg1.Game;
import game1.pkg1.input.KeyController;
import java.awt.Graphics;

/**
 *
 * @author acob1
 */
public abstract class State {
    private static State currentState = null;
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    
    //CLASS
    
    protected Game game;
    protected KeyController keyController;
    
    public State(Game game){
        this.game = game;
        this.keyController = game.getKeyController();
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
