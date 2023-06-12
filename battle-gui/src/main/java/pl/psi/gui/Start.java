package pl.psi.gui;

import java.io.IOException;
import java.util.List;

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
            scene = new Scene( loader.load() );
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
        Creature creature1 = new NecropolisFactory().create( true, 2, 5 );
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 2);

        final Hero ret = new Hero(List.of(creature1), List.of(warMachine1));
        return ret;
    }

    private Hero createP1()
    {
        Creature creature1 = new NecropolisFactory().create( false, 2, 1);
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 2);
        WarMachine warMachine2 = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 2, 1);

        final Hero ret = new Hero(List.of(creature1), List.of(warMachine1, warMachine2));
        return ret;
    }

}
