package game.listeners;

import game.events.impl.RoundPhaseUpdateEvent;

/**
 * @author Mateusz Żebrowski
 */
public interface RoundPhaseUpdateListener {
    void handleRoundPhaseUpdate(RoundPhaseUpdateEvent roundPhaseUpdateEvent);
}
