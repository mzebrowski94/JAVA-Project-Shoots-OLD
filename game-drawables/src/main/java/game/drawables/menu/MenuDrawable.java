package game.drawables.menu;

import game.consts.MenuOption;
import game.consts.RoundPhase;
import game.interfaces.Drawable;
import game.settings.ChangeableMenuSetting;
import game.settings.MutualGameSettings;
import lombok.Builder;
import lombok.NonNull;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Klasa odpowiadając za interfejs menu gry, zmienę ustawień menu, obsługę
 * klawiatury w menu oraz wyświetlenie wyników końcowych
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
@Builder
public class MenuDrawable implements Drawable {

    @NonNull
    private int width;
    @NonNull
    private int height;
    @NonNull
    private List<MenuOptionPanel> optionPanels;
    @NonNull
    private ScorePanel scorePanel;
    @NonNull
    private Font menuFont;

    private RoundPhase roundPhase;

    @Override
    public void draw(Graphics2D g2d, int xPosition, int yPosition) {
        draw(g2d);
    }

    /**
     * Metoda rysująca na ekranie interfejs menu gry
     *
     * @param g2d parametr pobierający obiekt Graphic2D który rysuje elementy
     *            menu
     */
    public void draw(Graphics2D g2d) {
        g2d.setFont(menuFont);

        if (roundPhase != RoundPhase.ENDED) {
            drawOptionPanels(g2d);
        } else {
            drawGameEnd(g2d);
        }
    }

    private void drawGameEnd(Graphics2D g2d) {
        scorePanel.draw(g2d, height / 5, width / 5);
    }

    public void update(MenuOption actuallyPointedOption, MutualGameSettings menuSettings, RoundPhase actualRoundPhase) {
        this.roundPhase = actualRoundPhase;
        updateGameOptions(menuSettings, actuallyPointedOption);
    }

    private void updateGameOptions(MutualGameSettings menuSettings, MenuOption actuallyPointedOption) {
        boolean gameContinues = roundPhase == RoundPhase.NOT_STARTED;
        for (MenuOptionPanel optionPanel : optionPanels) {
            if(optionPanel instanceof ChangeableMenuOptionPanel){
                optionPanel.setLabel(menuSettings.get(optionPanel.getOption()).toString());
            }
            optionPanel.setActive(optionPanel.getOption() == actuallyPointedOption);
            optionPanel.setDisabled(gameContinues && optionPanel.getOption() == MenuOption.CONTINUE);
        }
    }

    private void drawOptionPanels(Graphics2D g2d) {
        //TODO FONT SIZE - WYKORZYSTAC g2d.getFontMetrics()
        int textPosition = (this.width / 2) - 50;
        int firstLabelHeight = (height / 4);
        int nextLineHeight = (height / 2) / optionPanels.size();
        int drawHeight = firstLabelHeight;

        for (MenuOptionPanel optionPanel : optionPanels) {
            optionPanel.draw(g2d, textPosition, drawHeight);
            drawHeight += nextLineHeight;
        }
    }
}
