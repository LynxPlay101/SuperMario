package me.lynxplay.supermario.engine.world.entities.type;

import lombok.Getter;
import lombok.Setter;
import me.lynxplay.supermario.engine.world.entities.Entity;
import me.lynxplay.supermario.engine.world.entities.type.data.EntityTypeData;
import me.lynxplay.supermario.engine.world.entities.type.entity.Goomba;
import me.lynxplay.supermario.engine.world.location.Location;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public enum EntityType {
    GOOMBA("goomba", Goomba::new);

    private String id;
    private Function<Location, Entity> creator;

    @Getter
    @Setter
    private EntityTypeData data;

    EntityType(String id, Function<Location, Entity> creator) {
        this.id = id;
        this.creator = creator;
    }

    /**
     * Returns the objects unique id
     *
     * @return the id
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the EntityType bound to that id
     *
     * @param id the types id
     * @return the enum type
     */
    public static EntityType fromID(String id) {
        return Arrays.stream(values()).filter(t -> Objects.equals(t.getID(), id)).findAny().orElse(null);
    }

    /**
     * Creates a new instance of the entity type
     *
     * @param location the location for the entity
     * @return the entity
     */
    public Entity createNewInstance(Location location) {
        return creator.apply(location);
    }
}
