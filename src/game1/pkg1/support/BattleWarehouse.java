package game1.pkg1.support;

import game1.pkg1.entities.creatures.Ally;
import game1.pkg1.entities.creatures.Enemy;
import game1.pkg1.entities.creatures.Fighter;
import game1.pkg1.gfx.Animation;
import game1.pkg1.gfx.Assets;
import game1.pkg1.states.BattleState;
import java.util.Map;

/**
 *
 * @author acob1
 */
public class BattleWarehouse { //move to Ability?

    public static void joinAbility(Fighter fighter, int[] abilityReq) {
        for (int i = 0; i < 6; i++) {
            if (abilityReq[i] > 0) { //a null ability is represented by a -1
                Ability ability = Ability.lookUpAbility(abilityReq[i]);
                Animation anim = calculateAnim(fighter, ability);

                fighter.setAbility(i, ability, anim);
            }
        }
    }

    public static void calculateSprites(Fighter fighter) {
        int robeCode = fighter.getSpire().getTypeCode() % 4;
        switch (robeCode) {
            case 0:
                fighter.setIcon(Assets.greenIcon);
                if (fighter instanceof Ally) {
                    fighter.setFightingSprite(Assets.greenStandingR);
                } else if (fighter instanceof Enemy) {
                    fighter.setFightingSprite(Assets.greenStandingL);
                }
                break;
            case 1:
                fighter.setIcon(Assets.redIcon);
                if (fighter instanceof Ally) {
                    fighter.setFightingSprite(Assets.redStandingR);
                } else if (fighter instanceof Enemy) {
                    fighter.setFightingSprite(Assets.redStandingL);
                }
                break;
            case 2:
                fighter.setIcon(Assets.blueIcon);
                if (fighter instanceof Ally) {
                    fighter.setFightingSprite(Assets.blueStandingR);
                } else if (fighter instanceof Enemy) {
                    fighter.setFightingSprite(Assets.blueStandingL);
                }
                break;
            case 3:
                fighter.setIcon(Assets.yellowIcon);
                if (fighter instanceof Ally) {
                    fighter.setFightingSprite(Assets.yellowStandingR);
                } else if (fighter instanceof Enemy) {
                    fighter.setFightingSprite(Assets.yellowStandingL);
                }
                break;
        }
    }

    private static Animation calculateAnim(Fighter fighter, Ability ability) {
        Animation req = null;
        int robeCode = fighter.getSpire().getTypeCode() % 4;
        switch (robeCode) {
            case 0:
                switch (ability.getAnimCode()) {
                    case 1:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.greenA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.greenA1LAnim;
                        }
                        break;
                    case 2:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.greenA2RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.greenA2LAnim;
                        }
                        break;
                    default:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.greenA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.greenA1LAnim;
                        }
                        break;
                }
                break;
            case 1:
                switch (ability.getAnimCode()) {
                    case 1:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.redA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.redA1LAnim;
                        }
                        break;
                    case 2:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.redA2RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.redA2LAnim;
                        }
                        break;
                    default:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.redA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.redA1LAnim;
                        }
                        break;
                }
                break;
            case 2:
                switch (ability.getAnimCode()) {
                    case 1:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.blueA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.blueA1LAnim;
                        }
                        break;
                    case 2:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.blueA2RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.blueA2LAnim;
                        }
                        break;
                    default:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.blueA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.blueA1LAnim;
                        }
                        break;
                }
                break;
            case 3:
                switch (ability.getAnimCode()) {
                    case 1:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.yellowA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.yellowA1LAnim;
                        }
                        break;
                    case 2:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.yellowA2RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.yellowA2LAnim;
                        }
                    default:
                        if (fighter instanceof Ally) {
                            req = Assets.Animations.yellowA1RAnim;
                        } else if (fighter instanceof Enemy) {
                            req = Assets.Animations.yellowA1LAnim;
                        }
                        break;
                }
                break;
        }
        return req;
    }

    public static void commit(BattleState bs, Ability ability) {
        for (Effect effect : ability.getEffects()) {
            switch (effect.getMechanic()) {
                case F_DAMAGE1:
                    new BattleHandlers.DealFlatDamage().apply(bs, effect);
                    break;
                case F_DAMAGE2:
                    break;
                case F_DAMAGE3:
                    break;
                case F_DAMAGE3SPLASH:
                    break;
                case F_DAMAGEALL:
                    break;
                case F_DAMAGEALLBUTME:
                    break;
                case O_DAMAGE1:
                    break;
                case O_DAMAGE2:
                    break;
                case O_DAMAGE3:
                    break;
                case O_DAMAGE3SPLASH:
                    break;
                case O_DAMAGEALL:
                    break;
                case O_DAMAGEALLBUTME:
                    break;
                case A_DAMAGE1:
                    break;
                case A_DAMAGE2:
                    break;
                case A_DAMAGE3:
                    break;
                case A_DAMAGE3SPLASH:
                    break;
                case A_DAMAGEALL:
                    break;
                case A_DAMAGEALLBUTME:
                    break;
                case H_DAMAGE1:
                    break;
                case H_DAMAGE2:
                    break;
                case H_DAMAGE3:
                    break;
                case H_DAMAGE3SPLASH:
                    break;
                case H_DAMAGEALL:
                    break;
                case H_DAMAGEALLBUTME:
                    break;
                case HEAL1:
                    break;
                case HEAL3:
                    break;
                case REVIVE1:
                    break;
                case SLOW_ONCE:
                    System.out.println("Sloooooooooooowed");
                    break;
                case QUICKEN_ONCE:
                    break;
                case CORRUPT_ONCE:
                    break;
                case STUN_ONCE:
                    break;
                case SLOW_LONG:
                    break;
                case QUICKEN_LONG:
                    break;
                case CORRUPT_LONG:
                    break;
                case STUN_LONG:
                    break;
                default:
                    throw new AssertionError(effect.getMechanic().name());
            }
        }
    }
}
