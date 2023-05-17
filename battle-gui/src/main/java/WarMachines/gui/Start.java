package WarMachines.gui;

import WarMachines.WarMachine;
import WarMachines.WarMachineStatistic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;

import pl.psi.creatures.Creature;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.warmachines.NewHeroPrototype;

import java.io.IOException;
import java.util.List;

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

//    private MapObject createP2()
//    {
//        return new MapObject( List.of( new NecropolisFactory().create( false, 1, 1) ));
//    }
//
//    private MapObject createP1()
//    {
//        return new MapObject( List.of( new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build(), new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).amount(1).build()) );
//    }

    private NewHeroPrototype createP2()
    {
        Creature creature1 = new NecropolisFactory().create( false, 1, 1);
        WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).amount(1).build();

        final NewHeroPrototype ret = new NewHeroPrototype(List.of(creature1), List.of(warMachine1));
        return ret;
    }

    private NewHeroPrototype createP1()
    {
        Creature creature1 = new NecropolisFactory().create( false, 2, 1);
        WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build();
        WarMachine warMachine2 = new WarMachine.Builder().statistic(WarMachineStatistic.FIRST_AID_TENT).amount(1).build();

        final NewHeroPrototype ret = new NewHeroPrototype(List.of(creature1), List.of(warMachine1, warMachine2));
        return ret;
    }

}