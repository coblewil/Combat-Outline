package game1.pkg1.entities;

import java.awt.Graphics;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    
    public Entity(){
        //temp
        width = 320;
        height = 255;
        //END temp
    }
    
    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}