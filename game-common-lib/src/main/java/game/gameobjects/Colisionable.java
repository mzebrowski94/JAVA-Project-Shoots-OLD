package game.gameobjects;

/**
 * @author Mateusz Żebrowski
 */
public abstract class Colisionable {

    int moveAngle = 0;

    int colisionRadius = 1;

    double posX;
    double posY;
    int indexX;
    int indexY;
    int directionX;
    int directionY;
    boolean colision;
    int size = 36;
    int colisionType;
    double moveSpeed;

    abstract void setMoveAngle();

    abstract void setPosition(int x, int y);

    /**
     * Metoda służaca do obliczania przesunięcia danego obiektu
     * @param angle przyjmuje kąt pod którym przezmieszcza się obiekt
     */
    public void updatePosition(int angle) {
        posX += directionX * moveSpeed * Math.sin(Math.toRadians(-angle));
        posY += directionY * moveSpeed * Math.cos(Math.toRadians(-angle));
    }

}
