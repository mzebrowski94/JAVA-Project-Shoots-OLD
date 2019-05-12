package game.factories;

import game.consts.MenuOption;
import game.settings.ChangeableMenuSetting;
import game.settings.MutualGameSettings;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class MenuGameSettingsService {

    @Value("${game.default.number.of.players}")
    private int defaultNumberOfPlayers;

    @Value("${game.default.round.number}")
    private int defaultRoundNumber;

    @Value("${game.default.round.time.in.seconds}")
    private int defaultRoundTime;

    @Value("${max.number.of.players}")
    private int maxNumberOfPlayers;

    @Value("${min.round.time.in.seconds}")
    private int minRoundTime;

    @Value("${max.round.time.in.seconds}")
    private int maxRoundTime;

    @Value("${max.round.number}")
    private int maxRoundsNumber;

    @Value("${round.time.incrementation}")
    private int roundTimeIncrement;

    @Getter
    private MutualGameSettings settings;

    @PostConstruct
    private void init() {
        settings = createSettings();
    }

    private MutualGameSettings createSettings() {
        ChangeableMenuSetting<Integer> numberOfPlayers
                = addIntegerSetting(defaultNumberOfPlayers, 1, 1, maxNumberOfPlayers, MenuOption.PLAYER_NUMBER_OPTION);
        ChangeableMenuSetting<Integer> roundsNumber
                = addIntegerSetting(defaultRoundNumber, 1, 1, maxRoundsNumber, MenuOption.ROUND_NUMBER_OPTION);
        ChangeableMenuSetting<Integer> roundTime
                = addIntegerSetting(defaultRoundTime, roundTimeIncrement, minRoundTime, maxRoundTime, MenuOption.ROUND_TIME_OPTION);

        settings = new MutualGameSettings();
        settings.put(numberOfPlayers.getMenuOption(), numberOfPlayers);
        settings.put(roundsNumber.getMenuOption(), roundsNumber);
        settings.put(roundTime.getMenuOption(), roundTime);
        return settings;
    }

    public ChangeableMenuSetting getSetting(MenuOption menuOption) {
        return settings.get(menuOption);
    }

    private ChangeableMenuSetting<Integer> addIntegerSetting(Integer value, Integer incrementation, Integer minValue, Integer maxValue, MenuOption menuOption) {
        return new ChangeableMenuSetting<Integer>(value, incrementation, minValue, maxValue, menuOption) {
            @Override
            protected Integer increment() {
                return value + incrementation;
            }

            @Override
            protected Integer decrement() {
                return value - incrementation;
            }
        };
    }
}
