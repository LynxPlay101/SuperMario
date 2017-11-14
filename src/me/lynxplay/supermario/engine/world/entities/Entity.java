package me.lynxplay.supermario.engine.world.entities;

import lombok.Getter;
import me.lynxplay.supermario.engine.util.MathUtils;
import me.lynxplay.supermario.engine.world.blocks.Block;
import me.lynxplay.supermario.engine.world.entities.type.EntityType;
import me.lynxplay.supermario.engine.world.entities.type.data.Hitbox;
import me.lynxplay.supermario.engine.world.location.Collidable;
import me.lynxplay.supermario.engine.world.location.Location;
import me.lynxplay.supermario.engine.world.location.Vector;

@Getter
public class Entity implements Collidable {

    private final double maxSpeed;
    private Location location;
    private EntityGraphics graphics;
    private EntityType type;
    private Hitbox hitbox;

    private Vector vector = new Vector(0, 0);

    private boolean dead;

    public Entity(Location location, EntityType type) {
        this.location = location;
        this.type = type;
        this.graphics = new EntityGraphics(type.getData().getAnimationSet());
        this.hitbox = type.getData().getHitbox();
        this.maxSpeed = type.getData().getMovementSpeed();
    }

    /**
     * Returns the bottom left cornor of the entity hitbox
     *
     * @return the location
     */
    @Override
    public Location getBottomConrner() {
        return getLocation().clone().add(-getHitbox().getX() / 2, getHitbox().getY());
    }

    /**
     * Returns the upper right cornor of the entity hitbox
     *
     * @return the location
     */
    @Override
    public Location getUpperConrner() {
        return getLocation().clone().add(getHitbox().getX() / 2, 0);
    }

    /**
     * Returns the next block below the entitiy
     *
     * @return the location of the block below
     */
    public Block getBlockBelow() {
        return getLocation().clone().add(0, -1).toBlock();
    }


    /**
     * Returns if the entity is on the ground
     *
     * @return the if entity is on the ground
     */
    public boolean isOnGround() {
        Block ground = getLocation().clone().subtract(0, .05).toBlock();
        return ground.getMaterial().isSolid();
    }

    /**
     * Moves the entity
     *
     * @param x the x way to move
     * @param y the y way to move
     */
    public void teleport(double x, double y) {
        teleport(new Location(x, y));
    }

    /**
     * Teleports the entity to the given location
     *
     * @param target the target location
     */
    public void teleport(Location target) {
        this.location = target;
    }

    /**
     * Sets the entities vector
     *
     * @param vector the vector
     */
    public void setVector(Vector vector) {
        this.vector = vector;
    }

    /**
     * Moves the entity that way
     *
     * @param x the x
     * @param y the y
     */
    public void move(double x, double y) {
        vector.setX(MathUtils.constrain(-maxSpeed, maxSpeed, vector.getX() + x));
        vector.setY(vector.getY() + y);
    }

    /**
     * This methode will fix the entites location if it currently colliding with a block
     */
    public void fixLocation() {

    }
}
