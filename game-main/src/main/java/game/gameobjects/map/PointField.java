
package game.gameobjects.map;

import game.drawables.Drawable;
import game.units.Player;
import game.units.PSConst;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Klasa imprelementująca interfejs Drawable, obiekt obrazujący pole punktowe
 * które ma być przez graczy przejowane za pomocą dysków, by zdobyć punkty.
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class PointField implements Drawable {

    /**
     * Klasa wewnętrzna zawierająca informacje polu punktowym
     */
    public class GamePoint {

        boolean erned = false;
        int ernedPoints = 1;
        int playerNumber = -1;

        /**
         * Zwraca informację o tym, czy podany punkt jest zdobyty przez któregoś
         * z graczy
         *
         * @return Zwraca informację o tym, czy podany punkt jest zdobyty przez
         * któregoś z graczy
         */
        public boolean isErned() {
            return erned;
        }

        /**
         * Setter ustawiający zdobycie pola punktowego
         * 
         * @param erned przyjmuje wartość boolean czy udało się przejąć punkt
         */
        public void setErned(boolean erned) {
            this.erned = erned;
        }

        public int getErnedPoints() {
            return ernedPoints;
        }

        public void setErnedPoints(int ernedPoints) {
            this.ernedPoints = ernedPoints;
        }

        public int getPlayerNumber() {
            return playerNumber;
        }

        public void setPlayerNumber(int playerNumber) {
            this.playerNumber = playerNumber;
        }
    }

    GamePoint point = new GamePoint();
    int inX;
    int inY;
    // Rysowanie prostokąta.
    PSConst unit = PSConst.UNIT;
    int size = unit.getValue();
    int positionX = 0;
    int positionY = 0;
    Color color = Color.black;

    int radius = 7;
    int smallRadius = 4;
    int center;

    Line2D line;
    Line2D line2;
    RoundRectangle2D.Double rect;
    BasicStroke dashed;
    float dash1[] = {6f};
    float rotation = 0;
    /**
     * Kontruktor obiektu PointField
     *
     * @param inX index zawierajcący koordynat X w tablicy obiektów
     * @param inY index zawierajcący koordynat Y w tablicy obiektów
     */
    public PointField(int inX, int inY) {
        this.inX = inX;
        this.inY = inY;
        positionX = inX * size;
        positionY = inY * size;
        center = (int) 0.5 * size;
        line = new Line2D.Double();
        line2 = new Line2D.Double();
        rect = new RoundRectangle2D.Double(positionX + 2, positionY + 2, size - 4, size - 4, 10, 10);
        line.setLine(positionX + 4, positionY + 4, positionX + size - 4, positionY + size - 4);
        line2.setLine(positionX + 4, positionY + size - 4, positionX + size - 4, positionY + 4);
    }

    @Override
    public void draw(Graphics2D shape) {
        shape.setColor(Color.black);
        rotation += 0.12f;
        dashed = new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 5.0f, dash1, rotation);
        shape.setStroke(dashed);
        if (point.erned) {
            drawErned(shape);
        } else {
            drawUnerned(shape);
        }
    }

    /**
     * Metoda służąca do rysowanai pola punktowego gdy nie jest zdobyte przez
     * żadnego z graczy
     *
     * @param shape przyjmuje obiekt typu Graphics2D służący do rysowania
     * elementów w grze
     */
    public void drawUnerned(Graphics2D shape) {
        shape.draw(rect);
        shape.draw(line2);
        shape.draw(line);
    }

    /**
     * Metoda służąca do rysowania pola punktowego gdy jest zdobyte przez
     * któregoś z graczy
     *
     * @param shape przyjmuje obiekt typu Graphics2D służący do rysowania
     * elementów w grze
     */
    public void drawErned(Graphics2D shape) {

        shape.setColor(color.brighter());
        shape.draw(line2);
        shape.draw(line);
        shape.setColor(color);
        if (point.ernedPoints == 1) {
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 55, 70);
            shape.setColor(color.darker().darker());
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 145, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 235, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 325, 70);
        } else if (point.ernedPoints == 2) {
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 55, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 145, 70);
            shape.setColor(color.darker().darker());
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 235, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 325, 70);
        } else if (point.ernedPoints == 3) {
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 55, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 145, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 235, 70);
            shape.setColor(color.darker().darker());
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 325, 70);
        } else if (point.ernedPoints == 4) {
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 55, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 145, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 235, 70);
            shape.fillArc(positionX + 4, positionY + 4, size - 8, size - 8, 325, 70);
        }

        shape.setColor(Color.gray);
        shape.fillOval(positionX + 18 - radius, positionY + 18 - radius, 2 * radius, 2 * radius);
        shape.setColor(color.darker().darker());
        shape.drawOval(positionX + 2, positionY + 2, size - 4, size - 4);
        shape.drawOval(positionX + 18 - smallRadius, positionY + 18 - smallRadius, 2 * smallRadius, 2 * smallRadius);
    }

    /**
     * Metoda sprawdzająca czy podczas kolicji dysku z polem punktowym będzie
     * ono przejęte przez tego gracza
     *
     * @param player argument przyjmujący infromacje o graczu
     * @param colisionTimes argument przyjmujący informajce o ilości odbić dysku
     * który trafił w to pole
     * @return zwraca wartość boolean:
     *  - true - zdobyto punkt
     *  - false - nie zdobyto punktu
     */
    public boolean chckIfPointFieldErned(Player player, int colisionTimes) {
        boolean result = false;

        if (point.ernedPoints < colisionTimes) {
            result = true;
            point.erned = true;
            point.playerNumber = player.getNumber();
            point.ernedPoints = colisionTimes;
            this.color = player.getColor();

            if (point.ernedPoints > 4) {
                point.ernedPoints = 4;
            }

        } else if (point.ernedPoints == colisionTimes) {
            if (point.playerNumber != player.getNumber()) {
                result = true;
                point.erned = true;
                point.ernedPoints = colisionTimes;
                point.playerNumber = player.getNumber();
                this.color = player.getColor();
            } else {
                result = false;
            }
        }

        return result;
    }

    public int getInX() {
        return inX;
    }

    public void setInX(int inX) {
        this.inX = inX;
    }

    public int getInY() {
        return inY;
    }

    public void setInY(int inY) {
        this.inY = inY;
    }

    public GamePoint getPoint() {
        return point;
    }

    public void setPoint(GamePoint point) {
        this.point = point;
    }

}
