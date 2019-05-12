package game.services;

import game.consts.MenuNavigationKey;
import game.consts.MenuOption;
import game.settings.ChangeableMenuSetting;
import game.settings.MutualGameSettings;

import java.util.Map;

/**
 * @author Mateusz Żebrowski
 */
public interface MenuLogicService {

    void setActuallyPointedOption(MenuOption actuallyPointedOption);

    MenuOption getActuallyPointedOption();

    MutualGameSettings getMenuSettings();

    boolean isGameContinues();

    void updateMenuState(MenuNavigationKey menuNavigationKey);
}
