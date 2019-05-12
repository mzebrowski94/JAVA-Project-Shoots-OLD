package game;

import game.interfaces.GameInput;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;

/**
 * Klasa dziedzcząca po interfejsie KeyListener obługująca kontakt użytkownika z
 * grą, polegający na odczytywaniu z klwiatury
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
@Component
public class KeyboardInput implements GameInput {

    private static final int KEY_COUNT = 256;

    private boolean[] currentKeys = null;
    private KeyState[] keys = null;

    /**
     * Konstruktor obiektu typu game.KeyboardInput
     */
    public KeyboardInput() {

        currentKeys = new boolean[KEY_COUNT];

        keys = new KeyState[KEY_COUNT];

        for (int i = 0; i < KEY_COUNT; ++i) {
            keys[i] = KeyState.RELEASED;
        }

    }

    private enum KeyState {
        RELEASED, // Not down
        PRESSED, // Down, but not the first time
        ONCE      // Down for the first time
    }

    /**
     * Metoda służąca do sprawdzania w tablicy klawiszy czy któryś z nich został
     * naciśnięty
     */
    @Override
    public synchronized void poll() {

        for (int i = 0; i < KEY_COUNT; ++i) {

            if (currentKeys[i]) // Set the key state 
            {
                // If the key is down now, but was not
                // down last frame, set it to ONCE,
                // otherwise, set it to PRESSED
                if (keys[i] == KeyState.RELEASED) {
                    keys[i] = KeyState.ONCE;
                } else {
                    keys[i] = KeyState.PRESSED;
                }

            } else {
                keys[i] = KeyState.RELEASED;
            }

        }

    }

    /**
     * Metoda odpowiadająca za odczytanie przytyrzymanego wciśnięcia klawisza z
     * klawiatury
     *
     * @param keyCode odbiera wartość odpowiadającą konkretnemu klawiszowi
     * @return zwraca wartość boolean - true klawisz wciśnięty - false klawisz
     * nie wciśnięty
     */
    @Override
    public boolean keyDown(int keyCode) {

        return keys[keyCode] == KeyState.ONCE
                || keys[keyCode] == KeyState.PRESSED;
    }

    /**
     * Metoda odpowiadająca za odczytanie pojedynczego wciśnięcia klawisza z
     * klawiatury
     *
     * @param keyCode odbiera wartość odpowiadającą konkretnemu klawiszowi
     * @return zwraca wartość boolean - true klawisz wciśnięty - false klawisz
     * nie wciśnięty
     */
    @Override
    public boolean keyDownOnce(int keyCode) {
        return keys[keyCode] == KeyState.ONCE;
    }

    /**
     * Metoda służaca do oznaczenia w tablice, że dany przycisk został
     * naciśnięty
     *
     * @param e event związany z naciśnięciem klawisza
     */
    @Override
    public synchronized void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode >= 0 && keyCode < KEY_COUNT) {

            currentKeys[keyCode] = true;

        }

    }

    /**
     * Metoda służaca do oznaczenia w tablice, że dany przycisk przestał być
     * naciśnięty
     *
     * @param e event związany z naciśnięciem klawisza
     */
    @Override
    public synchronized void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode >= 0 && keyCode < KEY_COUNT) {

            currentKeys[keyCode] = false;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }

}
