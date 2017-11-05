package me.lynxplay.supermario.engine.world.entities.template;

import me.lynxplay.supermario.engine.util.Template;
import me.lynxplay.supermario.engine.world.entities.Entity;
import me.lynxplay.supermario.engine.world.entities.type.EntityType;
import me.lynxplay.supermario.engine.world.entities.type.EntityTypeAdapter;
import me.lynxplay.supermario.engine.world.location.Location;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "entity")
public class EntityTemplate implements Template<Entity> {

    @XmlElement
    private Location location;

    @XmlElement
    @XmlJavaTypeAdapter(EntityTypeAdapter.class)
    private EntityType type;

    public EntityTemplate() {
        this(new Location());
    }

    public EntityTemplate(Location location) {
        this.location = location;
    }

    /**
     * Builds the object based on this template
     *
     * @return the object
     */
    @Override
    public Entity build() {
        return type.createNewInstance(location);
    }
}
