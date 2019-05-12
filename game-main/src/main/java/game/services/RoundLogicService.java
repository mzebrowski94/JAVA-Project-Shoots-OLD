package game.services;
/**
 * @author Mateusz Żebrowski
 */
public interface RoundLogicService {

    void createGameObjects();

    void updateGameLogic();

    void anyDiscInGame();

    boolean isAnyDiscOnGameBoard();
}
