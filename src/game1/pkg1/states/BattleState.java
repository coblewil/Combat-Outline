package game1.pkg1.states;

import game1.pkg1.Game;
import game1.pkg1.entities.alerts.Arrow;
import game1.pkg1.entities.alerts.DriftString;
import game1.pkg1.entities.creatures.Ally;
import game1.pkg1.entities.creatures.Enemy;
import game1.pkg1.entities.creatures.Fighter;
import game1.pkg1.gfx.Assets;
import game1.pkg1.gfx.Text;
import game1.pkg1.input.KeyController;
import game1.pkg1.support.Ability;
import game1.pkg1.support.BattleQueue;
import game1.pkg1.support.BattleWarehouse;
import game1.pkg1.worlds.Setting;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author acob1
 */
public class BattleState extends State {

    public ArrayList<Fighter> entities;
    public Fighter ally, enemy;
    public Fighter active;
    public Setting setting;
    public BattleQueue battleQueue;

    public int activeAnimationIndex;
    public int queueResetTick = 0;
    public int curtainRaiseTick = 0;
    public final int QUEUE_RESET_TIME = 6;
    public final int CURTAIN_RAISE_TIME = 150;

    public enum TickState {
        CURTAIN_RAISE, INTRODUCING, DECIDING_MOVE, DECIDING_TARGET, DECIDING_COMMIT, ANIMATING;
    }

    public TickState tickState;

    public int moveDecided;
    public Ability activeAbility;
    public Fighter target;

    public Arrow arrow;

    public ArrayList<DriftString> driftAlerts = new ArrayList<DriftString>(); //TEST
    public ArrayList<DriftString> driftGC = new ArrayList<DriftString>(); //TEST

    public BattleState(Game game, Ally ally, Enemy enemy) {
        super(game);
        this.ally = ally;
        this.enemy = enemy;
        entities = new ArrayList<Fighter>();
        entities.add(ally);
        entities.add(enemy);
        battleQueue = new BattleQueue();
        arrow = new Arrow(0, 0);

        init();
    }

    public BattleState(Game game, Ally ally) {
        super(game);
    }

    public void init() {
        //move all this to the battlequeue constructor
        int pTTM = 0, pSpeed = ally.getSpeed();
        int eTTM = 0, eSpeed = enemy.getSpeed();
        for (int i = 0; i < 8; i++) {
            pTTM += pSpeed;
            battleQueue.add(ally, pTTM);
            eTTM += eSpeed;
            battleQueue.add(enemy, eTTM);
        }
        active = battleQueue.getActive();
        //temp
        active.setRenderable(Fighter.Renderable.S);
        //END temp
        tickState = TickState.CURTAIN_RAISE;
        /*ArrayList<BattleOrderNode> a = battleQueue.getOrder();
        for (BattleOrderNode z : a) {
            System.out.printf("%s\t\t%d\n\t\t\t%f\n", z.getSoldier().getName(), z.getTTM(), z.getSoldier().getHealth());
        }*/
    }

    public void commit() {
        BattleWarehouse.commit(this, activeAbility);
    }

    public void checkWinCondition() {

    }

    public boolean end() {
        return true;
    }

    private void decideMove(int moveReq) {
        moveDecided = moveReq;
        System.out.printf("\nPlease choose between these targets:\n ");
        for (Fighter x : entities) {
            System.out.printf("\t%s\n", x.getName());
        }
        //temp
        if (active instanceof Ally) {
            target = enemy;
        } else if (active instanceof Enemy) {
            target = ally;
        }
        setTarget();
    }

    private void setTarget() {
        if (target != null) {
            if (target instanceof Enemy) {
                arrow.setArrow(target.getX() + target.getWidth() - 2 * arrow.getWidth(), target.getY() + 70, 0);
            } else if (target instanceof Ally) {
                arrow.setArrow(target.getX() + arrow.getWidth(), target.getY() + 70, 0);
            }
        }
    }

    private int decisionNumber(int keyCode) {
        int decision;
        switch (keyCode) {
            case KeyController.ACT1_KEY:
                decision = 0;
                active.setRenderable(Fighter.Renderable.M1);
                activeAbility = active.getMove(0); //TEMP
                break;
            case KeyController.ACT2_KEY:
                decision = 1;
                active.setRenderable(Fighter.Renderable.M2);
                activeAbility = active.getMove(1); //TEMP
                break;
            default:
                decision = 0;
        }
        return decision;
    }

