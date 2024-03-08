package pongfx.pong;


import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Player extends Rectangle{

    static final double pW = 20;
    static final double pH = 100;
    static final double deltaY = 15;
    TranslateTransition translate = new TranslateTransition();

    public Player() {
        this.setX(0);
        this.setY(0);
        this.setWidth(pW);
        this.setHeight(pH);
        this.setFill(Color.WHITE);
    }

    public Player(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(pW);
        this.setHeight(pH);
        this.setFill(Color.WHITE);
    }


    public void moveUp() {
        this.setTranslateY(this.getTranslateY() - deltaY);

        /*translate.setNode(this);
        translate.setDuration(Duration.millis(200));
        translate.setByY(-deltaY);
        translate.play();*/
    }

    public void moveDown() {
        /*translate.setNode(this);
        translate.setDuration(Duration.millis(200));
        translate.setByY(deltaY);
        translate.play();*/

        this.setTranslateY(this.getTranslateY() + deltaY);

    }

    public void moveY(double dy) {
        double nextPos = this.getTranslateY() + dy;

        if (nextPos <= GameScene.HEIGHT - getY() - Player.pH && nextPos >= 0 - getY()) {
            this.setTranslateY(nextPos);
        } else {
            //System.out.println(nextPos);
        }


    }




}
