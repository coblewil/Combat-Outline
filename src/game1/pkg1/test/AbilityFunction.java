package game1.pkg1.test;

import game1.pkg1.states.BattleState;
import game1.pkg1.support.Ability;

/**
 *
 * @author acob1
 */
@FunctionalInterface
public interface AbilityFunction {
    public void perform(BattleState bs, Ability ability);
}
