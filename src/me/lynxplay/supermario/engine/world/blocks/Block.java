package me.lynxplay.supermario.engine.world.blocks;

import lombok.Getter;
import me.lynxplay.supermario.engine.world.blocks.material.Material;
import me.lynxplay.supermario.engine.world.entities.type.data.Hitbox;
import me.lynxplay.supermario.engine.world.location.Collidable;
import me.lynxplay.supermario.engine.world.location.Location;

public class Block implements Collidable {

    @Getter
    private Location location;

    @Getter
    private Material material;

    public Block(Location location, Material material) {
        this.location = location;
        this.material = material;
    }

    @Override
    public Hitbox getHitbox() {
        return material.getHitbox();
    }
}
