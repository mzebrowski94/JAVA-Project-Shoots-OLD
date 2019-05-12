
package game.units;

import java.util.ArrayList;

/**
 * Klasa służąca do obsługi punktacji podczas poszczególnej rundy w grze
 * Odpowiada, za przechowywanie, dodawanie aktualizowanie punktów zdobytych
 * przez graczy
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class PlayerScore {

    int roundsWon;
    int allPointsErned;
    boolean winner;

    private ArrayList<Integer> points;

    public void updatePoints(int pointsNumber) {
        if (points == null) {
            points = new ArrayList<>();
        }
        points.set(points.size() - 1, points.get(points.size() - 1) + pointsNumber);
    }

    public void updateOnNewRound(int pointNumber) {
        if (points == null) {
            points = new ArrayList<>();
        }
        points.add(pointNumber);
    }

    public int getActualPoints() {
        return points != null && !points.isEmpty() ? points.get(points.size() - 1) : 0;
    }

    public int getPreviousRoundPoints() {
        return points != null && points.size() > 1 ? points.get(points.size() - 2) : 0;
    }
}
