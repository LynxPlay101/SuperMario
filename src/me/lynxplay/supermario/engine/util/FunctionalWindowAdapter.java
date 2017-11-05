package me.lynxplay.supermario.engine.util;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class FunctionalWindowAdapter extends WindowAdapter {

    private static final Runnable EMPTY = () -> {
    };

    private HashMap<Integer, Runnable> actions = new HashMap<>();

    /**
     * Registers a runnable for a window action
     *
     * @param id       the window event id
     * @param runnable the runnable
     * @return the instance
     */
    public FunctionalWindowAdapter on(int id, Runnable runnable) {
        actions.put(id, runnable);
        return this;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        run(e);
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        run(e);
    }

    private void run(WindowEvent e) {
        actions.getOrDefault(e.getID(), EMPTY).run();
    }
}
