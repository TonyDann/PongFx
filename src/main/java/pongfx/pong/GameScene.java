package pongfx.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.security.PKCS12Attribute;


public class GameScene{
    static final double WIDTH = 1200;
    static final double HEIGHT = 800;

    Group root = new Group();
    Scene scene = new Scene(root, Color.BLACK);
    Player p1 = new Player(0, HEIGHT/2 - Player.pH * .5);
    Player p2 = new Player(getWidth() - Player.pW, HEIGHT/2 - Player.pH * .5);

    Label p1PointsText = new Label("P1 Points :");
    Label p2PointsText = new Label("P2 Points : ");

    Ball ball = new Ball();
    RectBall ball2 = new RectBall(WIDTH/2, HEIGHT/2);
    final int collideTop = 0, collideBottom = 1, collideP1 = 2, collideP2 = 3, p1Score = 4, p2Score = 5;

    double dx = 8;
    double dy = -5;
    /*double dx = Math.random() * 5 - 3;
    double dy = Math.random() * 5 - 3;*/
    int p1Points = 0, p2Points = 0;

    public Scene getScene() {
        ball.setCenterX(ball2.getX());
        ball.setCenterY(ball2.getY());
        ball.setRadius(ball2.getRadius());
        ball2.setFill(null);

        p1.setFill(Color.BLUE);
        p2.setFill(Color.RED);

        Line midLine = new Line(WIDTH/2, 0, WIDTH/2, HEIGHT);
        midLine.setStroke(Color.PURPLE);
        midLine.setFill(null);
        midLine.setStrokeWidth(3);
        Circle midCircle = new Circle(midLine.getEndX(), midLine.getEndY() / 2, 50);
        midCircle.setStroke(midLine.getStroke());
        midCircle.setFill(midLine.getFill());
        midCircle.setStrokeWidth(midLine.getStrokeWidth());

        p1PointsText.setTextFill(p1.getFill());
        p1PointsText.setFont(new Font(50));
        p1PointsText.setLayoutX(midLine.getEndX() - p1PointsText.getFont().getSize() * p1PointsText.getText().length()); p1PointsText.setLayoutY(50);

        p2PointsText.setTextFill(p2.getFill());
        p2PointsText.setFont(p1PointsText.getFont());
        p2PointsText.setLayoutX(midLine.getEndX() + 2 * p1PointsText.getLayoutX()); p2PointsText.setLayoutY(50);



        root.getChildren().addAll(p1, p2, ball2, ball, midLine, midCircle, p1PointsText, p2PointsText);
        move();
        return this.scene;
    }
    boolean bTop, bBottom, bP1, bP2;
    private void move() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ball2.setTranslateX(ball2.getTranslateX() + dx);
                ball2.setTranslateY(ball2.getTranslateY() + dy);

                ball.setTranslateX(ball2.getTranslateX());
                ball.setTranslateY(ball2.getTranslateY());

                int ballCollided = collideBall();
                switch (ballCollided) {
                    case collideTop: {
                        bTop = true;
                        break;
                    }
                    case collideBottom: {
                        bBottom = true;
                        break;
                    }
                    case collideP1: {
                        bP1 = true;
                        break;
                    }
                    case collideP2: {
                        bP2 = true;
                        break;
                    }
                    case p1Score: {
                        ball2.setTranslateX(0);
                        ball2.setTranslateY(0);
                        dx = -dx;
                        dy = Math.random() * 10 - 5;
                        p1Points++;
                        p1PointsText.setText("P1 Points: " + Integer.toString(p1Points));
                        ball.setFill(Color.WHITE);
                        break;
                    }
                    case p2Score: {
                        ball2.setTranslateX(0);
                        ball2.setTranslateY(0);
                        dx = -dx;
                        dy = Math.random() * 10 - 5;
                        p2Points++;
                        p2PointsText.setText("P2 Points: " + Integer.toString(p2Points));
                        ball.setFill(Color.WHITE);
                        break;
                    }
                    default: {
                        bTop = false;
                        bBottom = false;
                        bP1 = false;
                        bP2 = false;

                    }
                }

                if (bTop) {
                    dy = -dy;
                    //dx = -dx;
                }
                if (bBottom) {
                    dy = -dy;
                    //dx = -dx;
                }
                if (bP1) {
                    //dy = -dy;
                    dy = dy + Math.random() * 10 - 5;
                    dx = -dx;

                    ball.setFill(p1.getFill());
                }
                if (bP2) {
                    //dy = -dy;
                    dy = dy + Math.random() * 10 - 5;
                    dx = -dx;

                    ball.setFill(p2.getFill());
                }

                /*ball2.checkCollision(getPlayer1());
                ball2.checkCollision(getPlayer1());*/
            }
        };
        timer.start();

    }

    private int collideBall() {
        //0 - top, 1 - bottom, 2 - player 1, 3 - player 2
        double ballY = ball2.getTranslateY() + ball2.getY(), ballX = ball2.getTranslateX() + ball2.getX();
        double p1Y = p1.getTranslateY() + p1.getY();
        double p2Y = p2.getTranslateY() + p2.getY();
        if (ballY <= 0) {
            return collideTop;
        } else if (ballY + ball2.getRadius() * 2 >= HEIGHT) {
            return collideBottom;
        } else if (ballX <= Player.pW && ballY >= p1Y && ballY <= p1Y + Player.pH) {
            return collideP1;
        } else if (ballX + ball2.getRadius() * 2 >= WIDTH - Player.pW && ballY >= p2Y && ballY <= p2Y + Player.pH) {
            return collideP2;
        } else if (ballX <= 0) {
            return p2Score;
        } else if (ballX >= WIDTH - ball2.getRadius() * 2) {
            return p1Score;
        }
        return -1;
    }

    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() {
        return HEIGHT;
    }

    public Player getPlayer1() {
        return this.p1;
    }

    public Player getPlayer2() {
        return this.p2;
    }

    public Ball getBall() {return this.ball;}

}
