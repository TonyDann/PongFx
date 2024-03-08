package pongfx.pong;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {

    @Override
    public void handle(long a) {
        doHandle();
    }

    private void doHandle(){
        System.out.println("Hello World");
    }

}
