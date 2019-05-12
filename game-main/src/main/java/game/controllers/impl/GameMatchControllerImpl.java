package game.controllers.impl;

import game.consts.MenuOption;
import game.consts.RoundPhase;
import game.controllers.GameMatchController;
import game.controllers.GameObjectsController;
import game.controllers.InputController;
import game.events.impl.RoundPhaseUpdateEvent;
import game.gui.service.GraphicsController;
import game.settings.MutualGameSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.game.events.impl.MenuOptionUpdateEvent;

/**
 * @author Mateusz Żebrowski
 */
public class GameMatchControllerImpl implements GameMatchController {

    @Autowired
    private InputController inputController;

    @Autowired
    private GraphicsController graphicsController;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private GameObjectsController gameObjectsController;

    private RoundPhase actualRoundPhase;

    private MutualGameSettings mutualGameSettings;

    @Override
    public void updateRoundTime() {

    }

    @Override
    public void updateGraphics() {

    }

    @Override
    public void endRound() {

    }

    @Override
    public void update() {
        if (inputController.isExitKeyPressed() && actualRoundPhase != RoundPhase.PAUSED) {
            publisher.publishEvent(new MenuOptionUpdateEvent(MenuOption.CONTINUE));
        } else if (actualRoundPhase == RoundPhase.BEGINS) {
            gameObjectsController.initializeBasicGameObjects(mutualGameSettings);
            publisher.publishEvent(new RoundPhaseUpdateEvent(RoundPhase.CONTINUES));
        } else if (actualRoundPhase == RoundPhase.ENDS) {

        } else {
            gameObjectsController.update();
            updateGameState();
        }
    }

    private void updateGameState() {

    }

    @Override
    public void handleRoundPhaseUpdate(RoundPhaseUpdateEvent roundPhaseUpdateEvent) {
        actualRoundPhase = roundPhaseUpdateEvent.getRoundPhase();
        if (!roundPhaseUpdateEvent.getMutualGameSettings().isEmpty()) {
            mutualGameSettings = roundPhaseUpdateEvent.getMutualGameSettings();
        }
    }
}
