
package game.gameobjects.map;

import game.units.ColisionPoint;
import mzebrowski.projectshoots.GameSettingsImpl;

import java.util.Random;

/**
 * Klasa posiadająca tablicę w której zawarte są informacje o rozmieszczeniu
 * poszczególnych elementów na mapie gry. Odpowiedzialna jest także za
 * rozmieszczenie tych elementów podczas rozpoczyania gry
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class MapMatrix {

    public int[][] mapMatrix;
    Random random;
    GameSettingsImpl gS;

    /**
     * Konstruktor obiektu MapMatrix
     *
     * @param gameSettings argument pobierający ustawienia gry
     */
    public MapMatrix(GameSettingsImpl gameSettings) {
        this.gS = gameSettings;
        mapMatrix = new int[gS.getSIZE()][gS.getSIZE()];
        random = new Random();

        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix.length; j++) {
                mapMatrix[i][j] = 0;
            }
        }
    }

    /**
     * Metoda odpowiedzialna, za reinicjializacje tzn. rozmieszczenie elementów
     * na ekranie gry
     */
    public void reInitializeMap() {
        clearMap();
        initializeMap();
    }

    /**
     * Metoda która czyści odpowiedni fragment tablicy z niepotrzebnych
     * aktualnie elementów
     */
    public void clearMap() {
        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix.length; j++) {
                mapMatrix[i][j] = 0;
            }
        }
    }

    /**
     * Metoda inicjailizująca rozmieszczenie obiektów gry na mapie
     */
    public void initializeMap() {
        initializeWalls();
        initializeBlockCorners();
        initializeBlockStates();
        initializePointFieldStates();
        initializePlayerBases();
    }

    private void initializeWalls() {
        for (int i = 0; i < mapMatrix.length; i++) {
            mapMatrix[i][0] = 1;
            mapMatrix[0][i] = 1;
            mapMatrix[gS.getSIZE() - 1][i] = 1;
            mapMatrix[i][gS.getSIZE() - 1] = 1;
        }
    }

    private void initializePlayerBases() {
        if (gS.getPlayersNumber() >= 1) {

            cleanMatrixMap(9, 21, 6, 3);
            addSingleBlockState(9, 23);
            addSingleBlockState(14, 23);
            addPlayerBaseState(12, 23);
        }
        if (gS.getPlayersNumber() >= 2) {
            cleanMatrixMap(9, 1, 6, 3);
            addSingleBlockState(9, 1);
            addSingleBlockState(14, 1);
            addPlayerBaseState(12, 3);
        }
        if (gS.getPlayersNumber() >= 3) {
            cleanMatrixMap(9, 1, 6, 3);
            addSingleBlockState(9, 1);
            addSingleBlockState(14, 1);
            addPlayerBaseState(12, 3);
            cleanMatrixMap(1, 9, 3, 6);
            addSingleBlockState(1, 9);
            addSingleBlockState(1, 14);
            addPlayerBaseState(3, 12);
        }
        if (gS.getPlayersNumber() >= 4) {
//            cleanMatrixMap(9, 21, 6, 3);
//            addSingleBlockState(9, 23);
//            addSingleBlockState(14, 23);
//            addPlayerBaseState(12, 23);
            cleanMatrixMap(21, 9, 3, 6);
            addSingleBlockState(23, 9);
            addSingleBlockState(23, 14);
            addPlayerBaseState(23, 12);
        }

    }

    private void cleanMatrixMap(int x, int y, int hight, int width) {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                mapMatrix[x + i][y + j] = 0;
            }
        }
    }

    private void addPlayerBaseState(int x, int y) {
        mapMatrix[x][y] = 3;
    }

    private void addSingleBlockState(int x, int y) {
        mapMatrix[x][y] = 1;
    }

    private void initializeBlockCorners() {
        mapMatrix[1][1] = 1;
        mapMatrix[gS.getSIZE() - 2][1] = 1;
        mapMatrix[1][gS.getSIZE() - 2] = 1;
        mapMatrix[gS.getSIZE() - 2][gS.getSIZE() - 2] = 1;
    }

    private boolean initializeBlockStates() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                insertBlockState(i, j);
            }
        }
        return true;
    }

    private boolean initializePointFieldStates() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!((i == 0 && j == 1)
                        || (i == 0 && j == 2)
                        || (i == 1 && j == 0)
                        || (i == 1 && j == 3)
                        || (i == 2 && j == 0)
                        || (i == 2 && j == 3)
                        || (i == 3 && j == 1)
                        || (i == 3 && j == 2))) {
                    insertPointFieldState(i, j);
                }
            }
        }

        return true;
    }

    private ColisionPoint insertBlockState(int indexX, int indexY) {
        int x = 0;
        int y = 0;
        boolean isInserted = false;

        while (!isInserted) {
            x = indexX * 3;
            y = indexY * 3;
            x = random.nextInt(3) + x;
            y = random.nextInt(3) + y;

            if (!checkBlockFitting(x, y) && x != 0 && x != gS.getSIZE() && y != 0 && y != gS.getSIZE() - 1) {
                mapMatrix[x][y] = 1;
                isInserted = true;
            } else {
                isInserted = false;
            }
        }
        return new ColisionPoint(x, y);
    }

    private ColisionPoint insertPointFieldState(int indexX, int indexY) {
        int x = 0;
        int y = 0;
        boolean isInserted = false;

        while (!isInserted) {
            x = indexX * 6;
            y = indexY * 6;
            x = random.nextInt(6) + x;
            y = random.nextInt(6) + y;

            if (mapMatrix[x][y] == 0 && checkPointFitting(x, y)) {
                mapMatrix[x][y] = 2;
                isInserted = true;
            } else {
                isInserted = false;
            }
        }
        return new ColisionPoint(x, y);
    }

    private boolean checkBlockFitting(int x, int y) {
        boolean isFitting = false;

        if (x != 0 && mapMatrix[x - 1][y] == 1) {
            isFitting = true;
        } else if (x != gS.getSIZE() - 1 && mapMatrix[x + 1][y] == 1) {
            isFitting = true;
        } else if (y != 0 && mapMatrix[x][y - 1] == 1) {
            isFitting = true;
        } else if (y != gS.getSIZE() - 1 && mapMatrix[x][y + 1] == 1) {
            isFitting = true;
        }

        return isFitting;
    }

    private boolean checkPointFitting(int x, int y) {
        boolean isFitting = false;

        if (x != 0 && mapMatrix[x - 1][y] == 1 && (x - 1) != 0) {
            isFitting = true;
        } else if (x != gS.getSIZE() - 1 && mapMatrix[x + 1][y] == 1 && (x + 1) != (gS.getSIZE() - 1)) {
            isFitting = true;
        } else if (y != 0 && mapMatrix[x][y - 1] == 1 && (y - 1) != 0) {
            isFitting = true;
        } else if (y != gS.getSIZE() - 1 && mapMatrix[x][y + 1] == 1 && (y + 1) != (gS.getSIZE() - 1)) {
            isFitting = true;
        }

        return isFitting;
    }

    public int getLength() {
        return mapMatrix.length;
    }
}
