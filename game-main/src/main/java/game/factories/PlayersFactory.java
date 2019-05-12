package game.factories;

import game.drawables.settings.ColorScheme;
import game.drawables.settings.PhysicsSettings;
import game.gameobjects.player.Player;
import game.gameobjects.player.PlayerBase;
import game.settings.InputSettings;
import game.settings.InternalSettings;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;


/**
 * @author Mateusz Żebrowski
 */
@Component
public class PlayersFactory {

    @Autowired
    private ColorScheme colorScheme;

    @Autowired
    private InputSettings inputSettings;

    @Autowired
    private InternalSettings internalSettings;

    @Autowired
    private PhysicsSettings physicsSettings;

    @Getter
    private List<Player> players;

    public List<Player> createNewPlayers(int amountOfPlayers) {
        players.clear();
        if (amountOfPlayers < 1 || amountOfPlayers > internalSettings.getMaxNumberOfPlayers()) {
            throw new IllegalStateException(String.format("Player number must be between: [%s:%s]", 1, internalSettings.getMaxNumberOfPlayers()));
        } else {
            IntStream.range(1, amountOfPlayers)
                    .forEachOrdered(playerNumber -> players.add(createPlayer(playerNumber)));
        }
        return players;
    }

    private Player createPlayer(int playerNumber) {
        return Player.builder()
                .number(playerNumber)
                .color(colorScheme.getPlayerColor(playerNumber))
                .controls(inputSettings.getPlayerInput(playerNumber))
                .configuration(internalSettings.getPlayerConfiguration(playerNumber))
                .name(String.format("Player %s", playerNumber))
                .base(createPlayerBase())
                .cursor()
                .laser()
                .discs()
                .build();
    }

    private PlayerBase createPlayerBase(){
        return null;
    }
}
