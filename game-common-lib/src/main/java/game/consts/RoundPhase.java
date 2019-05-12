package game.consts;

/**
 * Enum oznaczający poszczególne stany rundy
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public enum RoundPhase {
        /**
         * Runda rozpoczyna się
         */
        BEGINS,

        /**
         * Runda trwa
         */
        CONTINUES,

        /**
         * Runda kończy się
         */
        ENDS,

        /**
         * Runda została zapauzowana
         */
        PAUSED,

        /**
         * Runda została odpauzowana
         */
        UNPAUSED,

        NOT_STARTED, ENDED;
}
