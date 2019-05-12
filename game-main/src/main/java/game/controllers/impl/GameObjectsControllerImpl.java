package game.controllers.impl;

import game.controllers.GameObjectsController;
import game.services.MapMatrixService;
import game.services.PlayersService;
import game.settings.MutualGameSettings;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Mateusz Żebrowski
 */
public class GameObjectsControllerImpl implements GameObjectsController {


    @Autowired
    private PlayersService playerService;

    @Autowired
    private MapMatrixService mapMatrixService;

    @Override
    public void updatePlayerState() {
    }

    @Override
    public void initializeBasicGameObjects(MutualGameSettings mutualGameSettings) {
        if (mutualGameSettings.isEmpty()) {
            throw new IllegalStateException("MutualGameSettings are empty");
        }

        playerService.initializePlayers(mutualGameSettings);
        mapMatrixService.initializeMapMatrix(mutualGameSettings);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateGraphics() {

    }
}
