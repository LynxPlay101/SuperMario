package me.lynxplay.supermario.engine.loader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.HashSet;

public class JAXBLoader {

    private HashSet<XmlAdapter<?, ?>> adapters = new HashSet<>();
    private HashMap<Class<?>, Marshaller> loadedMarshaller = new HashMap<>();
    private HashMap<Class<?>, Unmarshaller> loadedUnmarshaller = new HashMap<>();

    /**
     * Registers an xml adapter that will be registered on eveny JAXB context
     *
     * @param adapter the adapter
     */
    public void registerAdapter(XmlAdapter<?, ?> adapter) {
        this.adapters.add(adapter);
    }

    /**
     * Returns the loaded marshaller for the class or creates a new one if none was found
     *
     * @param clazz the clazz for the marshaller
     * @return the marshaller
     */
    public Marshaller getMarshallerFor(Class<?> clazz) {
        if (loadedMarshaller.containsKey(clazz)) return loadedMarshaller.get(clazz);

        try {
            JAXBContext context = JAXBContext.newInstance(clazz);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            adapters.forEach(marshaller::setAdapter);

            loadedMarshaller.put(clazz, marshaller);

            return marshaller;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the loaded marshaller for the class or creates a new one if none was found
     *
     * @param clazz the clazz for the marshaller
     * @return the marshaller
     */
    public Unmarshaller getUnmarshallerFor(Class<?> clazz) {
        if (loadedUnmarshaller.containsKey(clazz)) return loadedUnmarshaller.get(clazz);

        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            adapters.forEach(unmarshaller::setAdapter);

            loadedUnmarshaller.put(clazz, unmarshaller);

            return unmarshaller;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}
