package game.services.impl;

import game.consts.MenuNavigationKey;
import game.consts.MenuOption;
import game.settings.ChangeableMenuSetting;
import game.factories.MenuGameSettingsService;
import game.services.MenuLogicService;
import game.settings.MutualGameSettings;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.iterators.LoopingListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class MenuLogicServiceImpl implements MenuLogicService {

    @Autowired
    private MenuGameSettingsService menuGameSettingsService;

    @Getter
    @Setter
    private MenuOption actuallyPointedOption = MenuOption.START_NEW_GAME;

    private LoopingListIterator<MenuOption> menuIterator;

    @Getter
    private boolean gameContinues = false;

    @Override
    public MutualGameSettings getMenuSettings() {
        return menuGameSettingsService.getSettings();
    }

    @PostConstruct
    private void init() {
        menuIterator = new LoopingListIterator<>(Arrays.asList(MenuOption.values()));
    }

    /**
     * Metoda służaca do zmiany podświetlanego elementu menu przy zmianię opcji
     * menu w górę
     */
    @Override
    public void updateMenuState(MenuNavigationKey menuNavigationKey) {

        switch (menuNavigationKey) {
            case UP:
                actuallyPointedOption = menuIterator.previous();
                break;
            case DOWN:
                actuallyPointedOption = menuIterator.next();
                break;
            case LEFT:
                menuGameSettingsService.getSetting(actuallyPointedOption).decrease();
                break;
            case RIGHT:
                menuGameSettingsService.getSetting(actuallyPointedOption).increase();
                break;
        }
    }
}
