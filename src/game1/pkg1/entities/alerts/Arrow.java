package game1.pkg1.entities.alerts;

import game1.pkg1.entities.Entity;
import game1.pkg1.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author acob1
 */
public class Arrow extends Entity {
        protected BufferedImage icon = Assets.arrow;
        protected float bounceVal, bounceDelta, startY;
        protected final float BD_VAL = .2f, BOUNCE_RANGE = 10;
        
        public Arrow(float x, float y){
            this(x,y,40, 40);
        }
        public Arrow(float x, float y, int width, int height){
            super(x,y,width,height);
            bounceVal = 0;
            startY = y;
        }
        
        public void setArrow(float x, float y, float bounceVal){
            this.x = x;
            this.y = y;
            this.bounceVal = bounceVal;
            startY = y;
        }
        
        public void tick() {
            y = startY+(float)(BOUNCE_RANGE*(Math.cos(-bounceVal)-1));
            if(bounceVal >= Math.PI){
                bounceDelta = -BD_VAL;
            }
            if(bounceVal <= 0){
                bounceDelta = BD_VAL;
            }
            bounceVal += bounceDelta;
        }

        public void render(Graphics g) {
            g.drawImage(icon, (int)x, (int)y, width, height, null);
        }
        
    }
