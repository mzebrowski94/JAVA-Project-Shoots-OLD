package game.services;

import game.listeners.RoundPhaseUpdateListener;
import game.units.Round;

/**
 * @author Mateusz Żebrowski
 */
public interface GameStateService extends RoundPhaseUpdateListener {

    Round getActualRound();

    Round getPreviousRound();

    boolean roundTimeEnded();

    boolean checkGameEnd();
}
