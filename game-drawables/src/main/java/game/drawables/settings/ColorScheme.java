package game.drawables.settings;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @author Mateusz Żebrowski
 */

@Component
public class ColorScheme {

    @Getter
    private Color baseMenuOption;
    @Getter
    private Color activeMenuOption;
    @Getter
    private Color inactiveColor;

    private Color player1Color;
    private Color player2Color;
    private Color player3Color;
    private Color player4Color;

    public Color getPlayerColor(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return player1Color;
            case 2:
                return player2Color;
            case 3:
                return player3Color;
            case 4:
                return player4Color;
            default:
                return Color.BLACK;
        }
    }
}
