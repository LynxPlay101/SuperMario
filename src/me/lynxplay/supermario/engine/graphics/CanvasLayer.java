package me.lynxplay.supermario.engine.graphics;

@FunctionalInterface
public interface CanvasLayer {

    /**
     * Renders the layers
     * @param screen the screen to render on
     */
    void render(VoidCanvas screen);

}
