package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public final static double DISPLAY_WIDTH = 700;
    public final static double DISPLAY_HEIGHT = 700;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Menu menu = new Menu();
        Group menuGroup = menu.getGroup();
        Scene scene = new Scene(menuGroup, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
