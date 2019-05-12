package game;

import game.consts.GameState;
import game.events.GameStateUpdateEvent;
import game.gui.service.GraphicsController;
import game.listeners.GameStateUpdateListener;
import game.controllers.GameMatchController;
import game.controllers.InputController;
import game.controllers.MenuController;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa stanowiaca główny obiekt w grze, w klasie tej odbywa wywoływanie całej
 * logiki występującej w grze Odpowiedzialna jest, za: -obsługę głównej pętli
 * gry -zmianę stanu rundy -wywoływanie funkcji renderujących grafikę
 * -wywoływanie fukcji odpowiedzialny za obsługę klawiatury
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */

@Log4j2
@Component
@NoArgsConstructor
public final class GameLoop extends Thread implements GameStateUpdateListener {

    @Autowired
    private GameMatchController gameMatchController;

    @Autowired
    private MenuController menuController;

    @Autowired
    private GraphicsController graphicsController;

    @Autowired
    private InputController inputController;

    private static final int TARGET_FPS = 120;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    private GameState gameState = GameState.MENU;

    @Override
    public void run() {
        super.run();
        runGameLoop();
    }

    private void runGameLoop() {
        boolean isRunning = true;

        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;

        while (isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            //double delta = updateLength / ((double) OPTIMAL_TIME);
            lastFpsTime += updateLength;
//            lastFpsTime >= OPTIMAL_TIME

            if (lastFpsTime >= 1000000000) {
                inputController.pollInput();
                graphicsController.render();
                lastFpsTime = 0;

                if (gameState == GameState.MENU) {
                    menuController.update();
                    menuController.updateGraphics();
                } else if (gameState == GameState.GAME) {
                    gameMatchController.update();
                    gameMatchController.updateGraphics();
                    //TODO - GamePanelController.update()
                } else if (gameState == GameState.ANIMATION) {
                    //TODO - transitionAnimationService.update()
                } else if (gameState == GameState.QUIT) {
                    graphicsController.dispose();
                    isRunning = false;
                } else {
                    throw new IllegalStateException(String.format("Unknown game state: [%s]", gameState));
                }
            }

            long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    @EventListener
    public void handleGameStateUpdated(GameStateUpdateEvent gameStateUpdateEvent) {
        gameState = gameStateUpdateEvent.getGameState();
    }

    //TODO - ANIMACJA WYŁĄCZANIA GRY
//                            if (graphicsController.isAnimationEnded()) {
//        previousRoundState.changeTo(BEGINS);
//        graphicsController.restartAnimationTimers();
//        roundStateChanged = true;
//        if (menuData.getState() == MenuOption.QUIT) {
//            isRunning = false;
//        }
//    }
}
