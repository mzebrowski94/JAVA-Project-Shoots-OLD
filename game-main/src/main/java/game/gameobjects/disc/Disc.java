
package game.gameobjects.disc;

import game.gameobjects.Colisionable;
import game.units.ColisionPoint;

import java.awt.*;

/**
 * Klasa imprelementująca interfejs Drawable, obiekt obrazujący dysk który jest
 * wypuszczany przez gracza, i który służy do zdobywania punktów podczas trwania
 * gry.
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class Disc extends Colisionable {

    //COLISION
    int colisionTimes = 0;

    ColisionPoint discPoint = new ColisionPoint(0, 0);

    /**
     * Konstruktor obiektu typu Disc
     *
     * @param positionX pozycja X obiektu na mapie
     * @param positionY pozycja Y obiektu na mapie
     * @param player    argument przyjmujący ustawienia gracza który utworzył
     *                  obiekt typu Disc
     */
    public Disc(int positionX, int positionY, int moveAngle) {
        discPoint.setColision(false);
        discPoint.setDirectionX(1);
        discPoint.setDirectionY(1);
        discPoint.setPosX((int) positionX);
        discPoint.setPosY((int) positionY);
        discPoint.setMoveSpeed(2);
    }

    /**
     * Metoda obliczająca przesunięcie obiektu
     */
    public void moveDisc() {
        discPoint.updatePosition(moveAngle);
    }

    /**
     * Metoda sprawdzająca ile razy obiekt typu Disc kolidował z innymi
     * obiektami. Jeśli liczba zderzeń przekroczy odpowiednią wartość jest to
     * sygnał do usunięcia dysku
     *
     * @return zwraca wartość typu boolean określającą czy dysk powienien zostać
     * usunięty
     */
    public boolean reachedMaxColisions() {
        if (colisionTimes > 7) {
            return true;
        } else {
            return false;
        }
    }

    public int getColisionTimes() {
        return colisionTimes;
    }

    public void setColisionTimes(int colisionTimes) {
        this.colisionTimes = colisionTimes;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
