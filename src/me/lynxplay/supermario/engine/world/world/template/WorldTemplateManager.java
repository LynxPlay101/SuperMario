package me.lynxplay.supermario.engine.world.world.template;

import me.lynxplay.supermario.engine.util.handler.IdentifiableObjectManager;
import me.lynxplay.supermario.engine.loader.JAXBLoader;

import java.io.File;

public class WorldTemplateManager extends IdentifiableObjectManager<WorldTemplate> {

    public WorldTemplateManager(File resourceFolder, JAXBLoader loader) {
        super(resourceFolder, loader, WorldTemplate.class);
    }

}
