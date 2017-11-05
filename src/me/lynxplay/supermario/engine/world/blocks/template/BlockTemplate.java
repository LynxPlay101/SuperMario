package me.lynxplay.supermario.engine.world.blocks.template;

import me.lynxplay.supermario.engine.util.Template;
import me.lynxplay.supermario.engine.world.blocks.Block;
import me.lynxplay.supermario.engine.world.blocks.material.Material;
import me.lynxplay.supermario.engine.world.blocks.material.MaterialAdapter;
import me.lynxplay.supermario.engine.world.location.Location;
import me.lynxplay.supermario.engine.world.location.LocationAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "block")
public class BlockTemplate implements Template<Block> {

    @XmlAttribute
    @XmlJavaTypeAdapter(LocationAdapter.class)
    private Location location;

    @XmlAttribute
    @XmlJavaTypeAdapter(MaterialAdapter.class)
    private Material material;

    public BlockTemplate() {

    }

    public BlockTemplate(Location location) {
        this.location = location;
    }

    /**
     * Builds the object based on this template
     *
     * @return the object
     */
    @Override
    public Block build() {
        return new Block(location, material);
    }
}
