package me.lynxplay.supermario.engine.world.entities.type.data;

import lombok.Getter;

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

    @Override
    public String toString() {
        return "[HITBOX] x:" + x + ", y:" + y;
    }
}
