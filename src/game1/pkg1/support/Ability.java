package game1.pkg1.support;

import java.util.ArrayList;

/**
 *
 * @author acob1
 */
public enum Ability {
    NOTHING("Nothing", (short) 0, (byte)1, 0, (byte)1, Spire.NORMAL,new Effect[]{}),
    FLAME("Flame", (short) 1, (byte)1, 0, (byte)1, Spire.FIRE, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 5, (byte)0)}),
    BLAZE("Blaze", (short) 2, (byte)1, 3, (byte)2, Spire.FIRE, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 10, (byte)0), new Effect(Mechanic.SLOW_ONCE, 3, (byte) 0)}),
    DROP("Drop", (short) 3, (byte)1, 0, (byte)1, Spire.WATER, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 5, (byte)0)}),
    DRAIN("Drain", (short) 4, (byte)1, 3, (byte)2, Spire.WATER, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 10, (byte)0), new Effect(Mechanic.SLOW_ONCE, 3, (byte) 0)}),
    GUST("Gust", (short) 5, (byte)1, 0, (byte)1, Spire.WIND, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 5, (byte)0)}),
    GALE("Gale", (short) 6, (byte)1, 3, (byte)2, Spire.WIND, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 10, (byte)0), new Effect(Mechanic.SLOW_ONCE, 3, (byte) 0)}),
    PEBBLE("Pebble", (short) 7, (byte)1, 0, (byte)1, Spire.EARTH, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 5, (byte)0)}),
    STONE("Stone", (short) 8, (byte)1, 3, (byte)2, Spire.EARTH, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 10, (byte)0), new Effect(Mechanic.SLOW_ONCE, 3, (byte) 0)}),
    DAISY_CHAIN("Daisy Chain", (short) 8, (byte)1, 3, (byte)2, Spire.EARTH, new Effect[]{new Effect(Mechanic.F_DAMAGE1, 10, (byte)0), new Effect(Mechanic.SLOW_ONCE, 3, (byte) 0)});
    
    ;
    
    private final String name;
    private final short abilityCode;
    private byte level;
    private int cost;
    private final byte animCode;
    private final Spire spire;
    private ArrayList<Effect> effects;
    
    Ability(String name, short abilityCode, byte level, int cost, byte animCode, Spire spire, Effect[] effects){
        this.name = name;
        this.abilityCode = abilityCode;
        this.level = level;
        this.cost = cost;
        this.animCode = animCode;
        this.spire = spire;
        this.effects = new ArrayList<Effect>();
        for(Effect x : effects){
            this.effects.add(x);
        }
    }
    
    public void addEffect(Effect newEffect){
        effects.add(newEffect);
    }
    
    public void levelUp(){
        level+=1;
    }
    
    public void changeCost(int cost){
        this.cost -= cost;
    }
    
    public static Ability lookUpAbility(int req) {
        Ability result = null;
        for (Ability x : values()) {
            if (req == x.abilityCode) {
                result = x;
            }
        }
        return result;
    }
    
    public byte getAnimCode(){
        return this.animCode;
    }
    
    public ArrayList<Effect> getEffects(){
        return effects;
    }
}
