package game.gameobjects.player;

import game.consts.MoveDirection;
import game.gameobjects.disc.Disc;
import game.gameobjects.player.components.PlayerConfiguration;
import game.gameobjects.player.components.PlayerDiscs;
import game.interfaces.GameInput;
import game.settings.PlayerControls;
import lombok.Builder;
import lombok.Getter;

import java.awt.*;

/**
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */

//       if (number == 1) {
//               indexPosX = 23;
//               indexPosY = 12;
//               color = new Color(124, 252, 0);
//               keyLeft = KeyEvent.VK_LEFT;
//               keyRight = KeyEvent.VK_RIGHT;
//               keyShoot = KeyEvent.VK_UP;
//               moveUnit = -1;
//               shootDirection = 180;
//               } else if (number == 2) {
//               indexPosX = 2;
//               indexPosY = 12;
//               color = new Color(48, 213, 200);
//               keyLeft = KeyEvent.VK_A;
//               keyRight = KeyEvent.VK_D;
//               keyShoot = KeyEvent.VK_W;
//               moveUnit = 1;
//               shootDirection = 0;
//               } else if (number == 3) {
//               indexPosX = 12;
//               indexPosY = 2;
//               color = new Color(252, 3, 0);
//               keyLeft = KeyEvent.VK_NUMPAD4;
//               keyRight = KeyEvent.VK_NUMPAD6;
//               keyShoot = KeyEvent.VK_NUMPAD8;
//               moveUnit = -1;
//               shootDirection = -90;
//               } else if (number == 4) {
//               indexPosX = 12;
//               indexPosY = 23;
//               color = new Color(237, 26, 116);
//               keyLeft = KeyEvent.VK_L;
//               keyRight = KeyEvent.VK_J;
//               keyShoot = KeyEvent.VK_I;
//               moveUnit = 1;
//               shootDirection = 90;
//               }

@Getter
@Builder
public class Player {

    private int number;
    private Color color;
    private String name;

    private PlayerControls controls;
    private PlayerConfiguration configuration;

    private PlayerDiscs discs;
    private PlayerBase base;
    private PlayerCursor cursor;
    private PlayerLaser laser;


    public void updateWithInput(GameInput gameInput){
        if (gameInput.keyDownOnce(controls.getShootKey()) && discs.isLimitReached()) {
            discs.addDisc();
        } else if (gameInput.keyDown(controls.getLeftKey())) {
            cursor.setRotation(MoveDirection.LEFT);
            laser.setRotation(MoveDirection.LEFT);
        } else if (gameInput.keyDown(controls.getRightKey())) {
            cursor.setRotation(MoveDirection.RIGHT);
            laser.setRotation(MoveDirection.RIGHT);
        } else {
            laser.isMoving(false);
        }
    }


    /**
     * Metoda dodająca punkty do zmiennej points
     *
     * @param i argument pobierajacy wartość punktów do dodania
     */
    public void addPoints(int i) {
        points += i;
    }

    /**
     * Metoda umieszczająca obiekty PlayerCuror oraz PlayerLaser w domyślnej
     * pozycji na ekranie gry
     */
    public void resetPlayerCursor() {
        cursorRotation = 0;
        laser.resetLaserPosition();
        cursor.resetCursorPosition();

    }

    /**
     * Metoda doadająca ilośc wygranaych przez gracza rund
     */
    public void increaseRoundsWon() {
        this.roundsWon += 1;
    }

    /**
     * Metoda sumująca punkty gracza z aktualnej i przprzednich rund
     *
     * @param i argument pobierajacy wartość punktów do dodania
     */
    public void increaseAllPointsErned(int i) {
        this.allPointsErned += i;
    }
}
