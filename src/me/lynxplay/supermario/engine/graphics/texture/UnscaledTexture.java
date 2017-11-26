package me.lynxplay.supermario.engine.graphics.texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UnscaledTexture extends Texture {

    public UnscaledTexture(File source) {
        super(source);
    }

    public UnscaledTexture(BufferedImage image) {
        super(image);
    }

    @Override
    public void load() {
        try {
            image = ImageIO.read(getSource());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
