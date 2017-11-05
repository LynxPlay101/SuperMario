package me.lynxplay.supermario.engine.world.blocks.material;

import me.lynxplay.supermario.engine.util.handler.IdentifiableObjectManager;
import me.lynxplay.supermario.engine.loader.JAXBLoader;

import java.io.File;

public class MaterialManager extends IdentifiableObjectManager<Material> {

    public MaterialManager(File resourceFolder, JAXBLoader loader) {
        super(resourceFolder, loader, Material.class);
    }

}
