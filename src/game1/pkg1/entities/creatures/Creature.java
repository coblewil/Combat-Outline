package game1.pkg1.entities.creatures;

import game1.pkg1.entities.Entity;
import game1.pkg1.support.Spire;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author acob1
 */
public abstract class Creature extends Entity{
    long id;
    String name;
    Spire spire;
    float health, maxHealth;
    boolean alive;
    public BufferedImage worldSprite;
    
    //temp
    public Creature(int id, String name, float health){
        this.id = id;
        this.name = name;
        this.health = health;
    }
    //END temp

    public Creature(float x, float y, int width, int height, String name, Spire spire, float maxHealth, BufferedImage sprite) {
        super(x, y, width, height);
        this.name = name;
        this.spire = spire;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.worldSprite = sprite;
    }
    
    public Creature(float x, float y, int width, int height, String name, Spire spire, float maxHealth, float curHealth, BufferedImage sprite) {
        this(x,y, height, width, name, spire, maxHealth, sprite);
        this.health = curHealth;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public String getName(){
        return name;
    }
  
    public long getId(){
        return id;
    }
    
    public float getHealth(){
        return health;
    }
}
