package game.services.impl;

import game.gui.service.GraphicsController;
import game.services.RoundLogicService;
import mzebrowski.projectshoots.GameSettingsImpl;
import game.gameobjects.disc.Disc;
import game.gameobjects.effects.LightEffect;
import game.controllers.InputController;
import game.LogicSettings;
import game.logic.ColisionCalculator;
import game.services.RoundChangeListener;
import game.settings.MutualGameSettings;
import game.units.ColisionPoint;
import game.gameobjects.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class RoundLogicServiceImpl implements RoundLogicService {

    @Autowired
    ColisionCalculator colisionCalculator;

    @Autowired
    LogicSettings logicSettings;

    @Autowired
    RoundChangeListener gameObjectsService;


    @Autowired
    private InputController inputController;

    @Autowired
    private GraphicsController graphicsController;

    private RoundLogicServiceImpl(){}

    private int numberOfPlayers;

    @Override
    public void restartOnNewGame(MutualGameSettings mutualGameSettings) {
        numberOfPlayers = mutualGameSettings.getNumberOfPlayers();
        gameObjectsService.restartOnNewGame(mutualGameSettings);
    }

    @Override
    public void prepareOnNewRound(MutualGameSettings mutualGameSettings) {
        gameObjectsService.prepareOnNewRound(mutualGameSettings);

    }

    /**
     * Metoda odpowiadająca za uaktualnienie elementów logiki, zmiany sanów
     * obiektów w grze
     */
    public void updateGameLogic() {
        for (Player player : gameObjectsService.getPlayers()) {

            player.getLaser().moveLaser();

            for (Disc disc : player.getDiscs()){
                disc.moveDisc();

                ColisionPoint colisionPoint = colisionCalculator.checkColision(disc);

                if (Objects.nonNull(colisionPoint)) {
                    if (disc.reachedMaxColisions()) {
                        player.removeDisc(disc);
                    } else if (colisionPoint.getColisionType() == 0) {
                        if (graphicsController.updatePointField(colisionPoint, player, disc.getColisionTimes())) {
                            player.removeDisc(j);
                        }
                    } else {
                        graphicsController.gameScreen.getEffectList().add(new LightEffect(player.getColor(), colisionPoint));
                    }
                }
            }
        }
        //CALCULATE POINTS AFTER ALL
    }

    /**
     * Metoda służca sprawdzaniu czy na ekranie gry znajdują się jeszcze obiekty typu Disc
     *
     * @return liczba pozostałych obiektów typu Disc
     */
    private int checkDiscsAmount() {
        int discAmount = 0;
        for (int i = 0; i < gameSettings.getPlayerList().size(); i++) {
            Player player = gameSettings.getPlayerList().get(i);
            for (int j = 0; j < player.getDiscs().size(); j++) {
                discAmount++;
            }
        }
        return discAmount;
    }

    @Override
    public void createGameObjects() {

    }

    @Override
    public void anyDiscInGame() {

    }


    @Override
    public boolean isAnyDiscOnGameBoard() {
        return false;
    }

    /**
     * Metoda odpowiedzialna, za zapisanie punktów zdobytych przez graczy
     * podczas tej rundy
     */
    public void savePlayerPoints() {
        for (int i = 0; i < gS.getPlayerList().size(); i++) {
            playerPointsList.add(gS.getPlayerList().get(i).getPoints());
            gS.getPlayerList().get(i).increaseAllPointsErned(playerPointsList.get(i));
        }
    }

    /**
     * Metoda sumująca punkty i sprawdzająca który z podanych graczy daną rundę
     * wygrał
     */
    public void checkRoundWinner() {
        int bestRoundScore = 0;

        for (int i = 0; i < gS.getPlayerList().size(); i++) {
            if (playerPointsList.get(i) > bestRoundScore) {
                bestRoundScore = playerPointsList.get(i);
            }
        }

        for (int i = 0; i < gS.getPlayerList().size(); i++) {
            if (playerPointsList.get(i) == bestRoundScore) {
                gS.getPlayerList().get(i).increaseRoundsWon();
            }
        }

    }

    @Override
    public void restartOnNewGame() {
        players = initializePlayers(playersNumber, players);
    }

    @Override
    public void prepareOnNewRound() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).resetPlayerCursor();
        }

        actualRound.getPlayerPoints().updatePlayerPoints();
        mapMatrix.reInitializeMap();
        gameScreen.reInitializeMapPanel();
    }

    /**
     * Metoda restartująca ustawienia gry w przygotowaniu do nowej rozgrywki
     */
    public void restartGameSettings() {
        gameSettings.restartGame();
        graphicsController.gamePointer.restartGamePointer();
        graphicsController.gameCounter.restartAnimationTime();
        gameSettings.startNewRound(graphicsController.gameScreen);
    }

    /**
     * Konstruktor klasy RoundLogicServiceImpl
     *
     * @param gameSettings argument przyjmujący ustawienia gry
     */
    public RoundLogicServiceImpl(GameSettingsImpl gameSettings) {
        gS = gameSettings;
        pointFields = new ArrayList<>();
        playerList = gameSettings.getPlayerList();
        maxPointsAmount = 8 * 4;
    }

    /**
     * Metoda sprawdzająca czy podane pole punktowe zostało przejęte, oraz
     * obsługuje zdarzenie kolizji dysku z danym punktem
     *
     * @param colisionPoint argument przyjumjący wartości punktu w którym
     * nastąpiło przejęcie
     * @param player argument przyjmujący informacje o graczu
     * @param colisionTimes argument przyjmujący informację o tym ile razy
     * wcześnej dany obiekt typu Disc odbił się od innych obiektów
     * @return zwrac wartość boolean: - true jeśli punkt udało się zdobyć -
     * false jeśli punktu nie udało się zdobyć
     */
    public boolean checkPointFiledErned(ColisionPoint colisionPoint, Player player, int colisionTimes) {
        boolean result = false;

        for (int i = 0; i < pointFields.size(); i++) {
            if (pointFields.get(i).getInX() == colisionPoint.getIndexX() && pointFields.get(i).getInY() == colisionPoint.getIndexY()) {
                result = pointFields.get(i).chckIfPointFieldErned(player, colisionTimes);
                if (result) {
                    changed = true;
                    updatePlayerPoints();
                }
            }
        }

        return result;
    }

    /**
     * Metoda sprawdzając czy podane pole punktowe zostało przejęte, jeśli
     * tak, odpowiednia ilość punktów jest przypisywana odpowiedniemu graczowi
     */
    public void updatePlayerPoints() {

        for (int i = 0; i < gS.getPlayersNumber(); i++) {
            playerList.get(i).setPoints(0);
        }

        for (int i = 0; i < pointFields.size(); i++) {
            actualPoint = pointFields.get(i).getPoint();
            if (actualPoint.isErned()) {
                if (actualPoint.getPlayerNumber() == 1) {
                    playerList.get(0).addPoints(actualPoint.getErnedPoints());

                } else if (actualPoint.getPlayerNumber() == 2) {
                    playerList.get(1).addPoints(actualPoint.getErnedPoints());

                } else if (actualPoint.getPlayerNumber() == 3) {
                    playerList.get(2).addPoints(actualPoint.getErnedPoints());
                } else if (actualPoint.getPlayerNumber() == 4) {
                    playerList.get(3).addPoints(actualPoint.getErnedPoints());
                }
            }
        }

    }
}
