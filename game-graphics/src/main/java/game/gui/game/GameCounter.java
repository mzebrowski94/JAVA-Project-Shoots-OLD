
package game.gui.game;

import java.game.consts.RoundPhase;
import game.gui.GameCanvas;
import mzebrowski.projectshoots.GameSettingsImpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 * Klasa rozrzeszająca klasę abstrakcyjną GameCanvas, górny panel gry,
 * wyświetlający pasek czasu gry
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class GameCounter extends GameCanvas {

    //LayoutSizes
    int longWidth;
    int borderSize = 6;
    int size = 16;
    int borderSpace = 8;
    int timeBarWidth;
    int timeBarHalfWidth;
    int timeBarHight;
    int timeBarLeftWidth;
    int timeBarUpperHight;

    Color deadLineColor;
    Color deadLineBackgroundColor;

    //Layout
    Rectangle counterBorder;
    Rectangle leftDeadLine;
    Rectangle rightDeadLine;
    Rectangle leftDeadLineBackground;
    Rectangle rightDeadLineBackground;

    RoundPhase actualRoundPhase;

    GameCounter(GameSettingsImpl gameSettings) {
        super(gameSettings);

        this.width = this.gameSettings.getDEFAULT_WIDTH();
        this.hight = this.gameSettings.getDEFAULT_COUNTER_HIGHT();
        this.longWidth = this.gameSettings.getDEFAULT_COUNTER_WIDTH();
        this.timeBarWidth = (int) (0.75 * width);
        this.timeBarHalfWidth = (int) (0.5 * timeBarWidth);
        this.timeBarHight = (int) (0.25 * hight);
        this.timeBarUpperHight = (int) (0.5 * hight - timeBarHight * 0.5);
        this.timeBarLeftWidth = (int) (0.5 * width - 0.5 * timeBarWidth);

        setPreferredSize(new Dimension(this.gameSettings.getDEFAULT_WIDTH(), this.gameSettings.getDEFAULT_COUNTER_HIGHT()));
        deadLineColor = this.gameSettings.getColorScheme().getDeadLineColor();
        deadLineBackgroundColor = this.gameSettings.getColorScheme().getDeadLineBackgroundColor();
        animatedElementLenght = timeBarHalfWidth;
        animationTime = this.gameSettings.getRoundTime();
    }

    @Override

    public void initializeGraphics() {
        strategy = getBufferStrategy();
        if (strategy == null) {
            this.createBufferStrategy(3);
            strategy = getBufferStrategy();
        }
        graphics = strategy.getDrawGraphics();
        g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        initializeLayout();
    }

    @Override
    public void drawUpdate(RoundPhase roundPhase) {
        this.actualRoundPhase = roundPhase;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (roundPhase == RoundPhase.PAUSED) {
            drawRoundPaused();
        } else {
            drawRoundContinues();
        }
        strategy.show();
    }

    /**
     * Metoda rysująca ramkę panelu GameCounter
     *
     * @param g2d parametr pobierajacy obiekt Graphics2D, który rysuje elementy
     * na ekranie
     * @param roundPhase parametr pobierający aktualny stan rundy
     */
    public void drawBorder(Graphics2D g2d, RoundPhase roundPhase) {
        g2d.setColor(standardColor);
        g2d.fillRect(0, 0, borderSize, hight);
        g2d.fillRect(longWidth - borderSize, 0, borderSize, hight);
        g2d.fillRect(width, 0, borderSize, hight);
        g2d.fillRect(0, 0, longWidth, borderSize);
        g2d.fillRect(0, hight - borderSize, longWidth, borderSize);

        drawCounterDeadLine(g2d, roundPhase);
        drawTitle(g2d);

    }

    @Override
    public void tick() {
        timeElapsed += 0.008;
        animatedElementElapsed = (int) (animatedElementLenght * (timeElapsed * 1f / animationTime * 1f));
        if (timeElapsed > animationTime) {
            animationEnd = true;
        }
    }

    /**
     * Metoda wyświetlająca tytuł gry w prawym górnym rogu ekranu
     *
     * @param gd2 parametr pobierajacy obiekt Graphics2D, który rysuje elementy
     * na ekranie
     */
    public void drawTitle(Graphics2D gd2) {
        g2d.setColor(deadLineBackgroundColor);
        g2d.drawString("Project", width + fontFreeSpace, 30 + textOffset);
        g2d.drawString(" Shooots!", width + fontFreeSpace, 55 + textOffset);
        g2d.setColor(standardColor);
        g2d.drawString("Project", width + fontFreeSpace, 30);
        g2d.drawString(" Shooots!", width + fontFreeSpace, 55);

    }

    @Override
    public void initializeLayout() {
        //CounterBorder
        counterBorder = new Rectangle(timeBarLeftWidth - borderSpace, timeBarUpperHight - borderSpace, timeBarWidth + borderSpace + borderSpace, timeBarHight + borderSpace + borderSpace);
        //DeadLine
        leftDeadLine = new Rectangle(timeBarLeftWidth, timeBarUpperHight, timeBarHalfWidth, timeBarHight);
        rightDeadLine = new Rectangle(timeBarLeftWidth + timeBarHalfWidth, timeBarUpperHight, timeBarHalfWidth, timeBarHight);
        leftDeadLineBackground = new Rectangle(timeBarLeftWidth, timeBarUpperHight, timeBarHalfWidth, timeBarHight);
        rightDeadLineBackground = new Rectangle(timeBarLeftWidth + timeBarHalfWidth, timeBarUpperHight, timeBarHalfWidth, timeBarHight);
        //Text
        g2d.setFont(gameSettings.getGameFont().deriveFont(fontSize));
        g2d.setFont(new Font(gameSettings.getGameFont().getFontName(), 100, 25));
    }

    /**
     * Metoda rysująca pasku czasu
     *
     * @param gd2 parametr pobierajacy obiekt Graphics2D, który rysuje elementy
     * na ekranie
     */
    private void drawCounterDeadLine(Graphics2D g2d, RoundPhase roundPhase) {
        //RIGHT SIDE OF BAR - deadlinebackground increasing
        g2d.setColor(standardColor);
        g2d.fill(counterBorder);

        if (!animationEnd) {
            g2d.setColor(deadLineBackgroundColor);
            g2d.fill(rightDeadLineBackground);
            g2d.setColor(deadLineColor);
            g2d.fillRect(timeBarLeftWidth + timeBarHalfWidth, timeBarUpperHight, timeBarHalfWidth - animatedElementElapsed, timeBarHight);
            //LEFT SIDE OF BAR - deadline decreasing
            g2d.setColor(deadLineColor);
            g2d.fill(leftDeadLine);
            g2d.setColor(deadLineBackgroundColor);
            g2d.fillRect(timeBarLeftWidth, timeBarUpperHight, animatedElementElapsed, timeBarHight);

        } else {
            g2d.setColor(deadLineBackgroundColor);
            g2d.fillRect(timeBarLeftWidth, timeBarUpperHight, timeBarWidth, timeBarHight);
        }
        g2d.setColor(standardColor);

        if (roundPhase == RoundPhase.ROUND_BEGIN) {
            drawRoundBegining();
        } else if (roundPhase == RoundPhase.ENDS) {
            drawRoundEnding();
        }
    }

    @Override
    public void drawRoundPaused() {
        g2d.setColor(gameSettings.getColorScheme().getMenuStandardColor());
        g2d.fillRect(0, 0, gameSettings.getDEFAULT_COUNTER_WIDTH(), gameSettings.getDEFAULT_COUNTER_HIGHT());
    }

    @Override
    public void drawRoundContinues() {
        g2d.setColor(gameSettings.getColorScheme().getBackgroudColor());
        g2d.fillRect(0, 0, gameSettings.getDEFAULT_COUNTER_WIDTH(), gameSettings.getDEFAULT_COUNTER_HIGHT());

        drawBorder(g2d, actualRoundPhase);
    }

    @Override
    public void drawRoundBegining() {
        animationElementEnd = true;
        //System.out.println("ROUND COUNTER BEGIN");
    }

    @Override
    public void drawRoundEnding() {
        animationElementEnd = true;
        //System.out.println("ROUND COUNTER END");
    }

    public int getRoundTimeInSeconds() {
        return roundTimeInSeconds;
    }

    public void setRoundTimeInSeconds(int roundTimeInSeconds) {
        this.roundTimeInSeconds = roundTimeInSeconds;
    }

}
