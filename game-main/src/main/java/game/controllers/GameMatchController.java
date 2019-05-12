package game.controllers;

import game.interfaces.GameController;
import game.listeners.RoundPhaseUpdateListener;

/**
 * @author Mateusz Żebrowski
 */
public interface GameMatchController extends GameController, RoundPhaseUpdateListener {

    void updateRoundTime();

    void endRound();
}
