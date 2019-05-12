package game;

import org.springframework.context.annotation.ComponentScan;

/**
 * Główny obiekt gry przechowujący funkcję game.main w której wywoływany jest obiekt typu game.GameLoop czyli pętla gry
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */

@ComponentScan
public class MainApp {

    /**
     * Funkcja game.main rozpoczynająca działanie programu
     * @param args - parametr nie używany
     */
    public static void main(String[] args) {
        GameLoop gameLoop = new GameLoop();
        gameLoop.start();
    }

}



