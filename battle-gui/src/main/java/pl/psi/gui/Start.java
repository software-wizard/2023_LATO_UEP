package pl.psi.gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pl.psi.warmachines.WarMachine;
import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.creatures.Creature;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.warmachines.WarMachineFactory;

public class Start extends Application
{

    public Start()
    {

    }

    static void main( final String[] args )
    {
        launch( args );
    }

    @Override
    public void start( final Stage primaryStage )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( createP1(), createP2() ) );

            Parent root = loader.load();
            StackPane stackPane = new StackPane();
            Image img = new Image(new FileInputStream("battle-gui/src/main/resources/AppGui/Background.png"));
            BackgroundImage bImg = new BackgroundImage(img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, true));
            Background bGround = new Background(bImg);
            stackPane.getChildren().add(root);
            stackPane.setBackground(bGround);

            scene = new Scene( stackPane );
            primaryStage.setFullScreen(true);
            primaryStage.setScene( scene );
            primaryStage.setX( 5 );
            primaryStage.setY( 5 );
            primaryStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    private Hero createP2()
    {
        Creature creature1 = new NecropolisFactory().create( true, 2, 3);
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 2);
        WarMachine warMachine2 = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 0, 1);
        WarMachine warMachine3 = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 2);

        return new Hero(List.of(creature1), List.of(warMachine1, warMachine2, warMachine3));
    }

    private Hero createP1()
    {
        Creature creature1 = new NecropolisFactory().create( false, 2, 15);
        Creature creature2 = new NecropolisFactory().create( true, 3, 3 );
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 0, 2);
        WarMachine warMachine2 = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);

        return new Hero(List.of(creature1, creature2), List.of(warMachine1, warMachine2));
    }

}
