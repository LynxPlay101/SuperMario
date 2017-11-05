package me.lynxplay.supermario.engine.world.entities;

import me.lynxplay.supermario.engine.graphics.texture.Texture;
import me.lynxplay.supermario.engine.graphics.texture.TextureArray;

import java.util.HashMap;

public class EntityGraphics {

    private int ticks;
    private HashMap<String, TextureArray> animationSource;

    public EntityGraphics(HashMap<String, TextureArray> animationSource) {
        this.animationSource = animationSource;
    }

    /**
     * Returns the texture that has to be displayed at the momemnt the methode is called
     *
     * @return the texture
     */
    public Texture getDisplayedTexture() {
        return animationSource.values().stream().findAny().map(t -> t.getTexture(0)).orElse(null);
    }
}
