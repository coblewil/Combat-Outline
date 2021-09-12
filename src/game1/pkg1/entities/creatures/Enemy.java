package game1.pkg1.entities.creatures;

import game1.pkg1.entities.creatures.Fighter;
import game1.pkg1.gfx.Animation;
import game1.pkg1.gfx.Assets;
import game1.pkg1.support.Spire;
import game1.pkg1.support.Weapon;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author acob1
 */
public class Enemy extends Fighter{
    
    //temp
    public Enemy(int id, String name, float health, int speed, int[] movesReq, Spire spire){ //temp constructor, pass to super class
        super(id, name, health, speed, movesReq, spire);
        x = 270; //let a battle state handler decide the x location
    }
    //END temp

    public Enemy(float x, float y, int width, int height, String name, Spire spire, float maxHealth, BufferedImage sprite, int[] movesReq, Weapon weapon, BufferedImage fightingSprite) {
        super(x, y, width, height, name, spire, maxHealth, sprite, movesReq, weapon, fightingSprite);
    }

    public Enemy(float x, float y, int width, int height, String name, Spire spire, float maxHealth, float curHealth, BufferedImage sprite) {
        super(x, y, width, height, name, spire, maxHealth, curHealth, sprite);
    }

    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
