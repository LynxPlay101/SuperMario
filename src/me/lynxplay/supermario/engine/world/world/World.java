package me.lynxplay.supermario.engine.world.world;

import lombok.Getter;
import me.lynxplay.supermario.engine.graphics.VoidCanvas;
import me.lynxplay.supermario.engine.graphics.texture.UnscaledTexture;
import me.lynxplay.supermario.engine.world.blocks.Block;
import me.lynxplay.supermario.engine.world.blocks.BlockFace;
import me.lynxplay.supermario.engine.world.blocks.material.Material;
import me.lynxplay.supermario.engine.world.entities.Entity;
import me.lynxplay.supermario.engine.world.location.Location;
import me.lynxplay.supermario.engine.world.location.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class World {

    @Getter
    private final List<Block> blocks;

    @Getter
    private UnscaledTexture background;

    @Getter
    private final List<Entity> entities = new ArrayList<>();

    public World(List<Block> blocks, UnscaledTexture background) {
        this.blocks = blocks;
        this.background = background;
        this.background.setImage(VoidCanvas.scale(this.background.getImage() ,  VoidCanvas.getWidthModifier() , VoidCanvas.getHeigthModifier()));
    }

    /**
     * Ticks the world, including entities etc in it
     */
    public void tick() {
        entities.removeIf(Entity::isDead);
        entities.forEach(e -> {

            Block block = e.getLocation().toBlock();

            Vector vector = e.getVector();
            Vector backup = vector.clone();

            e.getLocation().add(vector);

            double x = vector.getX();
            if (Math.abs(x) <= e.getType().getData().getXReduction()) {
                vector.setX(0);
            } else {
                vector.setX(x - (x > 0 ? e.getType().getData().getXReduction() : -e.getType().getData().getXReduction()));
                if (vector.getX() > 0) {
                    Block relative = block.getRelative(BlockFace.RIGHT);
                    if (relative.getMaterial().isSolid() && relative.isColliding(e)) vector.setX(0);
                } else {
                    Block relative = block.getRelative(BlockFace.LEFT);
                    if (relative.getMaterial().isSolid() && relative.isColliding(e)) vector.setX(0);
                }
            }

            if (e.isOnGround()) {
                if (vector.getY() < 0) {
                    vector.setY(0);
                }
            } else {
                if (Stream.of(BlockFace.UP, BlockFace.UP_LEFT, BlockFace.UP_RIGHT)
                        .map(block::getRelative)
                        .anyMatch(b -> b.isColliding(e) && b.getMaterial().isSolid()) && vector.getY() > 0) {
                    vector.setY(0);
                } else {
                    vector.setY(Math.max(-.25, vector.getY() - e.getType().getData().getYReduction()));
                }
            }

            e.getLocation().add(backup.multiply(-1));
            e.getLocation().add(vector);

            if (e.getLocation().getY() < -50) e.kill();
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
