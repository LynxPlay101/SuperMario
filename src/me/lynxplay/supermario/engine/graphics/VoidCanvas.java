package me.lynxplay.supermario.engine.graphics;

import lombok.Getter;
import me.lynxplay.supermario.engine.graphics.texture.Texture;
import me.lynxplay.supermario.engine.world.location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Stack;

public class VoidCanvas extends Canvas {

    public static final double LOCATION_TO_PIXEL = 64;

    private JFrame parent;

    private BufferStrategy buffer;
    private Graphics2D drawnGraphics;

    private Stack<CanvasLayer> layers = new Stack<>();

    @Getter
    private Location displayedLocation = new Location(0, 0);
    private Location center;

    /**
     * Creates a new screen
     */
    public VoidCanvas(JFrame parent) {
        this.parent = parent;

        setSize(parent.getSize());
        setBackground(Color.BLACK);
        this.center = new Location(getSize().getWidth() / 2D, getSize().getHeight() / 2D);

        parent.add(this);
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
        if (!this.isDisplayable()) return;

        buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(3);
            buffer = getBufferStrategy();
        }

        try {
            drawnGraphics = (Graphics2D) buffer.getDrawGraphics();
            drawnGraphics.setColor(Color.BLACK);
            drawnGraphics.fillRect(0, 0, getWidth(), getHeight());

            layers.forEach(l -> l.render(this));

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
}
