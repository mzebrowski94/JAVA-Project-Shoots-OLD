
package game.interfaces;

import java.awt.Graphics2D;

/**
 * Interfejs który implementuje część obiektów które rysowane są na głównym ekranie gry GameScreen
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public interface Drawable {

    /**
     * Metoda odpowiedzialna za wywoływanie resyowania obiektów dzieciczących interface Drawable
 * @param g2d przyjmuje obiekt typu Graphics2D służący do rysowania elementów w grze
     */
    default void draw(Graphics2D g2d) {
        draw(g2d, 0, 0);
    }

    /**
     * Metoda odpowiedzialna za wywoływanie resyowania obiektów dzieciczących interface Drawable
     * @param g2d przyjmuje obiekt typu Graphics2D służący do rysowania elementów w grze
     */
    void draw(Graphics2D g2d, int xPosition, int yPosition);
}
