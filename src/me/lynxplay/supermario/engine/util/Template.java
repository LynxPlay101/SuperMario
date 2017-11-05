package me.lynxplay.supermario.engine.util;

public interface Template<T> {

    /**
     * Builds the object based on this template
     * @return the object
     */
    T build();

}
