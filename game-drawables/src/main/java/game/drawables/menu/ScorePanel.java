package game.drawables.menu;

import game.interfaces.Drawable;
import game.dtos.PlayerDto;
import lombok.Builder;
import lombok.NonNull;

import java.awt.*;
import java.util.List;

/**
 * @author Mateusz Żebrowski
 */
@Builder
public class ScorePanel implements Drawable {

    @NonNull
    private int height;
    @NonNull
    private int width;
    @NonNull
    private String roundsLocale;
    @NonNull
    private String pointsLocale;
    @NonNull
    private String winnerLocal;

    private List<PlayerDto> players;

    private int winTextBrightnessFactor;

    public void update(List<PlayerDto> players) {
        this.players = players;
    }

    /**
     * Metoda odpowiedzialna za rysowanie wyników końcowych gry
     *
     * @param g2d parametr pobierający obiekt Graphic2D który rysuje odpowiednie
     *            elementy na ekranie gry menu
     */
    @Override
    public void draw(Graphics2D g2d, int xPosition, int yPosition) {
        drawLabels(g2d, xPosition, yPosition);
        drawScores(g2d, xPosition, yPosition);
    }

    private void drawScores(Graphics2D g2d, int xPosition, int yPosition) {
        Color baseColor = g2d.getColor();
        Color winTextColor = calculateWinTextColor();
        int playerLabelSize = width / 6;

        int playerNumberPosition = yPosition;
        int playerScorePosition = playerNumberPosition + g2d.getFontMetrics().getHeight();
        int playerRoundsPosition = playerScorePosition + g2d.getFontMetrics().getHeight();
        int winnerLabelPosition = playerRoundsPosition + g2d.getFontMetrics().getHeight();

        for (int i = 0; i < players.size(); i++) {
            int textXPosition = xPosition + playerLabelSize + (playerLabelSize * (i));

            if (players.get(i).isWinner()) {
                g2d.setColor(winTextColor);
                g2d.drawString(winnerLocal,textXPosition, winnerLabelPosition);
            } else {
                g2d.setColor(baseColor);
            }

            g2d.drawString(String.format("|%s|",players.get(i).getPlayerNumber()), textXPosition, playerNumberPosition);
            g2d.drawString(String.format("|%s|",players.get(i).getRoundsWon()), textXPosition, playerRoundsPosition);
            g2d.drawString(String.format("|%s|",players.get(i).getPointsEarned()), textXPosition, playerScorePosition);
        }
    }

    private void drawLabels(Graphics2D g2d, int xPosition, int yPosition) {
        int firstLabelHeight = yPosition + g2d.getFontMetrics().getHeight();
        int secondLabelHeight = firstLabelHeight + g2d.getFontMetrics().getHeight();
        g2d.drawString(String.format("%s: ", roundsLocale), xPosition, firstLabelHeight);
        g2d.drawString(String.format("%s: ", pointsLocale), xPosition, secondLabelHeight);
    }

    private Color calculateWinTextColor() {
        int brightness;

        winTextBrightnessFactor++;
        if (winTextBrightnessFactor < 50) {
            brightness = 50 + winTextBrightnessFactor;
        } else {
            brightness = 100 - winTextBrightnessFactor;
            if (winTextBrightnessFactor > 100) {
                winTextBrightnessFactor = 0;
            }
        }
        return new Color(brightness / 2, 2 * brightness, brightness / 2);
    }
}
