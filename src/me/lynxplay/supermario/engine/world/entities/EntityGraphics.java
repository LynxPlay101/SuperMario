package me.lynxplay.supermario.engine.world.entities;

import me.lynxplay.supermario.engine.graphics.texture.Texture;
import me.lynxplay.supermario.engine.graphics.texture.TextureArray;

import java.util.HashMap;
import java.util.Optional;

public class EntityGraphics {

    private int ticks;
    private Entity entity;
    private HashMap<String, TextureArray> animationSource;

    private Texture lastTexture;

    public EntityGraphics(Entity entity, HashMap<String, TextureArray> animationSource) {
        this.entity = entity;
        this.animationSource = animationSource;
        this.lastTexture = Optional.ofNullable(animationSource.get("walking_left")).map(t -> t.getTexture(0)).orElse(null);
    }

    /**
     * Returns the texture that has to be displayed at the momemnt the methode is called
     *
     * @return the texture
     */
    public Texture getDisplayedTexture() {
        double x = entity.getVector().getX();
        String id = "walking_" + (x > 0 ? "right" : (x < 0 ? "left" : "idle"));

        Texture texture = Optional.ofNullable(animationSource.get(id)).map(t -> t.getTexture(0)).orElse(null);
        return texture != null ? (this.lastTexture = texture) : lastTexture;
    }
}
