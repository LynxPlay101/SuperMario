package me.lynxplay.supermario.engine.world.location;

import me.lynxplay.supermario.engine.world.entities.type.data.Hitbox;

public interface Collidable extends Locatable {

    /**
     * Returns the hitbox of the object
     *
     * @return the hitbox
     */
    Hitbox getHitbox();

    /**
     * Returns if the collidable is colliding
     *
     * @param other the object to hit
     * @return the result
     */
    default boolean isColliding(Collidable other) {
        Location loc = other.getBottomConrner();
        Location thisLoc = getBottomConrner();
        Hitbox box = other.getHitbox();

        return (loc.getX() + box.getX() > thisLoc.getX() &&
                loc.getY() - box.getY() < thisLoc.getY() &&
                loc.getX() < thisLoc.getX() + getHitbox().getX() &&
                loc.getY() > thisLoc.getY() - getHitbox().getY());
    }

    /**
     * Returns the bottom left cornor of the entity hitbox
     *
     * @return the location
     */
    Location getBottomConrner();

    /**
     * Returns the upper right cornor of the entity hitbox
     *
     * @return the location
     */
    Location getUpperConrner();
}
