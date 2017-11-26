package me.lynxplay.supermario.engine.keyboard;

import me.lynxplay.supermario.engine.graphics.VoidCanvas;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class VoidKeyboard {

    private final VoidCanvas parent;

    private final HashSet<Integer> pressedKeys = new HashSet<>();
    private HashMap<Integer, HashMap<String, Runnable>> actions = new HashMap<>();

    public VoidKeyboard(VoidCanvas parent) {
        this.parent = parent;
        this.parent.addKeyListener(new VoidKeyboardListener((e) -> pressedKeys.add(e.getKeyCode()), (e) -> pressedKeys.remove(e.getKeyCode())));
    }

    /**
     * Registers a key event
     *
     * @param key      the key to register on
     * @param id       the id
     * @param runnable the runnable to be executed
     */
    public void registerKeyEvent(int key, String id, Runnable runnable) {
        getKeyMap(key).put(id, runnable);
    }

    /**
     * Removes a key event from the key
     *
     * @param key the key
     * @param id  the id
     */
    public void removeKeyEvent(int key, String id) {
        getKeyMap(key).remove(id);
    }

    public void executeEvents() {
        synchronized (pressedKeys) {
            new HashSet<>(pressedKeys).parallelStream().map(this::getKeyMap).map(HashMap::values).flatMap(Collection::stream).forEach(Runnable::run);
        }
    }

    /**
     * Returns the hashmap assigned to that key
     *
     * @param key the key
     * @return the map
     */
    private HashMap<String, Runnable> getKeyMap(int key) {
        if (!actions.containsKey(key)) actions.put(key, new HashMap<>());
        return actions.get(key);
    }
}
