package game.listeners;

import game.events.GameStateUpdateEvent;

/**
 * @author Mateusz Żebrowski
 */
public interface GameStateUpdateListener {
    void handleGameStateUpdated(GameStateUpdateEvent gameStateUpdateEvent);
}
