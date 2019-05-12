package game.settings;

import game.consts.MenuOption;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Mateusz Żebrowski
 */

@Getter
@AllArgsConstructor
public abstract class ChangeableMenuSetting<T extends Comparable<? super T>> {

    protected T value;
    protected T incrementation;

    private T minValue;
    private T maxValue;
    private MenuOption menuOption;

    protected abstract T increment();

    protected abstract T decrement();

    public T increase(){
        T incremented = increment();
        int result = incremented.compareTo(maxValue);

        if(result < 0 ){
            value = incremented;
        } else {
            value = minValue;
        }

        return value;
    }

    public T decrease(){
        T decremented = decrement();
        int result = decremented.compareTo(minValue);

        if(result > 0 ){
            value = decremented;
        } else {
            value = maxValue;
        }

        return value;
    }
}
