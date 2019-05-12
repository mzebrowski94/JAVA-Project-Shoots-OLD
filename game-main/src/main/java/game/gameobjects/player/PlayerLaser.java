
package game.gameobjects.player;

import game.consts.MoveDirection;
import game.drawables.Drawable;
import game.gameobjects.player.components.PlayerConfiguration;
import game.units.Player;
import game.units.ColisionPoint;
import mzebrowski.projectshoots.GameSettingsImpl;
import game.units.PSConst;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Klasa imprelementująca interfejs Drawable, obiekt obrazujący laser gracza.
 * Laser wskazuje tor po którym będą przemieszczać się dyski wystrzeliwane w
 * danym kierunku.
 *
 * @author Mateusz Żebrowski, Nr albumu: 95281
 */
public class PlayerLaser implements Drawable {

    PSConst unit = PSConst.UNIT;
    int size = (int) (unit.getValue() * 0.5);
    GameSettingsImpl gS;
    double rotation = 0;
    Color color;
    int secondPosX;
    int secondPosY;
    double moveSpeed = 0.5;
    double cursorSpeed = 1;
    boolean moving = false;
    int angle;

    BasicStroke dashed;
    float dash1[] = {6f};
    float dashRotation = 0;

    Point2D.Double point1 = new Point2D.Double();
    Point2D.Double point2 = new Point2D.Double();
    Point2D.Double point3 = new Point2D.Double();
    Point2D.Double point4 = new Point2D.Double();
    Point2D.Double point5 = new Point2D.Double();

    int npoints = 4;
    int xpoints[] = new int[npoints];
    int ypoints[] = new int[npoints];
    int xpointsDirections[] = new int[npoints];
    int ypointsDirections[] = new int[npoints];

    private int directionX = 1;
    private int directionY = 1;
    private double positionX;
    private double positionY;

    Player player;

    ColisionPoint colisionPoint;

    /**
     * Konstruktor klasy PlayerLaser
     *
     * @param posX   polożenie początkowe X, tworzonego laseru
     * @param posY   polożenie początkowe Y, tworzonego laseru
     * @param player dane gracza dla ktorego tworzony jest laser
     */
    public PlayerLaser(int posX, int posY, Player player) {
        this.color = player.getColor();
        gS = player.getGameSettings();
        this.player = player;
        this.positionX = posX;
        this.positionY = posY;
        this.angle = player.getCursorRotation() + player.getShootDirection();
        colisionPoint = new ColisionPoint(0, 0);
        colisionPoint.setMoveSpeed(2);
        //START POINT OF THE LASER
        xpoints[0] = (int) positionX;
        ypoints[0] = (int) positionY;
        xpointsDirections[0] = 1;
        ypointsDirections[0] = 1;

        calculateLaser();
    }

    public PlayerLaser(PlayerConfiguration configuration, Color color) {
    }

    /**
     * Resetuje pozycję położenia laseru do jego początkowej wartości
     */
    public void resetLaserPosition() {
        for (int i = 0; i < npoints; i++) {
            xpoints[i] = (int) positionX;
            ypoints[i] = (int) positionY;
            xpointsDirections[i] = 1;
            ypointsDirections[i] = 1;
        }
        xpoints[0] = (int) positionX;
        ypoints[0] = (int) positionY;
        this.angle = player.getCursorRotation() + player.getShootDirection();
    }

    @Override
    public void draw(Graphics2D shape) {
        shape.setColor(color);
        AffineTransform old = shape.getTransform();
        dashRotation += 0.25f;
        dashed = new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 5.0f, dash1, dashRotation);
        shape.setStroke(dashed);
        shape.drawPolyline(xpoints, ypoints, npoints);

        //shape.rotate(Math.toRadians(rotation), positionX, positionY);     //draw shape/image (will be rotated)  //things you draw after here will not be rotated
        shape.setTransform(old);
    }

    /**
     * Metoda sprawdzająca czy laser przesuną się, a co za tym idzie, czy
     * powinna zostać obliczona jego nowa pozycja
     */
    public void moveLaser() {
        if (moving) {
            calculateLaser();
        }
    }

    /**
     * Metoda służąca do obliczania toru na którym leży laser
     */
    public void calculateLaser() {
        //int loops = 0;
        angle = player.getCursorRotation() + player.getShootDirection();

        for (int i = 1; i < npoints; i++) {

            //Skad zaczyna punkt lasera
            colisionPoint.setPosX(xpoints[i - 1]);
            colisionPoint.setPosY(ypoints[i - 1]);
            //Jaki ma kierunek
            colisionPoint.setDirectionX(xpointsDirections[i - 1]);
            colisionPoint.setDirectionY(ypointsDirections[i - 1]);
            //Zaczyna bez kolizji
            colisionPoint.setColision(false);

            //kalkulujemy do poki nie bedzie kolizji
            while (!colisionPoint.isColision()) {
                colisionPoint = gS.getColisionCalculator().checkColision(colisionPoint);
                colisionPoint.updatePosition(angle);
                //loops++;
            }

            //System.out.println("LASER CALCULATED: " + loops + " loops");
            xpoints[i] = (int) colisionPoint.getPosX();
            ypoints[i] = (int) colisionPoint.getPosY();
            xpointsDirections[i] = colisionPoint.getDirectionX(); //calculateddirection
            ypointsDirections[i] = colisionPoint.getDirectionY();

        }
    }

    /**
     * Metoda przesuwajaca laser o odpowiedni kat i w odpowiedniem kierunku,
     * zawierajaca limity katów o jaki może się dany laser obrócić
     *
     * @param type     określa kierunek przesunięcia kursora: - 1 oznacza w LEWO - 2
     *                 oznacza w PRAWO
     * @param moveUnit argument przyjmujacy wartość o jaką przesunąć ma się
     *                 kursor
     * @return zwraca kat obrotu obiektu
     */
    public double setRotation(MoveDirection moveDirection) {

        if (rotation < -110) {
            rotation = -110;
        } else if (rotation > 110) {
            rotation = 110;
        }

        if (moveDirection == MoveDirection.LEFT) // MOVE LEFT
        {
            rotation += (moveUnit * moveSpeed * cursorSpeed);
        } else if (moveDirection == MoveDirection.RIGHT) // MOVE RIGHT
        {
            rotation -= (moveUnit * moveSpeed * cursorSpeed);
        }

        moving = true;

        return rotation;
    }

    public int getNpoints() {
        return npoints;
    }

    public void setNpoints(int npoints) {
        this.npoints = npoints;
    }

    public int[] getXpointsDirections() {
        return xpointsDirections;
    }

    public void setXpointsDirections(int i, int direction) {
        this.ypointsDirections[i] = direction;
    }

    public int[] getYpointsDirections() {
        return ypointsDirections;
    }

    public void setYpointsDirections(int i, int direction) {
        this.ypointsDirections[i] = direction;
    }

    public int[] getXpoints() {
        return xpoints;
    }

    public void setXpoints(int[] xpoints) {
        this.xpoints = xpoints;
    }

    public int[] getYpoints() {
        return ypoints;
    }

    public void setYpoints(int[] ypoints) {
        this.ypoints = ypoints;
    }

    public boolean isMoving(boolean moving) {
        return this.moving = moving;
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

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

}
