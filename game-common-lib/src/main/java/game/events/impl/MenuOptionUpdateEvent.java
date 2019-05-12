package java.game.events.impl;

import game.consts.GameState;
import game.consts.MenuOption;
import game.events.GameStateUpdateEvent;
import lombok.Getter;

/**
 * @author Mateusz Żebrowski
 */
public class MenuOptionUpdateEvent implements GameStateUpdateEvent {

    @Getter
    private final MenuOption menuOption;

    public MenuOptionUpdateEvent(MenuOption menuOption) {
        this.menuOption = menuOption;
    }

    @Override
    public GameState getGameState() {
        return GameState.MENU;
    }
}
