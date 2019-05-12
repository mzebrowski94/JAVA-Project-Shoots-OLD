package game.events.impl;

import game.consts.GameState;
import game.consts.RoundPhase;
import game.events.GameStateUpdateEvent;
import game.settings.MutualGameSettings;
import lombok.Getter;

/**
 * @author Mateusz Żebrowski
 */
public class RoundPhaseUpdateEvent implements GameStateUpdateEvent {

    @Getter
    private final RoundPhase roundPhase;

    @Getter
    private final MutualGameSettings mutualGameSettings;

    public RoundPhaseUpdateEvent(RoundPhase roundPhase) {
        this.roundPhase = roundPhase;
        this.mutualGameSettings = MutualGameSettings.getEmpty();
    }

    public RoundPhaseUpdateEvent(RoundPhase roundPhase, MutualGameSettings mutualGameSettings) {
        this.roundPhase = roundPhase;
        this.mutualGameSettings = mutualGameSettings;
    }

    @Override
    public GameState getGameState() {
        return GameState.GAME;
    }
}
