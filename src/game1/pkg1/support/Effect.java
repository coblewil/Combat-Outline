package game1.pkg1.support;

/**
 *
 * @author acob1
 */
public class Effect {
    private final Mechanic effect;
    private final int strength;
    private final byte duration;
        
    public Effect(Mechanic effect, int strength, byte duration){
        this.effect = effect;
        this.strength = strength;
        this.duration = duration;
    }
    
    public Mechanic getMechanic(){
        return effect;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public byte getDuration(){
        return duration;
    }
}
