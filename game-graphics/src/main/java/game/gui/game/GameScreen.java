
package game.gui.game;

import java.game.consts.RoundPhase;
import game.gui.GameCanvas;
import game.logic.MapMatrix;
import game.gui.gameobject.Block;
import game.drawables.Drawable;
import game.drawables.DrawableEffect;
import game.gui.gameobject.PointField;
import game.gui.menu.GameMenu;
import mzebrowski.projectshoots.GameSettingsImpl;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

/**
 * Klasa rozrzeszająca klasę abstrakcyjną GameCanvas, jest to głowny ekran gry w
 * którym rysowana jest aktualnie obywająca się rozgrywka
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class GameScreen extends GameCanvas {

    MapMatrix matrixMap;
    private ArrayList<Drawable> drawList = null;
    private ArrayList<DrawableEffect> effectList;
    int playerIterator;
    GameMenu menuLayout;

    GameScreen(GameSettingsImpl gameSettings) {
        super(gameSettings);

        width = this.gameSettings.getDEFAULT_WIDTH();
        hight = this.gameSettings.getDEFAULT_HIGHT();
        menuLayout = new GameMenu(this.gameSettings);

        System.out.println("-GameScreen");
        setPreferredSize(new Dimension(width, hight));

        matrixMap = this.gameSettings.getMapMatrix();
        playerIterator = 0;
        drawList = new ArrayList<>();
        effectList = new ArrayList<>();

        animatedElementLenght = width / 2;
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
    }

    @Override
    public void drawUpdate(RoundPhase roundPhase) {

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        //RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        if (roundPhase == RoundPhase.PAUSED) {
            drawRoundPaused();
            menuLayout.drawMenu(g2d);

        } else if (roundPhase != RoundPhase.PAUSED) {
            drawRoundContinues();

            if (roundPhase == RoundPhase.ROUND_BEGIN) {
                drawRoundBegining();

            } else if (roundPhase == RoundPhase.ENDS) {
                drawRoundEnding();

            }
        }
        strategy.show();
    }

    /**
     * Metoda odpowiedzialna za dodanie obiektu do listy rysowanych obiektów
     *
     * @param drawable pobiera obiekt rozszerzający interfejs typu Drawable
     * @return zwraca listę rysowanych obiektów
     */
    public boolean addGameObject(Drawable drawable) {
        return drawList.add(drawable);
    }

    /**
     * etoda odpowiedzialna za usunięcie obiektu z listy rysowanych obiektów
     *
     * @param drawable pobiera obiekt rozszerzający interfejs typu Drawable
     * @return zwraca listę rysowanych obiektów
     */
    public boolean removeGameObject(Drawable drawable) {
        return drawList.remove(drawable);
    }

    /**
     * Metoda służaca do ponownego rozmieszczenia elemtnów gry na mapie gry
     */
    public void reInitializeMapPanel() {
        playerIterator = 0;
        drawList.clear();
        effectList.clear();
        initiailizeMapPanel();
    }

    /**
     * Metoda służaca do odczytania z tablicy położenia poszególnych elementów
     * gry oraz rozmieszczenia ich na mapie gry
     */
    private void initiailizeMapPanel() {
        for (int i = 0; i < matrixMap.getLength(); i++) {
            for (int j = 0; j < matrixMap.getLength(); j++) {
                if (matrixMap.mapMatrix[i][j] == 1) {
                    addBlock(i * 36, j * 36);
                } else if (matrixMap.mapMatrix[i][j] == 2) {
                    addPointField(i, j);
                } else if (matrixMap.mapMatrix[i][j] == 3) {
                    addPlayerBase();
                }
            }
        }

    }

    private void addPlayerBase() {
        drawList.add(gameSettings.getPlayer(playerIterator).getPlayerBase());
        drawList.add(gameSettings.getPlayer(playerIterator).getPlayerCursor());
        playerIterator++;
        if (playerIterator == 4) {
            playerIterator = 0;
        }
    }



    public GameSettingsImpl getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettingsImpl gS) {
        this.gameSettings = gS;
    }

    private void addBlock(int x, int y) {
        drawList.add(new Block(x, y));
    }

    private void addPointField(int x, int y) {
        gameSettings.getActualRound().getPlayerPoints().getPointFields().add(new PointField(x, y));
    }

    public ArrayList<Drawable> getDrawList() {
        return drawList;
    }

    public ArrayList<DrawableEffect> getEffectList() {
        return effectList;
    }

    @Override
    public void drawRoundPaused() {
        g2d.setColor(gameSettings.getColorScheme().getMenuStandardColor());
        g2d.fillRect(0, 0, width, hight);
        //g2d.setColor(gameSettings.getColorScheme().getBackgroundFontColor());
        //g2d.drawString("PAUSED", gameSettings.getDEFAULT_WIDTH()/2 + textOffset, gameSettings.getDEFAULT_HIGHT()/2);
        g2d.setColor(gameSettings.getColorScheme().getDeadLineColor());
        g2d.setFont(textFont);
    }

    @Override
    public void drawRoundContinues() {
        g2d.setColor(gameSettings.getColorScheme().getBackgroudColor());
        g2d.fillRect(0, 0, width, hight);
        //OtherDrawables
        if (drawList != null) {
            for (Drawable shape : drawList) {
                shape.draw(g2d);
            }
        }

        //Lasers
        if (gameSettings.getPlayerList() != null) {
            for (int i = 0; i < gameSettings.getPlayerList().size(); i++) {
                gameSettings.getPlayerList().get(i).getPlayerLaser().draw(g2d);
            }
        }

        //PointFields
        if (gameSettings.getActualRound().getPlayerPoints().getPointFields() != null) {
            for (Drawable shape : gameSettings.getActualRound().getPlayerPoints().getPointFields()) {
                shape.draw(g2d);
            }
        }

        //Discs
        if (gameSettings.getPlayerList() != null) {
            for (int i = 0; i < gameSettings.getPlayerList().size(); i++) {
                for (int j = 0; j < gameSettings.getPlayerList().get(i).getPlayerDiscs().size(); j++) {
                    gameSettings.getPlayerList().get(i).getPlayerDiscs().get(j).draw(g2d);
                }
            }
        }

        //Effects
        if (effectList != null) {
            for (int i = 0; i < effectList.size(); i++) {
                if (effectList.get(i) != null && effectList.get(i).isProceeding()) {
                    effectList.get(i).draw(g2d);
                } else {
                    effectList.remove(i);
                }
            }
        }
    }

    @Override
    public void drawRoundBegining() {
        g2d.setColor(gameSettings.getColorScheme().getStandardColor());
        g2d.fillRect(0, 0, animatedElementLenght - animatedElementElapsed, hight);
        g2d.fillRect(animatedElementLenght + animatedElementElapsed, 0, animatedElementLenght - animatedElementElapsed, hight);
        g2d.fillRect(0, 0, width, animatedElementLenght - animatedElementElapsed);
        g2d.fillRect(0, animatedElementLenght + animatedElementElapsed, width, animatedElementLenght - animatedElementElapsed);

        if (animatedElementElapsed > animatedElementLenght) {
            animationElementEnd = animationEnd;
        }
    }

    @Override
    public void drawRoundEnding() {
        g2d.setColor(gameSettings.getColorScheme().getStandardColor());
        //System.out.println(animatedElementElapsed);
        //System.out.println(animatedElementLenght);
        g2d.fillRect(0, 0, animatedElementElapsed, hight);
        g2d.fillRect(width - animatedElementElapsed, 0, animatedElementElapsed, hight);
        g2d.fillRect(0, 0, width, animatedElementElapsed);
        g2d.fillRect(0, width - animatedElementElapsed, hight, animatedElementElapsed);

        if (animatedElementElapsed > animatedElementLenght) {
            animationElementEnd = animationEnd;
        }
    }

    @Override
    public void initializeLayout() {

    }

    public GameMenu getMenuLayout() {
        return menuLayout;
    }

    public void setMenuLayout(GameMenu menuLayout) {
        this.menuLayout = menuLayout;
    }

}
