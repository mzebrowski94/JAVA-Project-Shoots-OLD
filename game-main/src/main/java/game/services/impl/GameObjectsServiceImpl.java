package game.services.impl;

import game.factories.MapMatrixFactory;
import game.gameobjects.map.MapMatrix;
import game.services.RoundChangeListener;
import game.settings.MutualGameSettings;
import game.gameobjects.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class GameObjectsServiceImpl implements RoundChangeListener {

    @Autowired
    MapMatrixFactory mapMatrixFactory;

    private List<Player> players;

    @Override
    public void restartOnNewGame(MutualGameSettings mutualGameSettings) {
        mutualGameSettings.getNumberOfPlayers();
    }

    @Override
    public void prepareOnNewRound(MutualGameSettings mutualGameSettings) {

    }


    @Override
    public Player getPlayer(int playerNumber) {
        return null;
    }

    @Override
    public MapMatrix getMapMatrix() {
        return null;
    }

    private void createPlayers(int numberOfPlayers) {

    }

    private void createMapMatrix() {

    }


}
