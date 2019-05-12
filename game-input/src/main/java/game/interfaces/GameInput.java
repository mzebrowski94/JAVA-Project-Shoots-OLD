package game.interfaces;

import java.awt.event.KeyListener;

/**
 * @author Mateusz Żebrowski
 */
public interface GameInput extends KeyListener {

    void poll();

    boolean keyDown(int keyCode);

    boolean keyDownOnce(int keyCode);
}
