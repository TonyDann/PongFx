package pongfx.pong;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.io.IOException;

public class Main extends Application {

    public final int WIDTH = 800;
    public final int HEIGHT = 600;

    GameScene gameScene = new GameScene();
    Scene mainScene = gameScene.getScene();

    Player p1 = gameScene.getPlayer1();
    Player p2 = gameScene.getPlayer2();

    Ball ball = gameScene.getBall();

    TranslateTransition translate = new TranslateTransition();



    boolean p1Up, p1Down, p2Up, p2Down;

    @Override
    public void start(Stage stage) throws IOException {

        GameTimer gameTimer = new GameTimer();
        MyTimer myTimer = new MyTimer();
        myTimer.start();

        //Movement
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {


                switch (keyEvent.getCode()) {
                    case W: p1Up = true; break;
                    case S: p1Down = true; break;
                    case UP: p2Up = true; break;
                    case DOWN: p2Down = true; break;
                }

                /*switch (keyEvent.getCode()) {
                    case W: {
                        p1.moveUp();
                        System.out.println("UP1");
                        break;
                    }
                    case S:
                        p1.moveDown();
                        break;
                    case UP:
                        p2.moveUp();
                        break;
                    case DOWN:
                        p2.moveDown();
                        break;
                }*/
            }
        });

        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W: p1Up = false; break;
                    case S: p1Down = false; break;
                    case UP: p2Up = false; break;
                    case DOWN: p2Down = false; break;
                }
            }
        });


        stage.setTitle("Ping Pong 2D");
        stage.setWidth(gameScene.getWidth() + Player.pW * .5);
        stage.setHeight(gameScene.getHeight() + Player.pH * .5);
        //stage.setResizable(false);

        stage.setScene(mainScene);
        stage.show();
        //System.out.println(stage.getHeight() + " " + gameScene.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
    public class MyTimer extends AnimationTimer {
        @Override
        public void handle(long a) {
            doHandle();
        }

        public void doHandle(){

//            ball.isCollision(p1);
//            ball.isCollision(p2);
//
//            if (ball != null) {
//                switch(ball.move()) {
//                    case 1: {
//                        //ball = null;
//                        break;
//                    }
//                }
//            }


            double dy1 = 0, dy2 = 0;
            if (p1Up) dy1 -= Player.deltaY;
            if (p1Down) dy1 += Player.deltaY;
            if (p2Up) dy2 -= Player.deltaY;
            if (p2Down) dy2 += Player.deltaY;

            //System.out.println("Hello World");
            /*p1.setTranslateY(p1.getTranslateY() + dy1);
            p2.setTranslateY(p2.getTranslateY() + dy2);*/

            p1.moveY(dy1);
            p2.moveY(dy2);


        }


    }

}
