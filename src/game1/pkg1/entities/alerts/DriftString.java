package game1.pkg1.entities.alerts;

import game1.pkg1.entities.Entity;
import game1.pkg1.gfx.Assets;
import game1.pkg1.gfx.Text;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author acob1
 */
public class DriftString extends Entity {

    protected String alert;
    protected byte tickTimes = 0;
    protected final byte TICK_DURATION = 60, DENOM_VAL = 3; //time in ticks
    protected final float DRIFT_RANGE = 30, DD_VAL = (8 - -DENOM_VAL*(float)Math.log(DRIFT_RANGE))/TICK_DURATION;
    protected float driftVal = -DENOM_VAL*(float)Math.log(DRIFT_RANGE), startX, startY; //driftVal is set to the y intercept of the natural log function (y = (-e^(-x/3)) + 15)]
    protected final Direction dir;
    protected boolean driftStop = false;
    protected boolean ended = false;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public DriftString(String message, float x, float y) {
        super(x, y, 40, 40);
        this.alert = message;
        startY = y;
        startX = x;
        dir = Direction.UP;
    }

    public DriftString(String message, float x, float y, Direction dir) {
        this.alert = message;
        this.x = x;
        this.y = y;
        startY = y;
        startX = x;
        this.dir = dir;
    }

    public DriftString(String message, float x, float y, int width, int height) {
        this.alert = message;
        this.x = x;
        this.y = y;
        startY = y;
        startX = x;
        this.dir = Direction.UP;
    }

    public DriftString(String message, float x, float y, int width, int height, Direction dir) {
        this.alert = message;
        this.x = x;
        this.y = y;
        startY = y;
        startX = x;
        this.dir = dir;
    }

    public boolean isEnded() {
        return ended;
    }

    @Override
    public void tick() {
        if (tickTimes <= TICK_DURATION) {
            switch (dir) {
                case UP:
                    y = startY - (float)-Math.pow(Math.E, (-driftVal/3)) + 15;
                    System.out.println(driftVal);
                    System.out.println((float)-Math.pow(Math.E, (-driftVal/1.5)) + DRIFT_RANGE);
                    //System.out.println(Math.pow(Math.E, (-7.93145)/1.5));
                    //y = startY - (float) ((-Math.pow(driftVal, 2) + DRIFT_RANGE));
                    break;
                case DOWN:
                    y = startY + (float) (DRIFT_RANGE * (Math.cos(driftVal) - 1));
                    break;
                case LEFT:
                    x = startX + (float) (DRIFT_RANGE * (Math.cos(-driftVal) - 1));
                    break;
                case RIGHT:
                    x = startX + (float) (DRIFT_RANGE * (Math.cos(driftVal) - 1));
                    break;
                default:
                    y = startY + (float) (DRIFT_RANGE * (Math.cos(-driftVal) - 1));
            }
            driftVal+=DD_VAL;
            tickTimes++;
            if (tickTimes > TICK_DURATION) {
                ended = true;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (tickTimes <= TICK_DURATION) {
            Text.drawString(g, alert, (int) x, (int) y, true, Color.BLUE, Assets.font28);
        }
    }
}
