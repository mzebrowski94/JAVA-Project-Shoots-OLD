package game.dtos;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Mateusz Żebrowski
 */

@Builder
@Getter
public class PlayerDto {
    private int playerNumber;
    private int pointsEarned;
    private int roundsWon;
    private boolean winner;
}
