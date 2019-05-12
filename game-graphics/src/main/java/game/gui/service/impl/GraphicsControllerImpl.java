package game.gui.service.impl;

import java.game.consts.RoundPhase;
import game.gui.game.GameCounter;
import game.gui.game.GamePointer;
import game.gui.game.GameScreen;
import mzebrowski.projectshoots.GameSettingsImpl;
import game.gui.service.GraphicsController;
import game.gui.service.MapRenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Klasa rozszerzająca JFrame stanowiąca okienko gry
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */

@Component
public class GraphicsControllerImpl extends JFrame implements GraphicsController {

    @Autowired
    MapRenderService mapRenderService;



    GameScreen gameScreen;
    GameCounter gameCounter;
    GamePointer gamePointer;
    
    public GraphicsControllerImpl(GameSettingsImpl gameSettings)
    {
        setTitle("Projekt1");            // Tytuł
        //centerFrame();
        //JFRAME GAME MENU
        gameScreen = new GameScreen(gameSettings);
        gameCounter = new GameCounter(gameSettings);
        gamePointer = new GamePointer(gameSettings);
              
        add(gameScreen, BorderLayout.CENTER);
        add(gameCounter, BorderLayout.NORTH);
        add(gamePointer, BorderLayout.EAST);
        this.setFocusable(true);
        setVisible(true);
        gameScreen.createBufferStrategy(2);
        setLocationByPlatform(false);                                            // NULL ustawia na środek , TRUE daje wybór systemowy
        Image icon = new ImageIcon("src/images/game.png").getImage();
        setIconImage(icon);
        addKeyListener(gameSettings.getKeyboard());
        
        System.out.println("-MainFrame");
        setIgnoreRepaint( true );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        
        gameScreen.initializeGraphics();
        gameCounter.initializeGraphics();  
        gamePointer.initializeGraphics();
    }


    /**
     * Metoda odpowiedzialna za wywołanie metod rysujących wszystkich paneli występujących w grze
     *
     * @param roundPhase argument pobiera akutalny stan rundy
     */
    public void gameRenderUpdate(RoundPhase roundPhase) {
        graphicsEngine.gameCounter.drawUpdate(roundPhase);
        graphicsEngine.gamePointer.drawUpdate(roundPhase);
        graphicsEngine.gameScreen.drawUpdate(roundPhase);

    }

    @Override
    public void render() {

    }

    /**
     * Metoda sprawdzająca czy skończyły się animacjie poszczególnych elementów paneli w grze
     *
     * @return zwraca wartość boolean:
     * - true - animacje zakończyły się
     * - false - animacje nadal trwają
     */
    public boolean checkGameCanvasesAnimationEnd() {
        return graphicsEngine.gameScreen.isAnimationElementEnd()
                && graphicsEngine.gameCounter.isAnimationElementEnd();
        /* && gameFrame.gamePointer.isAnimationElementEnd(); */
    }

    /**
     * Metoda wywołująca funkcjie restartujące czas animacji elenetów paneli w grze
     */
    public void restartAnimationTimers() {
        graphicsEngine.gameCounter.restartAnimation();
        graphicsEngine.gamePointer.restartAnimation();
        graphicsEngine.gameScreen.restartAnimation();
    }

    @Override
    public void update() {
        gameCounter.tick();
    }

    @Override
    public void updateScore() {
        gamePointer.tick();
    }

    @Override
    public boolean isAnimationEnded() {
        return false;
    }
}
