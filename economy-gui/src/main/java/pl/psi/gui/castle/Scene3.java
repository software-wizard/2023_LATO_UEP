package pl.psi.gui.castle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene3 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Scene3.class.getResource("Scene3Rampart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Heroes III");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}