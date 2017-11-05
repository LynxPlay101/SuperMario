package me.lynxplay.supermario.engine.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

public class VoidKeyboardListener implements KeyListener {

    private final Consumer<KeyEvent> onPress;
    private final Consumer<KeyEvent> onRelease;

    public VoidKeyboardListener(Consumer<KeyEvent> onPress, Consumer<KeyEvent> onRelease) {
        this.onPress = onPress;
        this.onRelease = onRelease;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        onPress.accept(e);
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        onRelease.accept(e);
    }
}
