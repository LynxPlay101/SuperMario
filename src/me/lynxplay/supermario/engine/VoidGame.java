package me.lynxplay.supermario.engine;

import lombok.AccessLevel;
import lombok.Getter;
import me.lynxplay.supermario.engine.graphics.VoidCanvas;
import me.lynxplay.supermario.engine.keyboard.VoidKeyboard;
import me.lynxplay.supermario.engine.loader.JAXBLoader;
import me.lynxplay.supermario.engine.runnables.VoidGameLoop;
import me.lynxplay.supermario.engine.world.blocks.material.MaterialAdapter;
import me.lynxplay.supermario.engine.world.blocks.material.MaterialManager;
import me.lynxplay.supermario.engine.world.entities.type.EntityTypeAdapter;
import me.lynxplay.supermario.engine.world.entities.type.data.EntityTypeDataManager;
import me.lynxplay.supermario.engine.world.world.World;
import me.lynxplay.supermario.engine.world.world.WorldRender;
import me.lynxplay.supermario.engine.world.world.template.WorldTemplateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

@Getter
public class VoidGame {

    @Getter
    private static VoidGame instance;

    private VoidCanvas canvas;
    private VoidKeyboard keyboard;
    private VoidGameLoop loop;

    private MaterialManager materialManager;
    private WorldTemplateManager worldTemplateManager;
    private EntityTypeDataManager typeDataManager;

    private JAXBLoader loader;

    private World world;

    @Getter(AccessLevel.NONE)
    private JFrame frame;

    public VoidGame() {
        instance = this;

        this.loader = new JAXBLoader();

        this.materialManager = new MaterialManager(new File("resources/materials"), getLoader());
        this.worldTemplateManager = new WorldTemplateManager(new File("resources/levels"), getLoader());
        this.typeDataManager = new EntityTypeDataManager(new File("resources/entities") , getLoader());

        loader.registerAdapter(new MaterialAdapter(materialManager));
        loader.registerAdapter(new EntityTypeAdapter());

        this.frame = new JFrame(this.getClass().getName());
        this.frame.setResizable(false);
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.frame.setLocationRelativeTo(null);
        this.frame.setUndecorated(true);
        this.frame.setAutoRequestFocus(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.toFront();

        this.canvas = new VoidCanvas(frame);
        this.keyboard = new VoidKeyboard(canvas);
        this.loop = new VoidGameLoop(this);

        this.keyboard.registerKeyEvent(KeyEvent.VK_ESCAPE, "end_game", this::stop);
    }

    /**
     * Starts the game
     */
    public void start() {
        this.canvas.setVisible(true);
        this.loop.start();
    }

    /**
     * Stops the game
     */
    private void stop() {
        this.frame.dispose();
        this.loop.stop();
    }

    /**
     * Loads the resources
     */
    public void load() {
        materialManager.loadAll();
        worldTemplateManager.loadAll();
        typeDataManager.loadAll();
    }

    /**
     * Sets the currently displayed world
     *
     * @param world the new world
     */
    public void setWorld(World world) {
        if (world == null) return;

        this.world = world;

        getCanvas().removeLayer(WorldRender.class);
        getCanvas().addLayer(new WorldRender(world));
    }

}
