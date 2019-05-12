package game.controllers;

import game.consts.MenuNavigationKey;

/**
 * @author Mateusz Żebrowski
 */
public interface InputController {

    boolean isExitKeyPressed();

    boolean isInputAvailable();
    
    void disablePlayersInput();

    void enablePlayersInput();

    void pollInput();

    void checkPlayerInput();

    boolean isSubmitKeyPressed();

    MenuNavigationKey getMenuNavigationKeyPressed();
}
