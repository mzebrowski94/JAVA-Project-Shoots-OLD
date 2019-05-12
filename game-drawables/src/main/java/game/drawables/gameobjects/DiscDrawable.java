package game.drawables.gameobjects;

import game.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * @author Mateusz Żebrowski
 */
public class DiscDrawable implements Drawable {

    int size;
    Color color = Color.black;
    int bigRadius = 18;
    int smallRadius = 10;
    int halfSmallRadius = (int) (0.5 * smallRadius);
    int halfBigRadius = (int) (0.5 * bigRadius);
    Graphics2D shape2;


    @Override
    public void draw(Graphics2D g2d) {
        Area outsideCircle = new Area(new Ellipse2D.Double(discPoint.getPosX() - halfBigRadius, discPoint.getPosY() - halfBigRadius, bigRadius, bigRadius));
        Area insideCircle = new Area(new Ellipse2D.Double(discPoint.getPosX() - halfSmallRadius, discPoint.getPosY() - halfSmallRadius, smallRadius, smallRadius));
        g2d.setColor(color);
        outsideCircle.subtract(insideCircle);
        g2d.fill(outsideCircle);
    }

    @Override
    public void draw(Graphics2D g2d, int xPosition, int yPosition) {

    }
}
