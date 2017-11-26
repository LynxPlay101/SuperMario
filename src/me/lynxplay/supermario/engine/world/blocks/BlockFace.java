package me.lynxplay.supermario.engine.world.blocks;

import me.lynxplay.supermario.engine.world.location.Location;

public enum BlockFace {
    UP(0, 1),
    UP_RIGHT(1, 0),
    UP_LEFT(-1, 0),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0);

    private final int xModifier;
    private final int yModifier;

    BlockFace(int xModifier, int yModifier) {
        this.xModifier = xModifier;
        this.yModifier = yModifier;
    }

    /**
     * Modifies the location based on the block face
     *
     * @param location the location to modify
     * @return the modified location
     */
    public Location modifiy(Location location) {
        return location.add(xModifier, yModifier);
    }
}