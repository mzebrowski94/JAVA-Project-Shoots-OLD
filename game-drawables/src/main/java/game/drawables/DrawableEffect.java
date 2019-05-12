
package game.drawables;

import java.awt.Graphics2D;

/**
 * Interfejs który implementuje część obiektów które rysowane są na głównym ekranie gry GameScreen
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public interface DrawableEffect {

/**
     * Metoda odpowiedzialna za wywoływanie resyowania obiektów dzieciczących interface DrawableEffect
     * @param shape przyjmuje obiekt typu Graphics2D
     */
    public void draw(Graphics2D shape);

    /**
     * Metoda sprawdajaca czy dany efekt jeszcze zachodzi, jeżeli nie, nastopią procedury usuwające dany efekt
     * @return zwraca wartość boolean:
     *  - true jeśli efekt dalej trwa
     *  - false jeśli efekt zakończył się
     */
    public boolean isProceeding();
}
