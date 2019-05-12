package game.events;

import game.consts.GameState;

/**
 * @author Mateusz Żebrowski
 */
public interface GameStateUpdateEvent {
    GameState getGameState();
}
