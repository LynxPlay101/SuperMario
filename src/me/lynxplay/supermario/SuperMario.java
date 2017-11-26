package me.lynxplay.supermario;

import me.lynxplay.supermario.engine.VoidGame;
import me.lynxplay.supermario.engine.world.entities.type.entity.Goomba;
import me.lynxplay.supermario.engine.world.location.Location;
import me.lynxplay.supermario.engine.world.world.World;
import me.lynxplay.supermario.engine.world.world.template.WorldTemplate;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class SuperMario extends VoidGame {

    public static void main(String[] args) {
        SuperMario superMario = new SuperMario();
        superMario.load();

        Optional<World> world = superMario.getWorldTemplateManager().find("level_1").map(WorldTemplate::build);
        if (!world.isPresent()) return;

        superMario.setWorld(world.get());

        Goomba goomba = new Goomba(new Location(0, 3));
        superMario.getWorld().spawn(goomba);

        /*superMario.getCanvas().addLayer((l) -> {
            l.getDrawnGraphics().setColor(Color.RED);
            superMario.getWorld().getEntities().forEach(e -> {
                Location converted = l.convertToScreen(e.getBottomConrner());
                l.getDrawnGraphics().drawRect(converted.getBlockX(), converted.getBlockY(), (int) (e.getHitbox().getX() * VoidCanvas.LOCATION_TO_PIXEL), (int) (e.getHitbox().getY() * VoidCanvas.LOCATION_TO_PIXEL));

                Block block = e.getBlockBelow();
                Location screen = l.convertToScreen(block.getBottomConrner().clone());
                l.getDrawnGraphics().drawRect(screen.getBlockX(), screen.getBlockY(), (int) (block.getHitbox().getX() * VoidCanvas.LOCATION_TO_PIXEL), (int) (block.getHitbox().getY() * VoidCanvas.LOCATION_TO_PIXEL));

                l.getDrawnGraphics().drawString(block.getLocation().toString(), 0, 50);
                l.getDrawnGraphics().drawString(e.getLocation().toString(), 0, 70);
                l.getDrawnGraphics().drawString(e.getVector().toString(), 0, 90);
                l.getDrawnGraphics().drawString(String.valueOf(e.isOnGround()), 0, 110);

            });
        });*/

        superMario.getCanvas().addLayer((l) -> {
            if(goomba.isDead()) superMario.stop();

            getInstance().getCanvas().teleportCamera(goomba.getLocation().getX(), 0);
        });

        superMario.getKeyboard().registerKeyEvent(KeyEvent.VK_A, "debug_move_a", () -> goomba.move(-goomba.getMaxSpeed(), 0));
        superMario.getKeyboard().registerKeyEvent(KeyEvent.VK_D, "debug_move_d", () -> goomba.move(goomba.getMaxSpeed(), 0));
        superMario.getKeyboard().registerKeyEvent(KeyEvent.VK_SPACE, "debug_spave", () -> {
            if (goomba.isOnGround()) {
                goomba.move(0, .25);
            }
        });

        superMario.start();
    }

}
