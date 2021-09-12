package game1.pkg1.support;

import game1.pkg1.states.BattleState;

/**
 *
 * @author acob1
 */
public interface AbilityHandler {
    public int project(BattleState bs, Effect effect);
    public boolean apply(BattleState bs, Effect effect);
}
