package me.lynxplay.supermario.engine.world.entities.type.data;

import lombok.Getter;
import me.lynxplay.supermario.engine.world.location.Location;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hitbox")
@Getter
public class Hitbox {

    @XmlAttribute(required = true)
    private double x, y;

    public Hitbox() {
    }

    public Hitbox(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns if the toTest location is hitting the center location
     *
     * @param center the center
     * @param toTest the location to test
     * @return the result
     */
    public boolean hit(Location center, Location toTest) {
        Location vec = center.clone().subtract(toTest).abs();
        return vec.getX() <= x / 2 && vec.getY() <= y / 2;
    }

    @Override
    public String toString() {
        return "[HITBOX] x:" + x + ", y:" + y;
    }
}
