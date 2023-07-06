package pl.psi.gui.castle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Scene1.class.getResource("Scene1Necropolis.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Heroes III");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}