package game1.pkg1.entities.creatures;

import game1.pkg1.gfx.Animation;
import game1.pkg1.support.Ability;
import game1.pkg1.support.BattleWarehouse;
import game1.pkg1.support.Spire;
import game1.pkg1.support.Weapon;
import java.awt.image.BufferedImage;

public abstract class Fighter extends Creature{
    public enum Renderable{ S,M1,M2,M3,M4,M5,M6;}
    private Renderable curRenderable = Renderable.S;

    Weapon weapon;
    int speed;
    float magic, maxMagic, stamina, maxStamina;
    float healthRegen, magicRegen, staminaRegen;
    Ability[] abilities = new Ability[6];
    Animation[] abilityAnims = new Animation[6];
    BufferedImage icon, fightingSprite;
    //temp
    //make a battleform object to hold all battle related stuff, separate from the larger character class
    public Fighter(int id, String name, float health, int speed, int[] abilityReq, Spire spire){
        super(id, name, health);
        this.speed = speed;
        this.spire = spire;
        BattleWarehouse.calculateSprites(this);
        BattleWarehouse.joinAbility(this, abilityReq);
    }
    //END temp
    
    public Fighter(float x, float y, int width, int height, String name, Spire spire, float maxHealth, BufferedImage sprite, int[] movesReq, Weapon weapon, BufferedImage fightingSprite) { //accept arrays for like variables, i.e. health, magic, stamina & maxHealth, maxMagic, maxStamina
        super(x, y, width, height, name, spire, maxHealth, sprite);
        BattleWarehouse.joinAbility(this, movesReq);
        this.fightingSprite = fightingSprite;
    }

    public Fighter(float x, float y, int width, int height, String name, Spire spire, float maxHealth, float curHealth, BufferedImage sprite) {
        super(x, y, width, height, name, spire, maxHealth, curHealth, sprite);
    }
    
    public void takeDamage(float damage){
        health -= damage;
        if(health<=0){
            kill();
        }
    }
    
    public void takeHeal(float recovery){
        health+=recovery;
        if(health > maxHealth){
            health = maxHealth;
        }
    }
    
    public void kill(){
        health = 0;
        alive = false;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public BufferedImage getIcon(){
        return icon;
    }
    
    public BufferedImage getSprite(){
        return fightingSprite;
    }
    
    public Ability[] getMoves(){
        return abilities;
    }
    
    public Ability getMove(int index){
        return abilities[index];
    }
        
    //temp
    public Animation getAnimation(int index){
        //temp
        return abilityAnims[index];
        //END temp
    }
    
    public Spire getSpire(){
        return spire;
    }
    //END temp
    
    //temp
    public BufferedImage getRenderable(){
        BufferedImage renderable;
        switch(curRenderable){
            case S:
                renderable = fightingSprite;
                break;
            case M1:
                renderable = abilityAnims[0].getCurrentFrame();
                break;
            case M2:
                renderable = abilityAnims[1].getCurrentFrame();
                break;
            case M3:
                renderable = abilityAnims[2].getCurrentFrame();
                break;
            case M4:
                renderable = abilityAnims[3].getCurrentFrame();
                break;
            case M5:
                renderable = abilityAnims[4].getCurrentFrame();
                break;
            case M6:
                renderable = abilityAnims[5].getCurrentFrame();
                break;
            default:
                renderable = fightingSprite;
        }
        return renderable;
    }
    
    public void setRenderable(Renderable renderableNo){
        curRenderable = renderableNo;
    }
    
    public void setAbility(int index, Ability ability, Animation anim){
        abilities[index] = ability;
        abilityAnims[index] = anim;
    }
    
    public void setFightingSprite(BufferedImage sprite){
        this.fightingSprite = sprite;
    }
    
    public void setIcon(BufferedImage icon){
        this.icon = icon;
    }
    //END temp
}
