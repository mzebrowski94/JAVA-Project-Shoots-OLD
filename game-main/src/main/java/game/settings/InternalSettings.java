package game.settings;

import game.gameobjects.player.components.PlayerConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */

@Component
public class InternalSettings {

    @Getter
    @Value("game.default.number.of.players")
    int defaultPlayersNumber;

    @Getter
    @Value("game.default.round.number")
    int defaultRoundNumber;

    @Getter
    @Value("game.default.round.time.in.seconds")
    int defaultRoundTime;

    @Getter
    @Value("max.number.of.players")
    int maxNumberOfPlayers;

    @Getter
    @Value("min.round.time.in.seconds")
    int minRoundTime;

    @Getter
    @Value("round.time.increment")
    int roundTimeIncrement;

    @Getter
    @Value("max.discs.limit")
    int discsLimit;

    @Value("configuration.player.one")
    private int[] configurationPlayerOne;

    @Value("configuration.player.two")
    private int[] configurationPlayerTwo;

    @Value("configuration.player.three")
    private int[] configurationPlayerThree;

    @Value("configuration.player.four")
    private int[] configurationPlayerFour;

    public PlayerConfiguration getPlayerConfiguration(int playerNumber){
        switch (playerNumber) {
            case 1:
                return new PlayerConfiguration(configurationPlayerOne, discsLimit);
            case 2:
                return new PlayerConfiguration(configurationPlayerTwo, discsLimit);
            case 3:
                return new PlayerConfiguration(configurationPlayerThree, discsLimit);
            case 4:
                return new PlayerConfiguration(configurationPlayerFour, discsLimit);
            default:
                return new PlayerConfiguration(new int[]{0,0,0}, discsLimit);
        }
    }
}
