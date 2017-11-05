package me.lynxplay.supermario.engine.util.handler;

import lombok.AccessLevel;
import lombok.Getter;
import me.lynxplay.supermario.engine.loader.JAXBLoader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Getter(AccessLevel.PROTECTED)
public class IdentifiableObjectManager<T extends Identifiable> {

    private HashMap<String, T> loadedObjects = new HashMap<>();

    private File resourceFolder;
    private JAXBLoader loader;
    private final Class<T> objectClass;

    public IdentifiableObjectManager(File resourceFolder, JAXBLoader loader, Class<T> objectClass) {
        this.resourceFolder = resourceFolder;
        this.loader = loader;
        this.objectClass = objectClass;
    }

    /**
     * Returns the loaded object by the given id
     *
     * @param id the id
     * @return the object's optional
     */
    public Optional<T> find(String id) {
        return Optional.ofNullable(loadedObjects.get(id));
    }

    /**
     * Loads the object with the give id
     *
     * @param id     the id
     * @param object the object
     */
    public void load(String id, T object) {
        this.loadedObjects.put(id, object);
    }

    /**
     * Loads all valid xml files in the folder
     */
    public void loadAll() {
        if (resourceFolder == null || !resourceFolder.exists() || !resourceFolder.isDirectory())
            return;

        File[] files = resourceFolder.listFiles();
        if (files == null) return;

        Unmarshaller unmarshallerFor = this.loader.getUnmarshallerFor(objectClass);

        Arrays.stream(files)
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().endsWith(".xml"))
                .map(file -> {
                    try {
                        return unmarshallerFor.unmarshal(file);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(objectClass::isInstance)
                .map(objectClass::cast)
                .forEach(t -> load(t.getId(), t));
    }

}
