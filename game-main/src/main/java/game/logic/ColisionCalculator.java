
package game.logic;


import mzebrowski.projectshoots.GameSettingsImpl;
import game.gameobjects.Colisionable;
import game.units.ColisionPoint;

/**
 * Obiekt odpowiadający za obliczanie kolizji w grze
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class ColisionCalculator {

    int restX;
    int restY;
    boolean colision;
    int inX;
    int inY;
    int size;
    int colisionType = 0;
    int ballColisionSize = 4;

    int[][] matrix;

    int directionX = 1;
    int directionY = 1;

    boolean rightSideColision;
    boolean leftSideColision;
    boolean upSideColision;
    boolean SideColision;

    /**
     * Konstruktor obiektu ColisionCalculator
     *
     * @param gameSettings argument pobierający ustawienia gry
     */
    public ColisionCalculator(GameSettingsImpl gameSettings) {
        this.matrix = gameSettings.getMapMatrix().mapMatrix;
        size = gameSettings.getUNIT();
    }

    /**
     * Metoda odpowiadająca za obliczenie kolizji obiektu z elementami
     * znajdujacymi się na mapie
     *
     * @param colisionPoint obiekt dla którego obliczona ma zostać kolizja
     * @return obiekt dla którego została obliczona kolizja
     */
    public ColisionPoint checkColision(Colisionable colisionable) {
        restX = (int) colisionable.getPosX() % size;
        restY = (int) colisionable.getPosY() % size;
        colision = false;
        colisionType = colisionable.getColisionType();
        inX = (int) colisionable.getPosX() / size;
        inY = (int) colisionable.getPosY() / size;

        directionX = colisionable.getDirectionX();
        directionY = colisionable.getDirectionY();

        rightSideColision = leftSideColision = upSideColision = SideColision = false;

        if (inX == 0 || inY == 0 || inX == 24 || inY == 24) {
            colisionable.setColision(true);
            return colisionable;
        }

        if (colisionType != 0) {
            if (matrix[inX][inY] == 2) {
                //System.out.println("Collison");
                colisionable.setIndexX(inX);
                colisionable.setIndexY(inY);
                colisionType = 0;
                colision = true;
//                colisionPoint.setColision(true);
                //colisionPoint
            }
        }
        if (!colision && colisionType != 1 && restX >= 0 && restX <= ballColisionSize) {
            rightSideColision = true;
            if (matrix[inX - 1][inY] == 1) {
                colisionable.setDirectionX(-directionX);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 1;
                colisionable.setIndexX(inX - 1);
                colisionable.setIndexY(inY);

                //System.out.println("PRAWA");
            }

        }
        if (!colision && colisionType != 2 && restX >= size - ballColisionSize && restX <= size) {
            leftSideColision = true;
            if (matrix[inX + 1][inY] == 1) {
                colisionable.setDirectionX(-directionX);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 2;
                colisionable.setIndexX(inX + 1);
                colisionable.setIndexY(inY);
                //System.out.println("LEWA");
            }
        }
        if (!colision && colisionType != 3 && restY >= 0 && restY <= ballColisionSize) {
            SideColision = true;
            if (matrix[inX][inY - 1] == 1) {
                colisionable.setDirectionY(-directionY);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 3;
                colisionable.setIndexX(inX);
                colisionable.setIndexY(inY - 1);
                // System.out.println("DOL");
            }
        }
        if (!colision && colisionType != 4 && restY >= size - ballColisionSize && restY <= size) {
            upSideColision = true;
            if (matrix[inX][inY + 1] == 1) {
                colisionable.setDirectionY(-directionY);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 4;
                colisionable.setIndexX(inX);
                colisionable.setIndexY(inY + 1);
                // System.out.println("GORA");
            }
        }

        ///////////////////////ROG UPDATE//////////////////////////////
        if (!colision && colisionType != 5 && rightSideColision && upSideColision) {
            //System.out.println("PRAWY GORNY ROG");
            if (matrix[inX - 1][inY + 1] == 1) {
                colisionable.setDirectionY(-directionY);
                colisionable.setDirectionX(-directionX);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 5;
                colisionable.setIndexX(inX - 1);
                colisionable.setIndexY(inY + 1);
            }
        } else if (!colision && colisionType != 6 && rightSideColision && SideColision) {
            //System.out.println("PRAWY DOLNY ROG");
            if (matrix[inX - 1][inY - 1] == 1) {
                colisionable.setDirectionY(-directionY);
                colisionable.setDirectionX(-directionX);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 6;
                colisionable.setIndexX(inX - 1);
                colisionable.setIndexY(inY - 1);
            }
        } else if (!colision && colisionType != 7 && leftSideColision && upSideColision) {
            //System.out.println("LEWY GORNY ROG");
            if (matrix[inX + 1][inY + 1] == 1) {
                colisionable.setDirectionY(-directionY);
                colisionable.setDirectionX(-directionX);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 7;
                colisionable.setIndexX(inX + 1);
                colisionable.setIndexY(inY + 1);
            }

        } else if (!colision && colisionType != 8 && leftSideColision && SideColision) {
            //System.out.println("LEWY DOLNY ROG");
            if (matrix[inX + 1][inY - 1] == 1) {
                colisionable.setDirectionY(-directionY);
                colisionable.setDirectionX(-directionX);
//                colisionPoint.setColision(true);
                colision = true;
                colisionType = 8;
                colisionable.setIndexX(inX + 1);
                colisionable.setIndexY(inY - 1);
            }
        }

        colisionable.setColisionType(colisionType);
        colisionable.setColision(colision);

        return colisionable;
    }

    /**
     * Metoda wywołująca sprawdzanie kolizji obecnego obiektu z elementami mapy
     * (obiektami typu Block lub PointField)
     *
     * @return Obiekt typu ColiisonPoint zawierający wartości dotyczące
     * przesuwania obiektu
     */
    public ColisionPoint checkCollision() {
        discPoint.setColision(false);
        discPoint = gS.getColisionCalculator().checkColision(discPoint);
        if (discPoint.isColision()) {
            if (discPoint.getColisionType() != 0) {
                colisionTimes++;
            }
        }
        return discPoint;
    }

}