    public void tick() {
        switch (tickState) {
            case CURTAIN_RAISE:
                curtainRaiseTick++;
                if (curtainRaiseTick > CURTAIN_RAISE_TIME) {
                    tickState = TickState.INTRODUCING;
                }
                break;
            case INTRODUCING:
                System.out.printf("\n%s\t\t%d\n\t\t%.2f\n", active.getName(), battleQueue.getOrder().get(0).getTTM(), active.getHealth());
                System.out.printf("\nDECIDE MOVE: press A or Z\n");
                if (queueResetTick >= QUEUE_RESET_TIME) {
                    queueResetTick = 0;
                    tickState = TickState.DECIDING_MOVE;
                }
                break;
            case DECIDING_MOVE: //should i make moves mapped to a button instead of selectable by arrow? that way, if two players play each other, the defender won't know which move to defend against based off what they see on the screen
                if (keyController.keyDown == KeyController.ACT1_KEY) {
                    decideMove(KeyController.ACT1_KEY);
                    keyController.setKeyFree(false);        //to keep from keyDown state when DECIDING_MOVE spilling over into DECIDING_TARGET     
                    tickState = TickState.DECIDING_TARGET;
                } else if (keyController.keyDown == KeyController.ACT2_KEY) {
                    decideMove(KeyController.ACT2_KEY);
                    tickState = TickState.DECIDING_TARGET;
                }
                break;
            case DECIDING_TARGET:
                switch (keyController.keyDown) {
                    case KeyController.LEFT_KEY:
                        if (target != ally) {
                            target = ally;
                            setTarget();
                        }
                        break;
                    case KeyController.RIGHT_KEY:
                        if (target != enemy) {
                            target = enemy;
                            setTarget();
                        }
                        break;
                }
                if (keyController.keyDown == KeyController.ACT1_KEY && keyController.isKeyFree()) {
                    //System.out.println("Deciding target hey");
                    activeAnimationIndex = decisionNumber(moveDecided);
                    //target = null;
                    battleQueue.isAnimDone = false;
                    tickState = TickState.ANIMATING;
                } else if (keyController.keyDown == KeyController.BACK_KEY && keyController.isKeyFree()) {
                    System.out.printf("\nBack...\nDECIDE MOVE: press A or Z\n");
                    //target = null;
                    tickState = TickState.DECIDING_MOVE;
                }
                break;
            case DECIDING_COMMIT:
                break;
            case ANIMATING:
                //temp
                active.getAnimation(activeAnimationIndex).tick();
                if (!battleQueue.isAnimDone) {
                    battleQueue.animate();
                }
                if (active.getAnimation(activeAnimationIndex).isEnded()) {
                    driftAlerts.add(new DriftString(activeAbility.name(), target.getX() + target.getWidth() / 2, target.getY() + target.getHeight() / 2)); //TEST
                    active.setRenderable(Fighter.Renderable.S);
                    commit();
                    battleQueue.commit();
                    active = battleQueue.getActive();
                    target = null;
                    battleQueue.animTick = 0;
                    tickState = TickState.INTRODUCING;
                }
                //END temp
                break;
        }
        for (DriftString x : driftAlerts) {
            x.tick();
            if (x.isEnded()) {
                driftGC.add(x);
            }
        }//TEST

        for (DriftString x : driftGC) {
            driftAlerts.remove(x);
        } //TEST

        //temp
        //perhaps have a move manager class handle the battle queue and the moves affecting it simultaneously
        /*if (decidingAction) {
            System.out.printf("%s\t\t%d\n\t\t%.2f\n", active.getName(), battleQueue.getOrder().get(0).getTTM(), active.getHealth());
            decidingAction = false;
        } else if (keyController.isKeyDown() && keyController.keyFree && !acting) {
            activeAnimationIndex = decisionNumber(keyController.keyDown);
            game.getKeyController().keyFree = false;
            //battleQueue.commit();
            animating = true;
            acting = true;
            //active = battleQueue.getActive();
            //deciding = true;
        }
        if (animating) {
            active.getAnimation(activeAnimationIndex).tick();
            if (active.getAnimation(activeAnimationIndex).isEnded()) {
                animating = false;
                active.setRenderable(Fighter.S);
                commit();
                battleQueue.commit();
                active = battleQueue.getActive();
                decidingAction = true;
                acting = false;
            }
        }
        /*else if(keyController.isKeyDown() && keyController.keyFree){
            game.getKeyController().keyFree = false;
            battleQueue.commit();
            active = battleQueue.getActive();
            deciding = true;
            //battleQueue.dump();
        }*/
        //END temp
    }

