package me.lynxplay.supermario.engine.util;

public class MathUtils {

    /**
     * Constrains a value to the max and minimum value
     *
     * @param min   the minimum
     * @param max   the maximum
     * @param value the value to constrain
     * @return the constrained value
     */
    public static double constrain(double min, double max, double value) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

}
