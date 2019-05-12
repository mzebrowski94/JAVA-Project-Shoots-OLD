
package game.units;

/**
 * Enum zawierający podstawowe wartości jednostek wykorzysytanych w grze:
 * - długość i szerokość tablicy
 * - długość i szczerkość jednego pola gry
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public enum PSConst {
    UNIT(36),
    TABLESIZE(25),
    WINDOWWIDTH(25);
    int value;
     
    public int getValue() {
        return value;
    }
    
    PSConst(int value)
    {
        this.value = value;
    }
}
