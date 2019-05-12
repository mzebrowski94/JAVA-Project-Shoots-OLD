package game.services.impl;

import game.consts.MenuOption;
import game.factories.PlayersFactory;
import game.gameobjects.player.Player;
import game.interfaces.GameInput;
import game.services.PlayersScoresService;
import game.services.PlayersService;
import game.settings.InternalSettings;
import game.settings.MutualGameSettings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Mateusz Żebrowski
 */
public class PlayersServiceImpl implements PlayersService {

    @Autowired
    private PlayersFactory playersFactory;

    @Autowired
    private PlayersScoresService playersScoresService;

    @Autowired
    private GameInput keyboardInput;

    @Autowired
    private InternalSettings internalSettings;

    private List<Player> players;

    @Override
    public void initializePlayers(MutualGameSettings mutualGameSettings) {
        int playersNumber = (int) mutualGameSettings.get(MenuOption.PLAYER_NUMBER_OPTION).getValue();
        players = playersFactory.createNewPlayers(playersNumber);
    }

    @Override
    public void updateWithInput(GameInput gameInput) {
        players.forEach(player -> player.updateWithInput(gameInput));
    }
}
