package game.drawables.menu;

import game.consts.MenuOption;

import java.awt.*;

/**
 * @author Mateusz Żebrowski
 */
public class ChangeableMenuOptionPanel<T> extends MenuOptionPanel {

    public ChangeableMenuOptionPanel(MenuOption menuOption, T initValue, Color baseColor, Color activeColor) {
        super(menuOption, initValue.toString(), baseColor, activeColor);
    }

    public String getLabel(){
        return "            < " + this.label + " >";
    }
}
