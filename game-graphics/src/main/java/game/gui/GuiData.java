package game.gui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import game.interfaces.GameData;

/**
 * @author Mateusz Żebrowski
 */

@Builder
@Getter
@Setter
public class GuiData implements GameData {

    int animationTime;

    @Override
    public void init() {

    }

    @Override
    public void reset() {

    }
}
