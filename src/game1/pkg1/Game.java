package game1.pkg1;

import game1.pkg1.entities.creatures.Ally;
import game1.pkg1.entities.creatures.Enemy;
import game1.pkg1.gfx.Assets;
import game1.pkg1.input.KeyController;
import game1.pkg1.states.BattleState;
import game1.pkg1.states.GameState;
import game1.pkg1.states.MenuState;
import game1.pkg1.states.State;
import game1.pkg1.support.Move;
import game1.pkg1.support.Spire;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author acob1
 */
public class Game implements Runnable {

    private Display display;
    public int displayWidth, displayHeight;
    public String displayTitle;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bufferStrategy;
    private Graphics g;

    private State gameState;
    private State menuState;
    private State battleState;

    //input
    private KeyController keyController;

    public Game(String title, int width, int height) {
        this.displayTitle = title;
        this.displayWidth = width;
        this.displayHeight = height;
        keyController = new KeyController();
    }

    private void init() {
        display = new Display(displayTitle, displayWidth, displayHeight);
        display.getJFrame().addKeyListener(keyController);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        //temp
        int[] moves = {1,2,7,8,-1,-1};
        Ally a = new Ally(0,"Player", 100, 3, moves, Spire.FIRE);
        moves = new int[]{3,4,5,6, -1, -1};
        Enemy e = new Enemy(1, "Enemy", 100, 4, moves, Spire.WATER);
        battleState = new BattleState(this, a, e);
        State.setState(battleState);
        //END temp
    }

    public KeyController getKeyController() {
        return keyController;
    }

    private void tick() {
        keyController.tick();
        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, displayWidth, displayHeight);
        if (State.getState() != null) {
            State.getState().render(g);
        }
        bufferStrategy.show();
        g.dispose();
    }

    public void run() {
        init();

        final int fps = 60;

        double timePerTick = 1000000000 / fps; // number of nanoseconds in each tick
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
