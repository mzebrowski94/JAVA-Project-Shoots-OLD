package game.events.impl;

import game.consts.GameState;
import game.events.GameStateUpdateEvent;

/**
 * @author Mateusz Żebrowski
 */
public class GameQuitEvent implements GameStateUpdateEvent {

    @Override
    public GameState getGameState() {
        return GameState.QUIT;
    }
}
