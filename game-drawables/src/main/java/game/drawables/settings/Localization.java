package game.drawables.settings;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */
@Component
@Getter
public class Localization {

    @Value("menu.option.continue")
    private String menuOptionContinue;

    @Value("menu.option.new.game")
    private String menuOptionNewGame;

    @Value("menu.option.number.of.players")
    private String menuOptionNumberOfPlayers;

    @Value("menu.option.round.limit")
    private String menuOptionRoundLimit;

    @Value("menu.option.round.time")
    private String menuOptionRoundTime;

    @Value("menu.option.quit")
    private String menuOptionQuit;

    @Value("score.winner")
    private String scoreWinner;

    @Value("score.rounds")
    private String scoreRounds;

    @Value("score.points")
    private String scorePoints;
}
