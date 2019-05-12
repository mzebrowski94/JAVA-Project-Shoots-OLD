package game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import game.gameobjects.player.Player;
import game.interfaces.GameData;
import game.units.PlayerScore;
import game.units.Round;
import java.game.consts.RoundPhase;

import java.util.ArrayList;

/**
 * @author Mateusz Żebrowski
 */

@Builder
@Getter
@Setter
public class LogicData implements GameData {

    ArrayList<Player> players;
    PlayerScore playerScore;
    ArrayList<Round> rounds;

    int actualRoundNumber;

    boolean playerKeyboardAvailable;
    int roundEndDelay;
    Round actualRound, previousRound;
    boolean gameEnd;

    private RoundPhase roundPhase, previousRoundPhase;

    private boolean changeRoundState = false;
    private boolean roundStateChanged = false;
    private boolean gamePaused = false;

    LogicData(playersNumber, playerList);


    /**
     * Metoda używana do zainicjalizowanie danej ilość graczy
     */

    @Override
    public void init() {
        initializePlayers();
    }

    @Override
    public void reset() {

    }

    private void initializePlayer(int number) {
        playerList.add(new Player(number, this));
    }

    private ArrayList<Player> initializePlayers(int playerNumber, ArrayList<Player> playerList) {
        playerList.clear();
        if (playerNumber >= 1) {
            initializePlayer(1);
        }
        if (playerNumber >= 2) {
            initializePlayer(2);
        }
        if (playerNumber >= 3) {
            initializePlayer(3);
        }
        if (playerNumber >= 4) {
            initializePlayer(4);
        }

        return playerList;
    }

    /**
     * Getter pobierający z listy graczy gracza o podanym indexsie
     * @param x index gracza w lisie graczy
     * @return gracza o podanym indexsie
     */
    public Player getPlayer(int x) {
        if (x == 0 || x == 1 || x == 2) {
            return playerList.get(x);
        } else {
            return playerList.get(3);
        }
    }



}
this.actualRoundNumber = 0;
this.playerKeyboardAvailable = true;
        this.roundEndDelay = 2;
                this.animationTime = 1;