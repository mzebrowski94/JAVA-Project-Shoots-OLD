package java.game.settings;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */

@Component
@Getter
public class RoundSettings {

    int maxRoundTime;

    int maxRoundDelay;


}
