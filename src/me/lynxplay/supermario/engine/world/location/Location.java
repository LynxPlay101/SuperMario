package me.lynxplay.supermario.engine.world.location;

import me.lynxplay.supermario.engine.VoidGame;
import me.lynxplay.supermario.engine.world.blocks.Block;
import me.lynxplay.supermario.engine.world.entities.type.data.Hitbox;
import me.lynxplay.supermario.engine.world.world.World;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "location")
public class Location implements Collidable {

    private static final Hitbox box = new Hitbox(0, 0);

    @XmlAttribute
    private double x, y;

    public Location() {
        this(0, 0);
    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the location
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the location
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the locations block x
     *
     * @return the block x
     */
    public int getBlockX() {
        return (int) (x % 1 == 0 ? x : (x >= 0 ? x : x - 1));
    }

    /**
     * Returns the locations block y
     *
     * @return the block y
     */
    public int getBlockY() {
        return (int) (y % 1 == 0 ? y : (y >= 0 ? y + 1 : y));
    }

    /**
     * Adds the x to the current x
     *
     * @param x the x to add
     * @return the current location
     */
    public Location addX(double x) {
        this.x += x;
        return this;
    }

    /**
     * Adds the y to the current y
     *
     * @param y the y to add
     * @return the current location
     */
    public Location addY(double y) {
        this.y += y;
        return this;
    }

    /**
     * Adds values to the location and returns the modified location
     *
     * @param x the x to add
     * @param y the y to add
     * @return the location instance
     */
    public Location add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Adds the location to the location instance performed on
     *
     * @param location the location to add
     * @return the modified location
     */
    public Location add(Location location) {
        return this.add(location.getX(), location.getY());
    }

    /**
     * Adds the vector to the location instance performed on
     *
     * @param vector the vector to add
     * @return the modified location
     */
    public Location add(Vector vector) {
        return this.add(vector.getX(), vector.getY());
    }

    /**
     * Subtracts the value
     *
     * @param x the x
     * @param y the y
     * @return the modified location
     */
    public Location subtract(double x, double y) {
        return this.add(-x, -y);
    }

    /**
     * Subtracts the value
     *
     * @param location the location to subtract
     * @return the modified location
     */
    public Location subtract(Location location) {
        return this.subtract(location.getX(), location.getY());
    }

    /**
     * Multiplies the location
     *
     * @param scalar the scalar
     * @return the location
     */
    public Location multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    /**
     * Returns the location but with only positive values
     *
     * @return the location
     */
    public Location abs() {
        x = Math.abs(x);
        y = Math.abs(y);
        return this;
    }

    /**
     * Returns a cloned location object
     *
     * @return the new location
     */
    @Override
    public Location clone() {
        return new Location(getX(), getY());
    }

    /**
     * Returns a new location representing the block
     *
     * @return the location of the block
     */
    public Block toBlock() {
        World world = VoidGame.getInstance().getWorld();
        if (world == null) return null;

        return world.getBlockAt(this);
    }

    /**
     * Returns the location of the object
     *
     * @return the location
     */
    @Override
    public Location getLocation() {
        return this;
    }

    @Override
    public String toString() {
        return "Location[" + x + "|" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            return ((Location) obj).getX() == getX() && ((Location) obj).getY() == getY();
        }

        return super.equals(obj);
    }

    /**
     * Returns the hitbox of the object
     *
     * @return the hitbox
     */
    @Override
    public Hitbox getHitbox() {
        return box;
    }

    /**
     * Returns the bottom left cornor of the entity hitbox
     *
     * @return the location
     */
    @Override
    public Location getBottomConrner() {
        return this;
    }

    /**
     * Returns the upper right cornor of the entity hitbox
     *
     * @return the location
     */
    @Override
    public Location getUpperConrner() {
        return this;
    }
}
