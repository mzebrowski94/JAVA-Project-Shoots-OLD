package game.gui.service;


import game.interfaces.Drawable;
import game.interfaces.GameController;

/**
 * @author Mateusz Żebrowski
 */
public interface GraphicsController extends GameController {

    void updateScore();

    boolean isAnimationEnded();

    void restartAnimationTimers();

    void gameRenderUpdate();

    void dispose();

    void render();

    void addToDrawQueue(Drawable menuDrawable);
}
