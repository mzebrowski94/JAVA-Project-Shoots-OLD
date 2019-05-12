
package game.units;

/**
 * Klasa przechowująca podstawowe informację potrzebne do obliczania kolicji,
 * przemieszczenia obiektów posiadających taką właśność
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class ColisionPoint {



    /**
     * Konstruktor obiektu typu ColisionPoint
     * @param posX własność X obiektu na mapie
     * @param posY własność Y obiektu na mapie
     */
    public ColisionPoint(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        directionX = 1;
        directionY = 1;
        colision = false;
        indexX = 0;
        indexY = 0;
        colisionType = -1;
        moveSpeed = 3;
    }




    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getIndexX() {
        return indexX;
    }

    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public int getIndexY() {
        return indexY;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    public int getColisionType() {
        return colisionType;
    }

    public void setColisionType(int colisionType) {
        this.colisionType = colisionType;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

}
