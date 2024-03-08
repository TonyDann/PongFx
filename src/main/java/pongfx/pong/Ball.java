package pongfx.pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ball extends Circle {

    Random gen = new Random();
    final double r = Player.pH * .2;
    double dx = 0, dy = 0;

    public Ball() {
        this.setRadius(r);
        this.setCenterX(GameScene.WIDTH/2);
        this.setCenterY(Player.pH/2);
        this.setFill(Color.WHITE);
        //this.dx = gen.nextInt(5) + 1;
        this.dx = 3;
        this.dy = 3;
    }

    public Ball(double x, double y) {
        this.setRadius(r);
        this.setCenterX(x);
        this.setCenterY(y);
        this.setFill(Color.WHITE);
    }

    public int move() {
        this.setTranslateX(this.getTranslateX() + this.dx);
        this.setTranslateY(this.getTranslateY() + this.dy);
        return 0;
    }

    private boolean isScored() {
        //System.out.println(this.getTranslateX());
        return false;
    }

    private void bounce() {
        this.dx *= -1;
        this.dy *= -1;
        //System.out.println("========================================================================");
        System.out.println(dx);
        System.out.println(dy);
    }

    public boolean isCollision(Player p) {
        double x2 = this.getTranslateX() + this.getCenterX() + this.getRadius();
        double y2 = this.getTranslateY() + this.getCenterY() + this.getRadius();
        double x1 = p.getTranslateX() + p.getX();
        double y1 = p.getTranslateY() + p.getY();
        double distance = getDistance(x2, y2, x1, y1);
        System.out.printf("\n%f - %f -> %f - %f", x2, y2, x1, y1);

        if (this.getTranslateX() > 0 && x2 >= x1 && y2 >= y1 && y2 <= y1 + p.getHeight() && p.getX() > this.getCenterX()) {
            //System.out.println("========================================================================");
            this.bounce();
            return true;
        } else if (this.getTranslateX() < 0 && x2 <= x1 + p.getWidth() + this.getRadius() && y2 >= y1 && y2 <= y1 + p.getHeight() && p.getX() < this.getCenterX()) {
            //System.out.println("========================================================================");
            this.bounce();
            return true;
        } if ((x2 >= 0 || x2 <= GameScene.WIDTH) && (y2 <= 0 || y2 >= GameScene.HEIGHT)) {
            //System.out.println("========================================================================");
            this.setTranslateY(this.getTranslateY() + ((this.getRadius() + 1) * (-this.dy)) /  Math.abs(this.dy)  );
            dx = -3;
            dy = -3;
            return true;
        }

        return false;
    }

    private double getDistance(double x2, double y2, double x1, double y1) {
        double xM = Math.pow((x2 - x1), 2);
        double yM = Math.pow((y2 - y1), 2);

        return Math.sqrt((xM + yM));
    }


}
