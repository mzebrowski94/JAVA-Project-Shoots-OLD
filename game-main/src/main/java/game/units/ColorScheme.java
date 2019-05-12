
package game.units;

import java.awt.Color;

/**
 * Klasa będąca paletą kolorów gry, zawierająca schematy kolorów w niej
 * wykorzystywane
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class ColorScheme {

    Color backgroudColor = new Color(95, 99, 104);
    Color standardColor = new Color(25, 25, 25);
    Color winBlockColor = new Color(68, 74, 80);

    Color deadLineColor = new Color(102, 0, 102);
    Color deadLineBackgroundColor = new Color(102, 75, 102);
    Color backgroundFontColor = new Color(192, 192, 192);
    Color backgroundPointBarColor = new Color(68, 74, 80);

    Color player1Color = new Color(124, 252, 0);
    Color player2Color = new Color(48, 213, 200);
    Color player3Color = new Color(252, 3, 0);
    Color player4Color = new Color(237, 26, 116);

    /////MENU
    Color menuStandardColor = new Color(35, 35, 35, 10);

    public Color getBackgroudColor() {
        return backgroudColor;
    }

    public void setBackgroudColor(Color backgroudColor) {
        this.backgroudColor = backgroudColor;
    }

    public Color getStandardColor() {
        return standardColor;
    }

    public void setStandardColor(Color standardColor) {
        this.standardColor = standardColor;
    }

    public Color getDeadLineColor() {
        return deadLineColor;
    }

    public void setDeadLineColor(Color deadLineColor) {
        this.deadLineColor = deadLineColor;
    }

    public Color getDeadLineBackgroundColor() {
        return deadLineBackgroundColor;
    }

    public void setDeadLineBackgroundColor(Color deadLineBackgroundColor) {
        this.deadLineBackgroundColor = deadLineBackgroundColor;
    }

    public Color getBackgroundFontColor() {
        return backgroundFontColor;
    }

    public void setBackgroundFontColor(Color backgroundFontColor) {
        this.backgroundFontColor = backgroundFontColor;
    }

    public Color getPlayer1Color() {
        return player1Color;
    }

    public void setPlayer1Color(Color player1Color) {
        this.player1Color = player1Color;
    }

    public Color getPlayer2Color() {
        return player2Color;
    }

    public void setPlayer2Color(Color player2Color) {
        this.player2Color = player2Color;
    }

    public Color getPlayer3Color() {
        return player3Color;
    }

    public void setPlayer3Color(Color player3Color) {
        this.player3Color = player3Color;
    }

    public Color getPlayer4Color() {
        return player4Color;
    }

    public void setPlayer4Color(Color player4Color) {
        this.player4Color = player4Color;
    }

    public Color getBackgroundPointBarColor() {
        return backgroundPointBarColor;
    }

    public void setBackgroundPointBarColor(Color backgroundPointBarColor) {
        this.backgroundPointBarColor = backgroundPointBarColor;
    }

    public Color getMenuStandardColor() {
        return menuStandardColor;
    }

    public void setMenuStandardColor(Color menuStandardColor) {
        this.menuStandardColor = menuStandardColor;
    }

    public Color getWinBlockColor() {
        return winBlockColor;
    }

    public void setWinBlockColor(Color winBlockColor) {
        this.winBlockColor = winBlockColor;
    }

}
