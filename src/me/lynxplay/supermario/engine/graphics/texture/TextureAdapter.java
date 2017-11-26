package me.lynxplay.supermario.engine.graphics.texture;

import me.lynxplay.supermario.engine.util.FileLoader;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TextureAdapter extends XmlAdapter<String, Texture> {

    /**
     * Convert a value type to a bound type.
     *
     * @param string The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public Texture unmarshal(String string) throws Exception {
        return new Texture(FileLoader.getFile(string));
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param image The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public String marshal(Texture image) throws Exception {
        return image.getSource().getAbsolutePath();
    }
}
