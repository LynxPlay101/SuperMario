package me.lynxplay.supermario.engine.world.world;

import me.lynxplay.supermario.engine.graphics.CanvasLayer;
import me.lynxplay.supermario.engine.graphics.VoidCanvas;

public class WorldRender implements CanvasLayer {

    private final World world;

    public WorldRender(World world) {
        this.world = world;
    }

    /**
     * Renders the layers
     *
     * @param screen the screen to render on
     */
    @Override
    public void render(VoidCanvas screen) {
        world.getBlocks().forEach(b -> screen.drawImage(b.getLocation(), b.getMaterial().getTexture()));
        world.getEntities().forEach(e -> screen.drawImage(e.getLocation() , e.getGraphics().getDisplayedTexture()));
    }
}
