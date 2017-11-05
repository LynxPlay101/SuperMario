package me.lynxplay.supermario.engine.runnables;

import me.lynxplay.supermario.engine.VoidGame;
import me.lynxplay.supermario.engine.world.world.World;

public class VoidGameLoop implements Runnable {

    public static final int MAX_FRAMES = 60;
    public static final int UPDATES_PER_SEK = 60;

    private boolean running;
    private Thread master;
    private VoidGame game;

    public VoidGameLoop(VoidGame game) {
        this.game = game;
        this.master = new Thread(this);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double nanoSecondsForUpdate = 1000000000.0 / UPDATES_PER_SEK;
        final double nanoSecondsFroRender = 1000000000.0 / MAX_FRAMES;

        double deltaUpdate = 0;
        double deltaFrames = 0;

        while (running) {

            long now = System.nanoTime();

            deltaUpdate += (now - lastTime) / nanoSecondsForUpdate;
            deltaFrames += (now - lastTime) / nanoSecondsFroRender;

            lastTime = now;

            while (deltaUpdate >= 1) {
                update();
                deltaUpdate--;
            }
            while (deltaFrames >= 1) {
                render();
                deltaFrames--;
            }

        }

        System.out.println("VoidGameLoop shutdown");
    }

    /**
     * Starts the game loop
     */
    public void start() {
        this.running = true;
        this.master.start();
    }

    /**
     * Stops the game loop
     */
    public void stop() {
        running = false;
    }

    /**
     * Renders the screen
     */
    private void render() {
        game.getCanvas().render();
    }

    private void update() {
        game.getKeyboard().executeEvents();

        World world = game.getWorld();
        if(world != null) {
            world.tick();
        }
    }

}
