package me.lynxplay.supermario.engine.graphics.texture;

import com.sun.istack.internal.Nullable;
import me.lynxplay.supermario.engine.graphics.VoidCanvas;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {

    protected BufferedImage image;
    private File source;

    public Texture(File source) {
        this.source = source;
        load();
    }

    public Texture(BufferedImage image) {
        this.image = image;
    }

    public void load() {
        try {
            image = ImageIO.read(source);
            image = VoidCanvas.scale(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the buffered image
     *
     * @return the buffered image
     */
    @Nullable
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets the image
     *
     * @param image the image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Returns the source file
     *
     * @return the source file
     */
    public File getSource() {
        return source;
    }

}
