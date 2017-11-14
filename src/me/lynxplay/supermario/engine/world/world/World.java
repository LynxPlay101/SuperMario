package me.lynxplay.supermario.engine.world.world;

import lombok.Getter;
import me.lynxplay.supermario.engine.world.blocks.Block;
import me.lynxplay.supermario.engine.world.blocks.material.Material;
import me.lynxplay.supermario.engine.world.entities.Entity;
import me.lynxplay.supermario.engine.world.location.Location;
import me.lynxplay.supermario.engine.world.location.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class World {

    @Getter
    private final List<Block> blocks;

    @Getter
    private final List<Entity> entities = new ArrayList<>();

    public World(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Ticks the world, including entities etc in it
     */
    public void tick() {
        entities.removeIf(Entity::isDead);
        entities.forEach(e -> {
            Vector vector = e.getVector();
            e.getLocation().add(vector);

            double x = vector.getX();
            if (Math.abs(x) <= e.getType().getData().getXReduction()) {
                vector.setX(0);
            } else {
                vector.setX(x - (x > 0 ? e.getType().getData().getXReduction() : -e.getType().getData().getXReduction()));
            }

            if (e.isOnGround()) {
                if (vector.getY() < 0) {
                    vector.setY(0);
                }
            } else {
                vector.setY(Math.max(-.25, vector.getY() - e.getType().getData().getYReduction()));
            }

            e.fixLocation();
        });
    }

    /**
     * Spawns an entity
     *
     * @param entity the entity to spawn
     */
    public void spawn(Entity entity) {
        if (entity.isDead()) return;

        this.entities.add(entity);
    }

    /**
     * Returns the block at the given lccation
     *
     * @param location the location
     * @return the block
     */
    public Block getBlockAt(Location location) {
        Location blockLocation = new Location(location.getBlockX(), location.getBlockY());

        Optional<Block> block = blocks.stream().filter(b -> Objects.equals(blockLocation, b.getLocation())).findAny();

        if (!block.isPresent()) {
            Block newBlock = new Block(blockLocation, Material.AIR);

            blocks.add(newBlock);
            return newBlock;
        } else {
            return block.get();
        }
    }
}
