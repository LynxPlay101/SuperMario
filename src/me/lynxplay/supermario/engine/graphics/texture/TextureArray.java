package me.lynxplay.supermario.engine.graphics.texture;

import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.image.BufferedImage;

@XmlRootElement
@Setter
public class TextureArray {

    @XmlElement
    @XmlJavaTypeAdapter(TextureAdapter.class)
    private Texture source;

    @XmlAttribute
    private int amount;

    private Texture[] array;

    /**
     * Returns the texture at the given index
     *
     * @param index the index
     * @return the texture
     */
    public Texture getTexture(int index) {
        if (array == null) array = new Texture[amount];

        if (array[index] == null) {
            loadToCache(index);
        }

        return array[index];
    }

    /**
     * This methode will load a certain sub part of the source into the array
     *
     * @param index the index of the sub texture
     */
    private void loadToCache(int index) {
        BufferedImage image = source.getImage();
        int pictureSize = image.getWidth() / amount;

        BufferedImage subimage = image.getSubimage(pictureSize * index, 0, pictureSize , image.getHeight());
        array[index] = new Texture(subimage);
    }

}
