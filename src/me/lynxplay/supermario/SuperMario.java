package me.lynxplay.supermario;

import me.lynxplay.supermario.engine.VoidGame;
import me.lynxplay.supermario.engine.world.blocks.Block;
import me.lynxplay.supermario.engine.world.entities.type.entity.Goomba;
import me.lynxplay.supermario.engine.world.location.Location;
import me.lynxplay.supermario.engine.world.world.World;
import me.lynxplay.supermario.engine.world.world.template.WorldTemplate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Optional;

import static me.lynxplay.supermario.engine.graphics.VoidCanvas.LOCATION_TO_PIXEL;

public class SuperMario extends VoidGame {

    public static void main(String[] args) {
        SuperMario superMario = new SuperMario();
        superMario.load();

        Optional<World> world = superMario.getWorldTemplateManager().find("level_1").map(WorldTemplate::build);
        if (!world.isPresent()) return;

        superMario.setWorld(world.get());

        Goomba goomba = new Goomba(new Location(0, 1));
        superMario.getWorld().spawn(goomba);

        superMario.getCanvas().addLayer((l) -> {
            l.getDrawnGraphics().setColor(Color.RED);
            superMario.getWorld().getEntities().forEach(e -> {
                Location converted = l.convertToScreen(e.getLocation());
                l.getDrawnGraphics().drawRect(converted.getBlockX(), converted.getBlockY(), (int) (e.getHitbox().getX() * LOCATION_TO_PIXEL), (int) (e.getHitbox().getY() * LOCATION_TO_PIXEL));

                Location locBelow = e.getBlockBelow();

                Block block = locBelow.toBlock();
                Location screen = l.convertToScreen(block.getLocation().clone());
                l.getDrawnGraphics().drawRect(screen.getBlockX(), screen.getBlockY(), (int) (block.getHitbox().getX() * LOCATION_TO_PIXEL), (int) (block.getHitbox().getY() * LOCATION_TO_PIXEL));

                Location center = l.convertToScreen(e.getCenter());
                l.getDrawnGraphics().drawLine(center.getBlockX(), center.getBlockY(), 0, 0);
            });

        });

        superMario.getKeyboard().registerKeyEvent(KeyEvent.VK_A, "debug_move_a", () -> goomba.move(-goomba.getMaxSpeed(), 0));
        superMario.getKeyboard().registerKeyEvent(KeyEvent.VK_D, "debug_move_d", () -> goomba.move(goomba.getMaxSpeed(), 0));
        superMario.getKeyboard().registerKeyEvent(KeyEvent.VK_SPACE, "debug_spave", () -> {
            if(goomba.isOnGround()) {
                goomba.move(0 , 10);
            }
        });

        superMario.start();
    }

}
