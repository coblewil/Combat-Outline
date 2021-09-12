package game1.pkg1.support;

import game1.pkg1.entities.creatures.Fighter;
import game1.pkg1.states.BattleState;

/**
 *
 * @author acob1
 */
public class BattleHandlers{
    
    public static final class DealFlatDamage implements AbilityHandler{

        public int project(BattleState bs, Effect effect) {
            return 0;
        }

        public boolean apply(BattleState bs, Effect effect) {
            Fighter tar = bs.target;
            tar.takeDamage(effect.getStrength());
            return true;
        }    
    }
}
