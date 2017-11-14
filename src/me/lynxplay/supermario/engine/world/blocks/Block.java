package me.lynxplay.supermario.engine.world.blocks;

import lombok.Getter;
import me.lynxplay.supermario.engine.world.blocks.material.Material;
import me.lynxplay.supermario.engine.world.entities.type.data.Hitbox;
import me.lynxplay.supermario.engine.world.location.Collidable;
import me.lynxplay.supermario.engine.world.location.Location;

public class Block implements Collidable {

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

    /**
     * Returns the bottom left cornor of the entity hitbox
     *
     * @return the location
     */
    @Override
    public Location getBottomConrner() {
        return getLocation();
    }

    /**
     * Returns the upper right cornor of the entity hitbox
     *
     * @return the location
     */
    @Override
    public Location getUpperConrner() {
        return getLocation().add(getHitbox().getX(), getHitbox().getY());
    }

    @Override
    public Location getLocation() {
        return location.clone();
    }
}
