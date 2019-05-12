package game.services;

import game.interfaces.RoundChangeListener;
import game.settings.MutualGameSettings;
import game.units.PlayerScore;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mateusz Żebrowski
 */

@Component
public interface PlayersScoresService {

    PlayerScore getPlayerScore(int playerNumber);

    List<PlayerScore> getPlayersScores();

    void initializePlayersScores(List<PlayerScore> players);
}
