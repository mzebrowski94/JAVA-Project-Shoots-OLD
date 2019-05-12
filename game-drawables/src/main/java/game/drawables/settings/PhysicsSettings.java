package game.drawables.settings;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */
@Component
@Getter
public class PhysicsSettings {

    @Value("cursor.move.angle")
    int cursorMoveAngle;

    @Value("disc.move.speed")
    int discMoveSpeed;
}
