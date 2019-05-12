package game.gameobjects.player.components;

import game.gameobjects.disc.Disc;
import game.units.ColisionPoint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mateusz Żebrowski
 */
public class PlayerDiscs {

    discs = new ArrayList<Disc>();
    ReachedFields = new LinkedList<ColisionPoint>();
    int discsLimit;
    private List<ColisionPoint> reachedFields;
    ArrayList<Disc> discs;

    public boolean getAmount() {
    }

    public boolean isLimitReached() {
        return false;
    }

    /**
     * Metoda odpowiedzialna za dodanie nowego obiektu Dics na ekran gry
     *
     * @return zwraca wskaźnik do nowo powstałego obiektu typu Disc
     */
    public Disc addDisc() {
        Disc newDisc = new Disc(positionX, positionY, getCursorRotation() + getShootDirection());
        discs.add(newDisc);
        discsLimit++;
        //System.out.println("++"+discsLimit);
        return newDisc;
    }


    /**
     * Metoda służąca od usuniecią obiektu typu Disc
     *
     * @param index pobiera index obiektu typu Disc który ma zostać usunięty
     */
    public void removeDisc(int index) {
        discs.remove(index);
        discsLimit--;
    }
}
