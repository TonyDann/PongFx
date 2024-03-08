package pongfx.pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectBall extends Rectangle {

    private final double radius = 10;

    public RectBall() {
        super();
        setWidth(radius * 2);
        setHeight(getWidth());
        setFill(Color.WHITE);
    }

    public RectBall(double x, double y) {
        setX(x - radius);
        setY(y - radius);
        setWidth(radius * 2);
        setHeight(getWidth());
        setFill(Color.WHITE);
    }


    boolean collideX = false, collideY = false;
    public void checkCollision(Player p) {;}

    public double getRadius() {
        return radius;
    }
}
