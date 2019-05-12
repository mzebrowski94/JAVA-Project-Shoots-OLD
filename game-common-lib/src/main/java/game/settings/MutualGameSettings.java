package game.settings;

import game.consts.MenuOption;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mateusz Żebrowski
 */
public class MutualGameSettings extends HashMap<MenuOption, ChangeableMenuSetting> {

    public static MutualGameSettings getEmpty(){
        Map<MenuOption, ChangeableMenuSetting> empty = Collections.emptyMap();
        return (MutualGameSettings) empty;
    }
}
