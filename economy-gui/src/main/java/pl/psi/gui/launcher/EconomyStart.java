package pl.psi.gui.launcher;

import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;
import pl.psi.hero.Hero;

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

    private void askForPlayers() {
        // Prompt the user for the number of players
        TextInputDialog numPlayersDialog = new TextInputDialog();
        numPlayersDialog.setTitle("Number of Players");
        numPlayersDialog.setHeaderText(null);
        numPlayersDialog.setContentText("Enter the number of players:");
        Optional<String> numPlayersResult = numPlayersDialog.showAndWait();

        // Create a list to hold the players
        List<Player> players = new ArrayList<>();

        // If the user entered a number, create the specified number of players
        if (numPlayersResult.isPresent()) {
            int numPlayers = Integer.parseInt(numPlayersResult.get());
            for (int i = 1; i <= numPlayers; i++) {
                // Prompt the user for the player's name
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("Player " + i + " Name");
                nameDialog.setHeaderText(null);
                nameDialog.setContentText("Enter player " + i + " name:");
                Optional<String> nameResult = nameDialog.showAndWait();
                Player player = new Player(nameResult.get(), null);
                players.add(player);
            }
        }

    }


}
