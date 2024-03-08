module pongfx.pong {
    requires javafx.controls;
    requires javafx.fxml;


    opens pongfx.pong to javafx.fxml;
    exports pongfx.pong;
}