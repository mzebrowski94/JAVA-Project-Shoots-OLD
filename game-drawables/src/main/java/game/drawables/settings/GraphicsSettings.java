package game.drawables.settings;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Mateusz Żebrowski
 */
@Component
@Getter
@Log4j2
public class GraphicsSettings {

    @Value("unit.in.pixels")
    private int unit;

    @Value("matrix.size.in.units")
    private int matrixSizeInUnits;

    @Value("score.panel.width.size.in.units")
    private int scorePanelWidthSizeInUnits;

    @Value("counter.height.size.in.units")
    private int counterHeightSizeInUnits;

    private int width, height;

    private Font gameFont;

    private Font menuFont;

    @PostConstruct
    private void init() {
        initializeFont();
        width = (matrixSizeInUnits + scorePanelWidthSizeInUnits) * unit;
        height = (matrixSizeInUnits + counterHeightSizeInUnits) * unit;
    }

    /**
     * Metoda odpowiadająca za inicjalizację czcionek w grze
     */
    private void initializeFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/game.main/resources/fonts/13_Misa.TTF")).deriveFont(12f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/game.main/resources/fonts/13_Misa.TTF")));
            log.info("- Loaded Misa font by Zane Townsend");

            menuFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/game.main/resources/fonts/GeosansLight.ttf")).deriveFont(25f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/game.main/resources/fonts/GeosansLight.ttf")));
            log.info("- Code Demo font by Fontfabric");
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
