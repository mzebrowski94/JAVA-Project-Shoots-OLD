package game.controllers.impl;

//import java.game.consts.MenuOption;

import game.KeyboardInput;
import game.consts.MenuNavigationKey;
import game.controllers.InputController;
import game.settings.InputSettings;
//import mzebrowski.projectshoots.input.KeyboardInput;
//import mzebrowski.projectshoots.input.InputSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Mateusz Żebrowski
 */

@Component
public class InputControllerImpl implements InputController {


    @Autowired
    private InputSettings inputSettings;

    private KeyboardInput keyboard;

    private boolean isInputAvailable;

    @Override
    public void disablePlayersInput() {
        isInputAvailable = false;
    }

    @Override
    public void enablePlayersInput() {
        isInputAvailable = true;
    }

    @Override
    public void pollInput() {
        keyboard.poll();
    }

    @Override
    public void checkPlayerInput() {

    }

    //TODO
//    /**
//     * Metoda odpowidzialna za sprawdzanie sygnałów z klawiatury dla poszczególnych graczy
//     */
//    public void checkPlayerInput() {
//        for (int i = 0; i < playerList.size(); i++) {
//            playerList.get(i).checkPlayerInput();
//        }
//    }
//
//    @Override
//    public MenuOption checkMenuInput() {
//        return null;
//    }

    @Override
    public boolean isSubmitKeyPressed() {
        return keyboard.keyDownOnce(inputSettings.getSubmitKey());
    }

    @Override
    public MenuNavigationKey getMenuNavigationKeyPressed() {
        if (keyboard.keyDownOnce(inputSettings.getMenuNavigationUpKey())) {
            return MenuNavigationKey.UP;
        } else if (keyboard.keyDownOnce(inputSettings.getMenuNavigationDownKey())) {
            return MenuNavigationKey.DOWN;
        } else if (keyboard.keyDownOnce(inputSettings.getMenuNavigationLeftKey())) {
            return MenuNavigationKey.LEFT;
        } else if (keyboard.keyDownOnce(inputSettings.getMenuNavigationRightKey())) {
            return MenuNavigationKey.RIGHT;
        } else {
            return null;
        }
    }

    @PostConstruct
    public void init() {
        keyboard = new KeyboardInput();
    }

    @Override
    public boolean isExitKeyPressed() {
        return keyboard.keyDownOnce(inputSettings.getExitKey());
    }

    @Override
    public boolean isInputAvailable() {
        return isInputAvailable;
    }
}
