package game.drawables.menu;

import game.consts.MenuOption;
import game.interfaces.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author Mateusz Żebrowski
 */
@Getter
@Setter
public class MenuOptionPanel implements Drawable {

    protected String label;
    protected int width;
    protected int height;
    protected boolean active;
    protected boolean disabled;

    protected Color activeColor;
    private Color disabledColor;
    protected MenuOption option;

    public MenuOptionPanel(MenuOption menuOption, String label, Color disabledColor, Color activeColor){
        this.option = menuOption;
        this.label = label;
        this.activeColor = activeColor;
        this.disabledColor = disabledColor;
    }

    @Override
    public void draw(Graphics2D g2d) {
        draw(g2d, 0,0);
    }

    @Override
    public void draw(Graphics2D g2d, int xPosition, int yPosition) {
        Color baseColor = g2d.getColor();
        if(active){
            g2d.setColor(activeColor);
        } else if(disabled){
            g2d.setColor(disabledColor);
        }
        g2d.drawString(label, xPosition, yPosition);
        g2d.setColor(baseColor);
    }
}
