package game.gui.service.impl;

import game.gui.service.MapRenderService;
//import game.units.ColisionPoint;
//import game.units.Player;

/**
 * @author Mateusz Żebrowski
 */
public class MapRenderServiceImpl implements MapRenderService {



    @Override
    public void update() {

    }

    /**
     * Metoda zmieniająca stan obiektu typu PointField w zależoności czy udało
     * się przejąć dany punkt w grze
     *
     * @param colisionPoint przyjmuje wartości dotyczące punktu biorącego udział
     *                      w kolizji
     * @param player        pobiera informacje o graczu
     * @param colisionTimes zawiera informację o tym ile razy obiekt typu Disc
     *                      uległ kolizji zanim zetknął się z obiektem PointField
     * @return zwraca wartość boolean informującą czy udało się przejąć dany punkt
     */
//    public boolean updatePointField(ColisionPoint colisionPoint, Player player, int colisionTimes) {
//        return gameSettings.getActualRound().getPlayerPoints().checkPointFiledErned(colisionPoint, player, colisionTimes);
//    }
}
