package pl.psi.gui.map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.gui.Start;

import java.io.IOException;

public class Map extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = null;

        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/map.fxml" ) );
        loader.setController( new MapController() );
        scene = new Scene( loader.load() );

        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
