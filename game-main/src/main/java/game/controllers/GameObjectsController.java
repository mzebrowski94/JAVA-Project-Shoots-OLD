package game.controllers;

import game.interfaces.GameController;
import game.settings.MutualGameSettings;

/**
 * @author Mateusz Żebrowski
 */
public interface GameObjectsController extends GameController {

    void updatePlayerState();

    void initializeBasicGameObjects(MutualGameSettings mutualGameSettings);
}
