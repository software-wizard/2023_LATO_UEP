package pl.psi.gui.launcher;

import javafx.scene.Parent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class EconomyStart extends Application
{

    public static void main( final String[] args )
    {
        launch();
    }

    @Override
    public void start( final Stage aStage ) throws Exception
    {
        Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/ecoLauncherScene.fxml")));
        final Scene launcherScene = new Scene(root);

        Screen screen = Screen.getPrimary();
        aStage.setX(screen.getVisualBounds().getMinX());
        aStage.setY(screen.getVisualBounds().getMinY());
        aStage.setWidth(screen.getVisualBounds().getWidth());
        aStage.setHeight(screen.getVisualBounds().getHeight());

        aStage.setScene( launcherScene );

        aStage.show();
    }

}
