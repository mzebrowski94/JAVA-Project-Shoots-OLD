package game.settings;

import lombok.Getter;

/**
 * @author Mateusz Żebrowski
 */
@Getter
public class PlayerControls {

    private final int leftKey;

    private final int rightKey;

    private final int shootKey;

    public PlayerControls(int[] keyInputs){
        leftKey = keyInputs[0];
        rightKey = keyInputs[1];
        shootKey = keyInputs[2];
    }


}
