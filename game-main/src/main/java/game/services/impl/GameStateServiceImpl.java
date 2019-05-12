package game.services.impl;

import game.events.impl.RoundPhaseUpdateEvent;
import game.services.PlayersScoresService;
//import game.settings.MutualGameSettings;
import game.units.Round;
import game.services.GameStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.game.settings.RoundSettings;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class GameStateServiceImpl implements GameStateService {

    @Autowired
    PlayersScoresService playersScoresService;

    @Autowired
    private RoundSettings roundSettings;

    private List<Round> roundList;

    private int calculateActualRoundNumber() {
        return getActualRound() != null ? getActualRound().getNumber() : 1;
    }

    @Override
    public Round getActualRound() {
        return roundList.get((calculateActualRoundNumber()));
    }

    @Override
    public Round getPreviousRound() {
        return roundList.get(roundList.size() - 1);
    }

    @Override
    public boolean roundTimeEnded() {
        return getActualRound().getElapsedTime() > roundSettings.getMaxRoundTime();
    }

    @Override
    public boolean checkGameEnd() {
        return false;
    }

    @Override
    public void handleRoundPhaseUpdate(RoundPhaseUpdateEvent roundPhaseUpdateEvent) {

    }

    //TODO UNCOMMENT
//    @Override
//    public void restartOnNewGame(MutualGameSettings mutualGameSettings) {
//        playersScoresService.restartOnNewGame(mutualGameSettings);
//
//        roundList.clear();
//    }
//
//    @Override
//    public void prepareOnNewRound(MutualGameSettings mutualGameSettings) {
//        playersScoresService.prepareOnNewRound(mutualGameSettings);
//
//        int nextRoundNumber = calculateActualRoundNumber() + 1;
//        Round newRound = new Round(nextRoundNumber, mutualGameSettings.getRoundTime());
//
//        if (roundList != null && !roundList.isEmpty()) {
//            roundList.add(newRound);
//        } else {
//            roundList = new ArrayList<>();
//            roundList.add(newRound);
//        }
//    }
//
//    /**
//     * Metoda sprawdza czy nastąpił koniec gry oraz którzy gracze wygrali daną partię
//     *
//     * @return zmienną boolowską oznaczającą czy nastąpił koniec gry
//     */
//    public boolean checkGameEnd() {
//
//        if (actualRoundNumber >= roundLimit) {
//            int roundsWon = 0;
//            int pointsErned = 0;
//
//            roundsWon = players.get(0).getRoundsWon();
//
//            gameEnd = true;
//            for (int i = 0; i < players.size(); i++) {
//                if (players.get(i).getRoundsWon() > roundsWon)
//                    roundsWon = players.get(i).getRoundsWon();
//            }
//
//            for (int i = 0; i < players.size(); i++) {
//                if (players.get(i).getRoundsWon() == roundsWon) {
//                    if (players.get(i).getAllPointsErned() > pointsErned)
//                        pointsErned = players.get(i).getAllPointsErned();
//                }
//            }
//
//            for (int i = 0; i < players.size(); i++) {
//                if (players.get(i).getRoundsWon() == roundsWon && players.get(i).getAllPointsErned() == pointsErned)
//                    players.get(i).setWinner(true);
//            }
//
//            return true;
//
//        } else {
//            return false;
//        }
//
//    }

}
