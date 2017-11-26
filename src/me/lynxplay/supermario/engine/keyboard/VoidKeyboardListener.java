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


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        onPress.accept(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        onRelease.accept(e);
    }
}
