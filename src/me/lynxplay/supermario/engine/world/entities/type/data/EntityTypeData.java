package me.lynxplay.supermario.engine.world.entities.type.data;

import lombok.Getter;
import me.lynxplay.supermario.engine.graphics.texture.TextureArray;
import me.lynxplay.supermario.engine.util.handler.Identifiable;

import javax.xml.bind.annotation.*;
import java.util.HashMap;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class EntityTypeData implements Identifiable {

    @XmlAttribute
    private String typeID;

    private Hitbox hitbox;

    @XmlElement
    private HashMap<String, TextureArray> animationSet;

    @XmlElement
    private double movementSpeed;

    @XmlElement
    private double xReduction;

    @XmlElement
    private double yReduction;

    /**
     * Returns the objects unique id
     *
     * @return the id
     */
    @Override
    public String getId() {
        return typeID;
    }

    /**
     * Returns the entity types animation set
     *
     * @return the hashmap
     */
    public HashMap<String, TextureArray> getAnimationSet() {
        return animationSet;
    }

    /**
     * Returns the max movement speed of the entity type
     *
     * @return the movement speed
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Returns the reduction of the entities velocitiy on the x axis
     *
     * @return
     */
    public double getXReduction() {
        return xReduction;
    }

    /**
     * Returns the reduction of the entities velocitiy on the x axis
     *
     * @return
     */
    public double getYReduction() {
        return yReduction;
    }
}
