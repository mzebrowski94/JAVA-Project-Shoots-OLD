package game.gameobjects.player.components;

import lombok.Getter;

/**
 * @author Mateusz Żebrowski
 */
@Getter
public class PlayerConfiguration {

    private int positionX;
    private int positionY;
    private int moveUnit;
    private int discLimit;

    private PlayerConfiguration(int positionX, int positionY, int moveUnit, int discLimit) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.moveUnit = moveUnit;
        this.discLimit = discLimit;
    }

    public PlayerConfiguration(int[] playerConfiguration, int discLimit) {
        this(playerConfiguration[0], playerConfiguration[1], playerConfiguration[2], discLimit);
    }
}

