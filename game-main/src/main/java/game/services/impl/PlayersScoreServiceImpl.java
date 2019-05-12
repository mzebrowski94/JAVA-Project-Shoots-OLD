package game.services.impl;

import game.services.PlayersScoresService;
import game.settings.MutualGameSettings;
import game.units.PlayerScore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateusz Żebrowski
 */

public class PlayersScoreServiceImpl implements PlayersScoresService {

    private ArrayList<PlayerScore> playersScores;

    private void init(int numberOfPlayers){
        playersScores = new ArrayList<>();
        playersScores.clear();
        for (int i = 0; i < numberOfPlayers; i++) {
            playersScores.add(new PlayerScore());
        }
    }

    @Override
    public void restartOnNewGame(MutualGameSettings mutualGameSettings) {
        init(mutualGameSettings.getNumberOfPlayers());
        playersScores.forEach(playerScore -> playerScore.updateOnNewRound(0));
    }

    @Override
    public void prepareOnNewRound(MutualGameSettings mutualGameSettings) {
        playersScores.forEach(playerScore -> playerScore.updateOnNewRound(0));
    }

    @Override
    public PlayerScore getPlayerScore(int playerNumber) {
        return playersScores.get(playerNumber);
    }

    @Override
    public List<PlayerScore> getPlayersScores() {
        return playersScores;
    }

    @Override
    public void initializePlayersScores(List<PlayerScore> players) {

    }
}
