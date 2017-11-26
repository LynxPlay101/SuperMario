package me.lynxplay.supermario.engine.graphics.texture;

import me.lynxplay.supermario.engine.util.FileLoader;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UnscaledTextureAdapter extends XmlAdapter<String, UnscaledTexture> {

    /**
     * Convert a value type to a bound type.
     *
     * @param string The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link javax.xml.bind.ValidationEventHandler}.
     */
    @Override
    public UnscaledTexture unmarshal(String string) throws Exception {
        return new UnscaledTexture(FileLoader.getFile(string));
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param image The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link javax.xml.bind.ValidationEventHandler}.
     */
    @Override
    public String marshal(UnscaledTexture image) throws Exception {
        return image.getSource().getAbsolutePath();
    }

}
