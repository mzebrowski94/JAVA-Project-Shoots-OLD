package game.units;

import lombok.Builder;
import lombok.Getter;

/**
 * Klasa zawierająca informację na temat odbytej bądź odbywanej aktualnie rundy.
 * Zawiera informacje takie jak, numer rundy, ilośc zdobytych podczas niej
 * punktów
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */

@Getter
@Builder
public class Round {

    private int number;

    private int elapsedTime;
    private int maxEndTime;
    private int delayTime;


    private boolean ended;
    private boolean delayed;
    private int maxDelayTime;

    /**
     * Metoda odmierzająca czas trwania rundy
     */
    public void roundTick() {
        elapsedTime++;
        if ((delayTime == 0 && elapsedTime >= maxEndTime)
                || (delayTime!=0 &&  delayTime > maxDelayTime)) {
            ended = true;
        }
    }

    //TODO - CREATE BUILDIER

    public void delayRoundBySec(int seconds) {
        delayTime += seconds;
    }
}
