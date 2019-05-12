
package game.gameobjects.player;

import game.consts.MoveDirection;
import game.drawables.Drawable;
import game.gameobjects.player.components.PlayerConfiguration;
import game.units.Player;
import game.units.PSConst;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Klasa imprelementująca interfejs Drawable, obiekt obrazujący kursor gracza.
 * Kursor wskazuje na kierunek w którym będą wystrzeliwane obiekty typu Disc.
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class PlayerCursor implements Drawable {

    int shootDirection;
    double moveUnit;
    PSConst unit = PSConst.UNIT;
    int size = (int) (unit.getValue() * 0.5);
    int positionX = 0;
    int positionY = 0;
    Point2D.Double point1 = new Point2D.Double();
    Point2D.Double point2 = new Point2D.Double();
    Point2D.Double point3 = new Point2D.Double();
    Point2D.Double point4 = new Point2D.Double();
    int basePositionX = 0;
    int basePositionY = 0;
    Color color = Color.black;
    double rotation = 0;
    double moveSpeed = 0.5;
    double cursorSpeed = 1;
    Player player;
    private int cursorRotation;

    /**
     * Konstruktor klasy PlayerCursor
     *
     * @param posX   polożenie X, tworzonego kursora
     * @param posY   polożenie Y, tworzonego kursora
     * @param player dane gracza dla ktorego tworzony jest kursor
     */
    public PlayerCursor(int posX, int posY, Player player) {
        this.player = player;
        this.color = player.getColor();

        basePositionX = posX;
        basePositionY = posY;

        setCursorPosition(posX, posY);

    }

    public PlayerCursor(PlayerConfiguration configuration, Color color) {
    }

    @Override
    public void draw(Graphics2D shape) {
        shape.setColor(color);
        AffineTransform old = shape.getTransform();
        shape.rotate(Math.toRadians(rotation), basePositionX, basePositionY);     //draw shape/image (will be rotated)  //things you draw after here will not be rotated

        int xpoints[] = {(int) point1.getX(), (int) point2.getX(), (int) point3.getX(), (int) point4.getX()};
        int ypoints[] = {(int) point1.getY(), (int) point2.getY(), (int) point3.getY(), (int) point4.getY()};
        int npoints = 4;

        shape.fillPolygon(xpoints, ypoints, npoints);
        shape.setTransform(old);
    }

    /**
     * Metoda pobierajaca aktualna rotacje
     *
     * @return zwraca aktualną wartość rotacji
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Metoda przesuwajaca kursor o odpowiedni kat i w odpowiedniem kierunku,
     * zawierajaca limity katów o jaki może się dany kursor obrócić
     *
     * @param moveDirection określa kierunek przesunięcia kursora: - 1 oznacza w LEWO - 2
     *                      oznacza w PRAWO
     * @param moveUnit      argument przyjmujacy wartość o jaką przesunąć ma się
     *                      kursor
     * @return zwraca kat obrotu obiektu
     */
    public double setRotation(MoveDirection moveDirection) {
        if (rotation < -110) {
            rotation = -110;
        } else if (rotation > 110) {
            rotation = 110;
        }

        if (moveDirection == MoveDirection.LEFT) {
            rotation += (moveUnit * moveSpeed * cursorSpeed);
        } else if (moveDirection == MoveDirection.RIGHT) // MOVE RIGHT
        {
            rotation -= (moveUnit * moveSpeed * cursorSpeed);
        }

        return rotation;
    }

    /**
     * Metoda służąca do resetu położenia kursora do jego podstawowej pozycji
     */
    public void resetCursorPosition() {
        rotation = 0;
        setCursorPosition(basePositionX, basePositionY);
    }

    /**
     * Metoda ustawiająca kursor w danej pozycji podanej przez parametry
     *
     * @param posX polożenie X, kursora
     * @param posY polożenie Y, kursora
     */
    public void setCursorPosition(int posX, int posY) {
        int x = posX;
        int y = posY;

        if (player.getNumber() == 1) {
            y = y - (2 * size);
            point1.setLocation(x - size, y);
            point2.setLocation(x, y - size);
            point3.setLocation(x + size, y);
            point4.setLocation(x, y - (0.5 * size));
        } else if (player.getNumber() == 2) {
            y = y + (2 * size);
            point1.setLocation(x + size, y);
            point2.setLocation(x, y + size);
            point3.setLocation(x - size, y);
            point4.setLocation(x, y + (0.5 * size));
        } else if (player.getNumber() == 3) {
            x = x + (2 * size);
            point1.setLocation(x, y - size);
            point2.setLocation(x + size, y);
            point3.setLocation(x, y + size);
            point4.setLocation(x + (0.5 * size), y);

        } else if (player.getNumber() == 4) {
            x = x - (2 * size);
            point1.setLocation(x, y + size);
            point2.setLocation(x - size, y);
            point3.setLocation(x, y - size);
            point4.setLocation(x - (0.5 * size), y);
        }
    }
}
