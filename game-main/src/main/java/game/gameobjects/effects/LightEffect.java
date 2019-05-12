
package game.gameobjects.effects;

import game.drawables.DrawableEffect;
import game.units.ColisionPoint;
import game.units.PSConst;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Klasa imprelementująca interfejs DrawableEffect, obiekt obrazujacy efekt
 * podswietlenia pola które zostałó uderzone przez dysk gracza.
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class LightEffect implements DrawableEffect {

    PSConst unit = PSConst.UNIT;
    int size = unit.getValue();
    Color color = null;
    int colorMode;
    int posX;
    int posY;
    boolean status;
    int colorRed = 0;
    int colorGreen = 0;
    int colorBlue = 0;
    int colorWhite = 0;
    int divider = 230;

    /**
     * Konstruktor klasy LightEffect
     *
     * @param color argumnet przyjmjacy kolor gracza który wywołał ten efekt
     * @param index argument przyjmujacy dane o pozycji w ktorej powinien zostac
     * wywyolany efekt
     */
    public LightEffect(Color color, ColisionPoint index) {
        this.color = color;
        colorBlue = color.getBlue();
        colorRed = color.getRed();
        colorGreen = color.getGreen();
        posX = (int) index.getIndexX() * size;
        posY = (int) index.getIndexY() * size;
        colorMode = 1;
        status = true;
    }

    @Override
    public void draw(Graphics2D shape) {
        setEffect();
        shape.setColor(color);
        BasicStroke normal = new BasicStroke();
        shape.setStroke(normal);
        shape.fillRect(posX, posY, size, size);
        Rectangle rect = new Rectangle(posX, posY, size, size);
        shape.draw(rect);
    }

    @Override
    public boolean isProceeding() {
        return status;
    }

    
    /**
     * Metoda ktora uaktualnia efekt, zmieniajac jego kolor, ksztalt
     */
    public void setEffect() {

        if (colorMode == 1) {
            colorWhite += 10;
            if (colorWhite == 170) {
                colorMode = 2;
            } else {
                color = new Color(colorWhite, colorWhite, colorWhite);
            }

        } else if (colorMode == 2) {
            if (divider > 11) {
                //System.out.println(colorRed + " " + colorGreen + " " + colorBlue + " Divider: " + divider);
                color = color = new Color(colorRed, colorGreen, colorBlue, divider);
                divider -= 10;
            } else {
                colorMode = 3;
            }

        } else {
            status = false;
        }

    }

}


/*
 if (colorMode == 3) {
            if (color.getBlue() != 0) {
                colorBlue = color.getBlue() - 1;
            }
            if (color.getRed() != 0) {
                colorRed = color.getRed() - 1;
            }
            if (color.getGreen() != 0) {
                colorGreen = color.getGreen() - 1;
            }

            if (color.getBlue() != 0 && color.getRed() != 0 && color.getGreen() != 0) {
                color = color = new Color(colorBlue, colorRed, colorGreen);
            } else {
                colorMode = 0;
            }
        } 
 */
