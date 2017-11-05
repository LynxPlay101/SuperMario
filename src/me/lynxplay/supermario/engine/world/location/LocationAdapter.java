package me.lynxplay.supermario.engine.world.location;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocationAdapter extends XmlAdapter<String, Location> {
    /**
     * Convert a value type to a bound type.
     *
     * @param v The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public Location unmarshal(String v) throws Exception {
        if (v == null) return null;
        String[] values = v.split(";");

        return new Location(Double.valueOf(values[0]), Double.valueOf(values[1]));
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public String marshal(Location v) throws Exception {
        return v.getX() + ";" + v.getY();
    }
}
