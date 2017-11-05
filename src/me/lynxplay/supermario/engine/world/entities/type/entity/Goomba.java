package me.lynxplay.supermario.engine.world.entities.type.entity;

import me.lynxplay.supermario.engine.world.entities.Entity;
import me.lynxplay.supermario.engine.world.entities.type.EntityType;
import me.lynxplay.supermario.engine.world.location.Location;

public class Goomba extends Entity {

    public Goomba(Location location) {
        super(location, EntityType.GOOMBA);
    }

}