    //TO-DO put this in a render class
    public void render(Graphics g) {
        g.drawImage(ally.getRenderable(), (int) ally.getX(), (int) ally.getY(), ally.getWidth(), ally.getHeight(), null);
        g.drawImage(enemy.getRenderable(), (int) enemy.getX(), (int) enemy.getY(), enemy.getWidth(), enemy.getHeight(), null);

        if (target != null) {
            arrow.tick();
            arrow.render(g);
        }
        g.setColor(new Color(0, 200, 70));
        if (tickState == TickState.ANIMATING) {
            if (battleQueue.animTick < 60) {
                g.fillRect(game.displayWidth - battleQueue.getOrder().get(0).getIcon().getWidth() + 8, -((battleQueue.getOrder().get(0).getIcon().getHeight()) / 60 * battleQueue.animTick), battleQueue.getOrder().get(0).getIcon().getWidth() + 4, battleQueue.getOrder().get(0).getIcon().getHeight() + 4 + (battleQueue.animTick >= 60 ? -8 : 0));
            }
            for (int i = 0; i < 7; i++) {
                g.drawImage(battleQueue.getOrder().get(i).getIcon(), game.displayWidth - battleQueue.getOrder().get(i).getIcon().getWidth(), battleQueue.getOrder().get(i).getIcon().getHeight() * i - (((battleQueue.getOrder().get(i).getIcon().getHeight()) / 60) * battleQueue.animTick), null);
            }
        } else if (tickState == TickState.INTRODUCING) {
            g.fillRect(game.displayWidth - battleQueue.getOrder().get(0).getIcon().getWidth() + 8, -(battleQueue.getOrder().get(0).getIcon().getHeight()) + (battleQueue.getOrder().get(0).getIcon().getHeight()) / 6 * (queueResetTick++), battleQueue.getOrder().get(0).getIcon().getWidth() + 4, battleQueue.getOrder().get(0).getIcon().getHeight() + 4);
            for (int i = 0; i < 7; i++) {
                g.drawImage(battleQueue.getOrder().get(i).getIcon(), game.displayWidth - battleQueue.getOrder().get(i).getIcon().getWidth(), battleQueue.getOrder().get(i).getIcon().getHeight() * i, null);
            }
        } else if (tickState == TickState.CURTAIN_RAISE) {
            for (int i = 0; i < 7; i++) {
                g.drawImage(battleQueue.getOrder().get(i).getIcon(), game.displayWidth - battleQueue.getOrder().get(i).getIcon().getWidth(), battleQueue.getOrder().get(i).getIcon().getHeight() * i, null);
            }
        } else {
            queueResetTick = 0;
            g.fillRect(game.displayWidth - battleQueue.getOrder().get(0).getIcon().getWidth() + 8, 0, battleQueue.getOrder().get(0).getIcon().getWidth() + 4, battleQueue.getOrder().get(0).getIcon().getHeight() + 4);
            for (int i = 0; i < 7; i++) {
                g.drawImage(battleQueue.getOrder().get(i).getIcon(), game.displayWidth - battleQueue.getOrder().get(i).getIcon().getWidth(), battleQueue.getOrder().get(i).getIcon().getHeight() * i, null);
            }
        }

        if (tickState == TickState.DECIDING_MOVE) {
            if (active instanceof Ally) {
                Text.drawString(g, active.getName(), (int) active.getX() + active.getWidth() / 2 - 100, (int) active.getY() + active.getHeight() + 30, true, Color.BLACK, Assets.font28);
                Text.drawString(g, "" + active.getHealth(), (int) active.getX() + active.getWidth() / 2 - 100, (int) active.getY() + active.getHeight() + 60, true, Color.BLACK, Assets.font28);
            } else if (active instanceof Enemy) {
                Text.drawString(g, active.getName(), (int) active.getX() + active.getWidth() - 65, (int) active.getY() + active.getHeight() + 30, true, Color.BLACK, Assets.font28);
                Text.drawString(g, "" + active.getHealth(), (int) active.getX() + active.getWidth() - 65, (int) active.getY() + active.getHeight() + 60, true, Color.BLACK, Assets.font28);
            }
            for (int i = 0; i < 6; i++) {
                if (active.getMove(i) != null) {
                    if (active instanceof Ally) {
                        Text.drawString(g, "> " + active.getMove(i).name() + " <", (int) active.getX() + active.getWidth() / 2 - 100, (int) active.getY() + active.getHeight() + (i + 1) * 30 + 90, true, Color.RED, Assets.font28);
                    } else if (active instanceof Enemy) {
                        Text.drawString(g, "> " + active.getMove(i).name() + " <", (int) active.getX() + active.getWidth() - 65, (int) active.getY() + active.getHeight() + (i + 1) * 30 + 90, true, Color.RED, Assets.font28);
                    }
                }
            }
        }
        for (DriftString x : driftAlerts) {
            x.render(g); //TEST
        }
    }

}
