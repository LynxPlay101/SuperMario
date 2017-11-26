package me.lynxplay.supermario.engine.graphics;

import lombok.Getter;
import me.lynxplay.supermario.engine.graphics.texture.Texture;
import me.lynxplay.supermario.engine.world.location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class VoidCanvas extends Canvas {

    public static double LOCATION_TO_PIXEL = 64;

    private static final int DEFAULT_WIDTH = 1920, DEFAULT_HEIGHT = 1080;

    private JFrame parent;

    private BufferStrategy buffer;
    private Graphics2D drawnGraphics;

    private Stack<CanvasLayer> layers = new Stack<>();

    @Getter
    private Location displayedLocation = new Location(0, 0);
    private Location center;

    /**
     * Creates a new screen
     *
     * @param parent the jFrame to be displayed on
     */
    public VoidCanvas(JFrame parent) {
        this.parent = parent;

        setSize(parent.getSize());
        setBackground(Color.BLACK);
        this.center = new Location(getSize().getWidth() / 2D, getSize().getHeight() / 2D);

        parent.add(this);

        LOCATION_TO_PIXEL = getWidthModifier() * 64;
    }

    /**
     * Sets if the screen is visible or not
     *
     * @param visible the visibility
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        parent.setVisible(visible);
        parent.toFront();
        parent.requestFocus();

        requestFocusInWindow();
    }

    /**
     * Registers a canvas layer
     *
     * @param layer the layer
     */
    public void addLayer(CanvasLayer layer) {
        this.layers.add(layer);
    }

    /**
     * Removes all layers of that type
     *
     * @param type the type
     * @param <T>  genric type
     */
    public <T extends CanvasLayer> void removeLayer(Class<T> type) {
        layers.removeIf(type::isInstance);
    }

    /**
     * Moves the canvas camera
     *
     * @param x the x to move
     * @param y the y to move
     */
    public void moveCamera(double x, double y) {
        this.displayedLocation.add(x, -y);
    }

    public void teleportCamera(double x, double y) {
        this.displayedLocation = new Location(x, y);
    }

    /**
     * Returns if the location is displayed right now
     *
     * @param location the location to display
     * @return the result
     */
    public boolean isDisplayed(Location location) {
        if (location == null) return false;

        Location vector = this.displayedLocation.clone().subtract(location).abs();
        Dimension size = getSize();

        return size.getWidth() >= (vector.getX() * LOCATION_TO_PIXEL) && size.getHeight() >= (vector.getY() * LOCATION_TO_PIXEL);
    }

    /**
     * Draws the given object onto the screen
     *
     * @param location the world location to draw it on
     * @param image    the image to draw
     */
    public void drawImage(Location location, Texture image) {
        if (image == null || image.getImage() == null || !isDisplayed(location)) return;

        Location draw = convertToScreen(location);

        getDrawnGraphics().drawImage(image.getImage(), draw.getBlockX(), draw.getBlockY(), null);
    }

    /**
     * Converts a in world location into a on screen location
     *
     * @param location the location to convert
     * @return the converted location
     */
    public Location convertToScreen(Location location) {
        if (location == null) return null;

        return location.clone().add(0, -location.getY() * 2).subtract(displayedLocation).multiply(LOCATION_TO_PIXEL).add(center);
    }

    /**
     * Renders every registered layer on the screen
     */
    public void render() {
        buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(3);
            buffer = getBufferStrategy();
        }

        try {
            if (!this.isDisplayable()) return;
            drawnGraphics = (Graphics2D) buffer.getDrawGraphics();
            drawnGraphics.setColor(Color.BLACK);
            drawnGraphics.fillRect(0, 0, getWidth(), getHeight());

            layers.forEach(l -> l.render(this));

            if (!this.isDisplayable()) return;
            buffer.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the drawn graphics that will be displayed in the next render process
     *
     * @return the graphics instance
     */
    public Graphics2D getDrawnGraphics() {
        return drawnGraphics;
    }

    /**
     * Scales an image to the current screen size
     *
     * @param image the image
     * @return the scaled image
     */
    public static BufferedImage scale(BufferedImage image) {
        double widthModifier = getWidthModifier();
        double heigthModifier = getHeigthModifier();

        return scale(image, widthModifier, heigthModifier);
    }

    /**
     * Scales the image
     *
     * @param image          the image
     * @param widthModifier  the widthModifier
     * @param heigthModifier the heigthModifier
     * @return the scaled image
     */
    public static BufferedImage scale(BufferedImage image, double widthModifier, double heigthModifier) {
        int width = (int) (image.getWidth() * widthModifier + .5);
        int heigth = (int) (image.getHeight() * heigthModifier + .5);

        BufferedImage scaledImage = new BufferedImage(width, heigth, image.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, heigth, null);
        graphics2D.dispose();

        return scaledImage;
    }

    /**
     * Returns the height modifier
     *
     * @return the modifier
     */
    public static double getHeigthModifier() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight() / DEFAULT_HEIGHT;
    }

    /**
     * Returns the width modifier
     *
     * @return the modifier
     */
    public static double getWidthModifier() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth() / DEFAULT_WIDTH;
    }
}
