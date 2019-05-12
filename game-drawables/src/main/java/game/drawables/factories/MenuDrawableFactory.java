package game.drawables.factories;

import game.consts.MenuOption;
import game.drawables.menu.ChangeableMenuOptionPanel;
import game.drawables.menu.MenuDrawable;
import game.drawables.menu.MenuOptionPanel;
import game.drawables.menu.ScorePanel;
import game.drawables.settings.ColorScheme;
import game.drawables.settings.GraphicsSettings;
import game.drawables.settings.Localization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateusz Żebrowski
 */

@Component
public class MenuDrawableFactory {

    @Autowired
    ColorScheme colorScheme;

    @Autowired
    Localization localization;

    @Autowired
    GraphicsSettings graphicsSettings;

    public MenuDrawable produceMenuDrawable() {
        return MenuDrawable.builder()
                .height(graphicsSettings.getHeight())
                .width(graphicsSettings.getWidth())
                .menuFont(graphicsSettings.getMenuFont())
                .optionPanels(createOptionPanels())
                .scorePanel(createScorePanel())
                .build();
    }

    private ScorePanel createScorePanel() {
        return ScorePanel.builder()
                .height(graphicsSettings.getHeight())
                .width(graphicsSettings.getWidth())
                .roundsLocale(localization.getScoreRounds())
                .pointsLocale(localization.getScorePoints())
                .winnerLocal(localization.getScoreWinner())
                .build();
    }

    private List<MenuOptionPanel> createOptionPanels() {
        List<MenuOptionPanel> optionPanels = new ArrayList<>();

        Color activeColor = colorScheme.getActiveMenuOption();
        Color inactiveColor = colorScheme.getInactiveColor();

        optionPanels.add(new MenuOptionPanel(MenuOption.CONTINUE, localization.getMenuOptionContinue(), inactiveColor, activeColor));
        optionPanels.add(new MenuOptionPanel(MenuOption.START_NEW_GAME, localization.getMenuOptionNewGame(), inactiveColor, activeColor));
        optionPanels.add(new MenuOptionPanel(MenuOption.PLAYER_NUMBER_OPTION, localization.getMenuOptionNumberOfPlayers(), inactiveColor, activeColor));
        optionPanels.add(new ChangeableMenuOptionPanel<>(MenuOption.PLAYER_NUMBER_OPTION, 0, inactiveColor, activeColor));
        optionPanels.add(new MenuOptionPanel(MenuOption.ROUND_NUMBER_OPTION, localization.getMenuOptionRoundLimit(), inactiveColor, activeColor));
        optionPanels.add(new ChangeableMenuOptionPanel<>(MenuOption.ROUND_NUMBER_OPTION, 0, inactiveColor, activeColor));
        optionPanels.add(new MenuOptionPanel(MenuOption.ROUND_TIME_OPTION, localization.getMenuOptionRoundTime(), inactiveColor, activeColor));
        optionPanels.add(new ChangeableMenuOptionPanel<>(MenuOption.ROUND_TIME_OPTION, 0, inactiveColor, activeColor));
        optionPanels.add(new MenuOptionPanel(MenuOption.QUIT, localization.getMenuOptionQuit(), inactiveColor, activeColor));

        return optionPanels;
    }
}
