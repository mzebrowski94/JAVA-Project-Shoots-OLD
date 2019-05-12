package game.settings;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */

@Component
public class InputSettings {

    @Getter
    @Value("key.exit")
    private int exitKey;

    @Getter
    @Value("key.submit")
    private int submitKey;

    @Getter
    @Value("key.menu.navigation.up")
    private int menuNavigationUpKey;

    @Getter
    @Value("key.menu.navigation.down")
    private int menuNavigationDownKey;

    @Getter
    @Value("key.menu.navigation.left")
    private int menuNavigationLeftKey;

    @Getter
    @Value("key.menu.navigation.right")
    private int menuNavigationRightKey;

    @Value("${player.one.input}")
    private int[] playerOneInput;

    @Value("${player.two.input}")
    private int[] playerTwoInput;

    @Value("${player.three.input}")
    private int[] playerThreeInput;

    @Value("${player.four.input}")
    private int[] playerFourInput;

    public PlayerControls getPlayerInput(int number) {
        if (number == 1) {
            return new PlayerControls(playerOneInput);
        } else if (number == 2) {
            return new PlayerControls(playerTwoInput);
        } else if (number == 3) {
            return new PlayerControls(playerThreeInput);
        } else if (number == 4) {
            return new PlayerControls(playerFourInput);
        } else {
            return new PlayerControls(new int[]{0,0,0});
        }
    }
}
