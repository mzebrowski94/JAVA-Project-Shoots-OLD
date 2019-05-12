package game.services;

import game.interfaces.GameInput;
import game.settings.MutualGameSettings;

/**
 * @author Mateusz Żebrowski
 */
public interface PlayersService {

    void initializePlayers(MutualGameSettings mutualGameSettings);

    void updateWithInput(GameInput gameInput);
}
