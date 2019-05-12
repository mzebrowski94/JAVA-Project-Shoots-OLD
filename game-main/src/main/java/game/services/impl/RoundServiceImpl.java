package game.services.impl;

import java.game.MenuData;
import java.game.consts.MenuOption;
import java.game.consts.RoundPhase;
import java.game.events.impl.RoundPhaseUpdateEvent;
import java.game.services.GameStateService;
import java.game.services.RoundService;

import game.gui.service.GraphicsController;
import game.gui.service.impl.GraphicsControllerImpl;
import game.controllers.InputController;
import game.services.RoundLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class RoundServiceImpl implements RoundService {

    @Autowired
    private InputController inputController;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private GameStateService gameStateService;

    @Autowired
    private RoundLogicService roundLogicService;

    @Autowired
    private GraphicsController graphicsController;

    private RoundPhase roundPhase;

    @Override
    public void updateRoundTime() {
        if (gameStateService.roundTimeEnded() && !roundCanBeEnded()) {
            gameStateService.getActualRound().delayRoundBySec(1);
        }
        gameStateService.getActualRound().roundTick();
    }

    private boolean roundCanBeEnded() {
        return roundLogicService.isAnyDiscOnGameBoard();
    }

    @Override
    public boolean isRoundEnded() {
        return gameStateService.getActualRound().isEnded();
    }

    @Override
    public RoundPhase updateGameLogic() {
        roundLogicService.updateGameLogic();
        return null;
    }

    @Override
    public void updateGraphics() {
        graphicsController.update();
        graphicsController.updateScore();
    }

    private void restartGame() {
        gameStateService.restartOnNewGame();
        roundLogicService.restartOnNewGame();

    }

    private void startNewRound() {
        gameStateService.prepareOnNewRound();
        roundLogicService.prepareOnNewRound();
    }

    /**
     * Metoda odpowiedzialna, za inicjializacje okienka gry
     *
     * @return boolean zwraca czy inicializacja grafiki (stworzenie okienka GameFrame) odbyło się prawidłowo
     */
    private void initializeGraphics() {
        graphicsController = new GraphicsControllerImpl(gameSettings);
    }

    private void initializeLogic() {
        gameSettings.startNewRound(graphicsController.gameScreen);
        gameRoundState = RoundPhase.PAUSED;
        gameSettings.setActualRoundNumber(0);
    }

    @Override
    public void endRound() {
        gameStateService.getActualRound().savePlayerPoints();
        gameStateService.getActualRound().checkRoundWinner();
    }

    @Override
    public void update() {

        if (roundPhase.equals(PAUSED)) {
            gamePaused();
        } else if (roundPhase.equals(CONTINUES)) {
            gameContinues(roundPhase);
        } else if (roundPhase.equals(ENDS)) {
            gameEnds(roundPhase);
        } else if (roundPhase.equals(BEGINS)) {
            gameBegins(menuData);
            if (graphicsController.isAnimationEnded()) {
                graphicsController.restartAnimationTimers();
                roundPhase.changeTo(CONTINUES);
            }
        } else if (roundPhase == PAUSED) {
            roundStateChanged = true;
        }


        graphicsController.gameRenderUpdate(roundPhase);
    }

    private void gameBegins(MenuData menuData) {
        if (menuData.getState() == MenuOption.START_NEW_GAME) {
            restartGame();
            menuData.setState(MenuOption.NO_OPTION);
        } else {
            startNewRound();
        }
    }


    private void gameEnds(RoundPhase roundPhase) {
        if (roundPhase.isJustChanged()) {
            inputController.disablePlayersInput();
            roundService.endRound();
        }

        if (graphicsController.isAnimationEnded()) {
            roundPhase.changeTo(BEGINS);
            graphicsController.restartAnimationTimers();
            if (gameStateService.checkGameEnd()) {
                roundPhase.changeTo(PAUSED);
            }
        }
    }

    private void gameContinues(RoundPhase roundPhase) {
        if (roundPhase.isJustChanged()) {
            inputController.enablePlayersInput();
        }

        RoundPhase.RoundState updatedState = roundService.updateGameLogic();
        if (!roundPhase.equalsState(updatedState)) {
            roundPhase.changeTo(updatedState);
        }

        if (inputController.isInputAvailable()) {
            inputController.checkPlayerInput();
        }

        if (roundPhase.isJustChanged()) {
            roundPhase.changeTo(ROUND_ENDS);
        }
    }

    private void gamePaused() {
        boolean changeRoundState;
        roundService.updateRoundTime();

        if (roundService.isRoundEnded()) {
            inputController.disablePlayersInput();
            changeRoundState = true;
        }
    }

    @Override
    @EventListener
    public void handleRoundPhaseUpdate(RoundPhaseUpdateEvent roundPhaseUpdateEvent) {

    }
}
