package me.lynxplay.supermario.engine.world.world.template;

import lombok.Getter;
import lombok.Setter;
import me.lynxplay.supermario.engine.graphics.texture.UnscaledTexture;
import me.lynxplay.supermario.engine.graphics.texture.UnscaledTextureAdapter;
import me.lynxplay.supermario.engine.util.Template;
import me.lynxplay.supermario.engine.util.handler.Identifiable;
import me.lynxplay.supermario.engine.world.blocks.template.BlockTemplate;
import me.lynxplay.supermario.engine.world.entities.template.EntityTemplate;
import me.lynxplay.supermario.engine.world.world.World;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "level")
public class WorldTemplate implements Template<World>, Identifiable {

    @XmlElement(name = "block", type = BlockTemplate.class)
    @XmlElementWrapper(name = "blocks")
    @Getter
    private List<BlockTemplate> blockTemplates = new ArrayList<>();

    @XmlAttribute
    @Setter
    private String name;

    @XmlElement(name = "entity", type = EntityTemplate.class)
    @XmlElementWrapper(name = "entities")
    private List<EntityTemplate> entityTemplates = new ArrayList<>();

    @XmlElement(name = "backround")
    @XmlJavaTypeAdapter(UnscaledTextureAdapter.class)
    private UnscaledTexture background;

    /**
     * Returns the loaded block templates
     *
     * @return the list of block templates
     */
    public List<BlockTemplate> getBlockTemplates() {
        return blockTemplates;
    }

    /**
     * Builds the object based on this template
     *
     * @return the object
     */
    @Override
    public World build() {
        World world = new World(blockTemplates.stream().map(BlockTemplate::build).collect(Collectors.toList()) , background);

        entityTemplates.stream().map(EntityTemplate::build).forEach(world::spawn);

        return world;
    }

    /**
     * Returns the objects unique id
     *
     * @return the id
     */
    @Override
    public String getId() {
        return name;
    }
}
