package me.lynxplay.supermario.engine.world.world;

import me.lynxplay.supermario.engine.graphics.CanvasLayer;
import me.lynxplay.supermario.engine.graphics.VoidCanvas;

import java.awt.image.BufferedImage;

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
        BufferedImage background = world.getBackground().getImage();
        int result = (int) (screen.getBounds().getWidth() / background.getWidth());
        for (int i = 0; i <= result; i++) {
            screen.getDrawnGraphics().drawImage(background, (int) background.getWidth() * i, 0, null);
        }

        world.getBlocks().forEach(b -> screen.drawImage(b.getBottomConrner(), b.getMaterial().getTexture()));
        world.getEntities().forEach(e -> screen.drawImage(e.getBottomConrner(), e.getGraphics().getDisplayedTexture()));
    }
}
