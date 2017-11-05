package me.lynxplay.supermario.engine.world.entities.type.data;

import me.lynxplay.supermario.engine.loader.JAXBLoader;
import me.lynxplay.supermario.engine.util.handler.IdentifiableObjectManager;
import me.lynxplay.supermario.engine.world.entities.type.EntityType;

import java.io.File;

public class EntityTypeDataManager extends IdentifiableObjectManager<EntityTypeData> {
    public EntityTypeDataManager(File resourceFolder, JAXBLoader loader) {
        super(resourceFolder, loader, EntityTypeData.class);
    }

    @Override
    public void loadAll() {
        super.loadAll();
        getLoadedObjects().values().forEach(d -> EntityType.fromID(d.getId()).setData(d));
    }
}
