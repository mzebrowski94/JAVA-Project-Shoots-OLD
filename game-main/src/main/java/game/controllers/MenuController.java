package game.controllers;

import game.interfaces.GameController;
import game.listeners.MenuOptionUpdateListener;
import game.listeners.RoundPhaseUpdateListener;

/**
 * @author Mateusz Żebrowski
 */
public interface MenuController extends GameController, MenuOptionUpdateListener, RoundPhaseUpdateListener {
    void submitActualOption();
}
