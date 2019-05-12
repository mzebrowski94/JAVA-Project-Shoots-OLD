
package game.gui;

import mzebrowski.projectshoots.GameSettingsImpl;
import java.game.consts.RoundPhase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 * Klasa abstrakcyjna zawierajaca podstawowe parametry i metody paneli występujących w grze
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
@Component
public abstract class GameCanvas extends Canvas {

    @Autowired
    GameSettingsImpl gameSettings;

    //GRAFA
    Graphics2D g2d;
    BufferStrategy strategy;
    Graphics graphics;
    Color standardColor;
    int width;
    int hight;

    //TIMING
    boolean animationEnd = false;
    boolean animationElementEnd = false;
    int animationTime = 0;
    int animatedElementElapsed = 0;
    int animatedElementLenght = 0;
    long tickTime = 0;
    double timeElapsed = 0;
    int roundTimeInSeconds = 0;

    //TEXT
    Font textFont;
    int fontSize = 80;
    int fontFreeSpace = 6;
    int textOffset = 3;
    int playerNamesTextSize = 30;

    GameCanvas() {
        animationTime = gameSettings.getAnimationTime();
        this.setBackground(gameSettings.getColorScheme().getBackgroudColor());
        this.addKeyListener(gameSettings.getKeyboard());
        standardColor = gameSettings.getColorScheme().getStandardColor();
        roundTimeInSeconds = gameSettings.getRoundTime();
        this.setFocusable(true);
    }

    /**
     * Metoda odpowiedzialna za inicjalizacje grafiki tzn. stworzenie odpowiednich obiektów BufferStrategy, Graphics oraz Graphics2D
     */
    public void initializeGraphics() {
        strategy = getBufferStrategy();
        if (strategy == null) {
            this.createBufferStrategy(3);
            strategy = getBufferStrategy();
        }
        graphics = strategy.getDrawGraphics();
        g2d = (Graphics2D) graphics;

        initializeLayout();
    }

    /**
     * Metoda odliczająca kolejne klatki animacji elementów menu występujących w grze
     */
    public void tick() {
        timeElapsed += 0.012;
        animatedElementElapsed = (int) (animatedElementLenght * (timeElapsed * 1f / animationTime * 1f));
        if (timeElapsed > animationTime) {
            animationEnd = true;
        }
    }

    /**
     *  Metoda restartująca animacje elementów menu w grze
     */
    public void restartAnimation() {
        timeElapsed = 0;
        animatedElementElapsed = 0;
        animationEnd = false;
        animationElementEnd = false;
    }
    
    /**
     * Metoda restartująca czas animacji elementów menu w grze
     */
    public void restartAnimationTime()
    {
        animationTime = gameSettings.getRoundTime();
    }
    
    /**
     * Metoda odpowiedzialna, za wykonanie instrukcji potrzebnych do narysowanie danego panelu
     * @param roundPhase zmienna pobierająca aktualny stan rundy
     */
    abstract public void drawUpdate(RoundPhase roundPhase);

    /**
     * Metoda odpowiedzialna, za wykonanie instrukcji w panelu podczas pauzowania gry
     */
    abstract public void drawRoundPaused();

    /**
     * Metoda odpowiedzialna, za wykonanie instrukcji w panelu podczas trwania rundy
     */
    abstract public void drawRoundContinues();

    /**
     * Metoda odpowiedzialna, za wykonanie instrukcji w panelu podczas ropoczynania rundy
     */
    abstract public void drawRoundBegining();

    /**
     * Metoda odpowiedzialna, za wykonanie instrukcji w panelu podczas kończenia rundy
     */
    abstract public void drawRoundEnding();

    /**
     * Metoda odpowiedzialna, za wykonanie instrukcji w panelu podczas inicjalizowania elementów panelu
     */
    abstract public void initializeLayout();

    public boolean isAnimationEnd() {
        return animationEnd;
    }

    public void setAnimationEnd(boolean animationEnd) {
        this.animationEnd = animationEnd;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public boolean isAnimationElementEnd() {
        return animationElementEnd;
    }

    public void setAnimationElementEnd(boolean animationElementEnd) {
        this.animationElementEnd = animationElementEnd;
    }

}
