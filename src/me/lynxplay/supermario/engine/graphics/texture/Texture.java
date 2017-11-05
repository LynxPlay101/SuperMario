package me.lynxplay.supermario.engine.graphics.texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {

    private BufferedImage image;
    private File source;

    public Texture(File source) {
        this.source = source;
        load();
    }

    public Texture(BufferedImage image) {
        this.image = image;
    }

    private void load() {
        try {
            image = ImageIO.read(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the buffered image
     *
     * @return the optional
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Returns the source file
     *
     * @return
     */
    public File getSource() {
        return source;
    }
}
