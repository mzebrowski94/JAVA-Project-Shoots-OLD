package game.gameobjects.player;


import game.gameobjects.player.components.PlayerConfiguration;
import game.interfaces.Drawable;
import game.units.PSConst;
import lombok.Builder;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Klasa imprelementująca interfejs Drawable, obiekt obrazujący bazę gracza.
 * Miejsce z ktorego wylatywać będą dyski wypuszczane przez gracza.
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
@Builder
public class PlayerBase implements Drawable {

    int radius1 = 15;
    int radius2 = 25;
    int bigRotation = 0;
    int smallRotation = 0;

    /**
     * Konstruktor klasy PlayerBase
     *
     * @param x     polożenie X, tworzonej bazy
     * @param y     polożenie Y, tworzonej bazy
     * @param color kolor gracza dla ktorego tworzona jest baza
     */
    public PlayerBase(int x, int y, Color color) {
        this.color = color;
        this.positionX = x;
        this.positionY = y;

    }

    public PlayerBase(PlayerConfiguration configuration, Color color) {
    }

    @Override
    public void draw(Graphics2D shape) {

        Ellipse2D.Double bigCircle = new Ellipse2D.Double(positionX - radius2, positionY - radius2, 2 * radius2, 2 * radius2);
        Ellipse2D.Double smallCircle = new Ellipse2D.Double(positionX - radius1, positionY - radius1, 2 * radius1, 2 * radius1);
        float dash1[] = {6.9f};
        BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, dash1, 1.0f);
        shape.setStroke(dashed);
        shape.setColor(color);

        rotateAndDraw(shape, bigRotation, bigCircle);
        bigRotation = bigRotation + 1;
        rotateAndDraw(shape, -1 * smallRotation, smallCircle);
        smallRotation = smallRotation + 1;

    }

    @Override
    public void draw(Graphics2D g2d, int xPosition, int yPosition) {

    }

    /**
     * Metoda która sluzy do animacji obiektu PlayerBase, a dokładnie jego obracania
     *
     * @param shape    przyjmuje obiekt typu Graphics2D służący do rysowania elementów w grze
     * @param rotation przyjmuje kat obrotu o jaki ma byc wykonany podczas animacji
     * @param circle   przyjmuje rozmiar i polozenie koła które będzie rysowane podczas animacji
     */
    public void rotateAndDraw(Graphics2D shape, int rotation, Ellipse2D.Double circle) {
        AffineTransform old = shape.getTransform();
        shape.rotate(Math.toRadians(rotation), positionX, positionY);     //draw shape/image (will be rotated)
        shape.draw(circle);
        shape.setTransform(old);  //things you draw after here will not be rotated
        //rotation=rotation+10;
    }
}
