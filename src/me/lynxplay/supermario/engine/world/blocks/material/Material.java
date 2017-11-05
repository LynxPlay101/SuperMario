package me.lynxplay.supermario.engine.world.blocks.material;

import lombok.Getter;
import me.lynxplay.supermario.engine.graphics.texture.Texture;
import me.lynxplay.supermario.engine.graphics.texture.TextureAdapter;
import me.lynxplay.supermario.engine.util.handler.Identifiable;
import me.lynxplay.supermario.engine.world.entities.type.data.Hitbox;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "material")
@Getter
public class Material implements Identifiable {

    public static final Material AIR = new Material("air", false, new Hitbox(1, 1));

    @XmlAttribute(required = true)
    private String id;

    @XmlElement
    private boolean solid = false;

    @XmlElement
    private Hitbox hitbox;

    @XmlElement(name = "texture", required = true)
    @XmlJavaTypeAdapter(TextureAdapter.class)
    private Texture texture;

    public Material() {
    }

    public Material(String id, boolean solid, Hitbox hitbox) {
        this.id = id;
        this.solid = solid;
        this.hitbox = hitbox;
    }
}
