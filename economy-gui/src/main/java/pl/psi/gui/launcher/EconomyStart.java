package pl.psi.gui.launcher;

import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EconomyStart extends Application
{

    public static void main( final String[] args )
    {
        launch();
    }

    @Override
    public void start( final Stage aStage ) throws Exception
    {
//
//        final FXMLLoader loader = new FXMLLoader();
//        loader.setLocation( getClass().getClassLoader()
//            .getResource( "fxml/eco.fxml" ) );
//        loader.setController( new EcoController( new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000 ),
//            new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000 ) ) );
//        final Scene scene = new Scene( loader.load() );
//        aStage.setScene( scene );
//        aStage.setX( 5 );
//        aStage.setY( 5 );
//        aStage.show();
//

//        final FXMLLoader loader = new FXMLLoader();
//        loader.setLocation( getClass().getClassLoader()
//                .getResource( "fxml/ecoLauncherScene.fxml" ) );
//        final Scene launcherScene = new Scene( loader.load() );
        //askForPlayers();

        Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/ecoLauncherScene.fxml")));
        final Scene launcherScene = new Scene(root);
        aStage.setScene( launcherScene );
        aStage.setX( 5 );
        aStage.setY( 5 );
        aStage.show();

    }



}
