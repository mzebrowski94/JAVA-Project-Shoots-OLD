
package game.gameobjects.map;


import game.drawables.Drawable;
import game.units.PSConst;

import java.awt.*;

/**
 * Klasa imprelementująca interfejs Drawable
 * Podstawowy obiekt występujący na
 * mapie gry tzw. ściana od której odbijają się obiekty typu Disc
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class Block implements Drawable {

    // Rysowanie prostokąta.
    PSConst unit = PSConst.UNIT;
    int size = unit.getValue();
    int positionX = 0;
    int positionY = 0;

    /**
     * Konstruktor obiektu typu Block
     *
     * @param positionX pozycja X obiektu na mapie
     * @param positionY pozycja Y obiektu na mapie
     */
    public Block(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public void draw(Graphics2D shape) {
        shape.setColor(new Color(25, 25, 25));

        BasicStroke normal = new BasicStroke();
        shape.setStroke(normal);
        shape.fillRect(positionX, positionY, size, size);
        //Rectangle rect = new Rectangle(positionX, positionY, size, size);
        //shape.draw(rect);
    }
}
