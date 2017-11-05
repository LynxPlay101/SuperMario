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
     * Returns the center if the entity calcualted due to its hitbox
     *
     * @return the center
     */
    default Location getCenter() {
        return getLocation().clone().add(getHitbox().getX() / 2, -getHitbox().getY() / 2);
    }

    /**
     * Returns if the collidable is colliding
     *
     * @param other the object to hit
     * @return the result
     */
    default boolean isColliding(Collidable other) {
        Location loc = other.getLocation();
        Hitbox box = other.getHitbox();

        return (loc.getX() + box.getX() > getLocation().getX() &&
                loc.getY() - box.getY() < getLocation().getY() &&
                loc.getX() < getLocation().getX() + getHitbox().getX() &&
                loc.getY() > getLocation().getY() - getHitbox().getY());
    }

}
